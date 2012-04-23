/*! 
 ***********************************************************************
 * @file      Archive.h
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/03/03
 *
 *  Copyright (C) 2008. DeVivo AST, Inc
 *
 *  This file is part of Jr Middleware.
 *
 *  Jr Middleware is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jr Middleware is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jr Middleware.  If not, see <http://www.gnu.org/licenses/>.
 *
 ************************************************************************
 */

#ifndef  __JAUS_ARCHIVE_H
#define  __JAUS_ARCHIVE_H

#include <iostream>
#include <math.h>
#include "Types.h"
#include "OS.h"
#include "JrLogger.h"

namespace DeVivo {
namespace Junior {

//
// For convenience, we define the scale and unscale functions here,
// since we use them frequently to populate an archive.
//
template<typename S, typename T>
void scaleValue(S in, T& out, double min_value, double max_value)
{
    double scale_factor = (max_value - min_value) / 
                                 (double)(pow(2, sizeof(T)*8) - 1);
    out = (T) ( (in - min_value) / scale_factor + 0.5 );
}

template<typename S, typename T>
void unscaleValue(S in, T& out, double min_value, double max_value)
{
    double scale_factor = (max_value - min_value) / 
                            (double)(pow(2, sizeof(S)*8) - 1);
    out = (T) (in * scale_factor + min_value );
}
    


class Archive 
{
public:

    Archive():
       data(),
       data_length(0),
       buffer_size(0),
       offset(0),
       pack_mode(LittleEndian)
    {
        data = (char*) malloc(255);
        buffer_size = 255;
    }

    ~Archive()
    {
        if (data) free(data);
    }

    void operator=(const Archive& in)
    {
        // strip the const qualifier
        Archive& temp = const_cast<Archive&>(in);
        setData( temp.getArchive(), temp.getArchiveLength() );
    }

    template<typename T> T swapBytes(T value)
    {
        if ((sizeof(T)!=2) && (sizeof(T)!=4)) return value;
        char temp[sizeof(T)];
        for (int i=0; i < sizeof(T); i++)
            temp[i] = ((char*)(&value))[sizeof(T)-1-i];
        return *((T*) temp);
    }

    // templated operator to append data on the archive
    template<typename T> void operator<<(T value)
    {
        // If necessary, grow the buffer to accommodate the new data
        growBuffer(data_length + sizeof(T));

        // Switch to network byte ordering if the length of the value
        // is more than a single byte.  This implementation
        // assumes an Intel byte ordering (host is little endian).
        bool isBigHost = (htons(256) == 256);
        if (( isBigHost && (pack_mode == LittleEndian)) ||
            (!isBigHost && (pack_mode == BigEndian)))
        {
			T tempValue = swapBytes(value);
			memcpy( data+data_length, (void*) &tempValue, sizeof(T) );
        }
        else 
            memcpy( data+data_length, (void*) &value, sizeof(T) );
        data_length += sizeof(T);
    }

    // templated operator to pull data from the archive
	// UPDATE 12/2010: ARM processors don't like pointers
	// that span 4-byte boundaries when the data size
	// is 4 bytes.  Switch to using memcpy.
    template<typename T> void operator>>(T& value)
    {
		T tempValue;
        memcpy( (void*) &tempValue, data+offset, sizeof(T));
        bool isBigHost = (htons(256) == 256);
        if (( isBigHost && (pack_mode == LittleEndian)) ||
            (!isBigHost && (pack_mode == BigEndian)))
            value = swapBytes(tempValue);
		else
			value = tempValue;
        offset += sizeof(T);
        return;
    }

    // Handle strings
    void operator<<(std::string value)
    {
        // If necessary, grow the buffer to accommodate the new data
        growBuffer(data_length + value.length() + sizeof(int));

        // When packing strings, we must first pack the length.
        *(int*)(data+data_length)=(int)(value.length());
        data_length += sizeof(int);
        strncpy(data+data_length, value.c_str(), value.length());
        data_length += value.length();
    }
    void operator>>(std::string& value)
    {
        // When packing strings, we must first pack the length.
        int size = *((int*)(data+offset));
        offset += sizeof(int);
        value.assign( data+offset, size);
        offset += size;
    }

    // Special case for archives.
    void operator>>(Archive& value)
    {
        // Set the value based using the current offset and rest of the buffer
        if (data_length-offset > 0)
           value.setData(data+offset, data_length-offset);
        else
            value.setData(NULL, 0);
    }
    void operator<<(Archive& value)
    {
        append(value);
    }

    // Rewind the archive to the beginning
    void rewind() { offset = 0; }

    // Specialized function for appending data onto the archive
    void append(Archive& archive);
    void append(const char* buffer, unsigned int length);

    // We can't use a template to access a string directly
    void getValueAt(int index, std::string& value)
    {
        int size = *((int*)(data+index));
        value.assign( data+index+sizeof(int), size);
    }

    // templated function to access an individual piece of the archive
    // based on some index from the front.
    template<class T> void getValueAt(int index, T& value)
    {
        value = *((T*) (data+index));
        bool isBigHost = (htons(256) == 256);
        if (( isBigHost && (pack_mode == LittleEndian)) ||
            (!isBigHost && (pack_mode == BigEndian)))
            value = swapBytes(value);
    }

    template<class T> void setValueAt(int index, T value)
    {
        // Switch to network byte ordering if the length of the value
        // is more than a single byte.  
        bool isBigHost = (htons(256) == 256);
        if (( isBigHost && (pack_mode == LittleEndian)) ||
            (!isBigHost && (pack_mode == BigEndian)))
        {
            *((T*)(data+index)) = swapBytes(value);
        }
        else 
            *((T*)(data+index)) = value;
    }

    // Set the data explicitly from a raw character buffer
    void setData( const char* buffer, unsigned short length);

    // Insert and remove data in the middle of the buffer
    void insertAt( int index, Archive& archive );
    void removeAt( int index, int length );

    // Clear the archive completely
    void clear() {data_length = 0;}
    bool empty() {return (data_length==0);}

    // Set the packing mode (little versus big endian)
    enum PackMode { LittleEndian, BigEndian };
    void setPackMode(enum PackMode mode) { pack_mode = mode; }
    enum PackMode getPackMode() { return pack_mode; }

    // Access the raw data for message passing
    unsigned short getArchiveLength() { return data_length; }
    char*          getArchive()       { return data; }

    // Debugging
    void printArchive(int size);

protected:

    char*          data;
    unsigned short buffer_size;
    unsigned short data_length;
    int            offset;
    enum PackMode  pack_mode; 

    void growBuffer(unsigned int newLength);

};

inline void Archive::printArchive(int size)
{
    // get the stream from the logger
    std::ostream& out = Logger::get()->getStream(Logger::full);
    JrFull << "Archive length: " << data_length << std::endl;
    JrFull << "Archive: ";
    int max_print = (size > data_length) ? data_length : size;
    for (int i=0; i < max_print; i++)
		out << "  0x" << std::hex <<(unsigned int)((char) *(data+i))<<std::dec;
    out << std::endl;
}

inline void Archive::setData( const char* buffer, unsigned short length )
{
    // Clear any existing data
    if (data) free(data);

    // Increase the buffer if needed.
    if (length > buffer_size) buffer_size = length;

    // malloc memory for the new data buffer
    data = (char*) malloc(buffer_size);

    // copy from the given buffer
    memcpy( data, buffer, length);
    data_length = length;
}


inline void Archive::append(Archive& archive)
{
    // If necessary, grow the buffer to accommodate the new data
    growBuffer(data_length + archive.getArchiveLength());
    memcpy( data+data_length, archive.getArchive(),
            archive.getArchiveLength());
    data_length += archive.getArchiveLength();
}

inline void Archive::append(const char* buffer, unsigned int length)
{
    // If necessary, grow the buffer to accommodate the new data
    growBuffer(data_length + length);
    memcpy( data+data_length, buffer, length);
    data_length += length;
}


inline void Archive::growBuffer(unsigned int newLength)
{
    // Only grow it if the buffer size is smaller than the requested size
    if (newLength <= buffer_size) return;
    buffer_size = newLength;
    char* temp_data = (char*) malloc(buffer_size);
    memcpy( temp_data, data, data_length);
    if (data) free(data);
    data = temp_data;
}

inline void Archive::insertAt( int index, Archive& archive )
{
    unsigned short length = archive.getArchiveLength();

    // If necessary, grow the buffer to accommodate the new data
    growBuffer(data_length + length);

    // Shift the tail data backward to create an empty region in the buffer
    char* temp_data = (char*) malloc( buffer_size );
    int tail_size = data_length - index;
    memcpy( temp_data, &data[index], tail_size );
    memcpy( &data[index+length], temp_data, tail_size ); 
    free(temp_data);
    
    // insert the stuff from the new archive
    memcpy( &data[index], archive.getArchive(), length );
    data_length += length;
}

inline void Archive::removeAt( int index, int length )
{
    // Shift the tail data forward and reduce the count.
    char* tail = &data[index + length]; 
    memcpy( &data[index], tail, data_length - length - index);
    data_length -= length;
}


}} // namespace DeVivo::Junior
#endif
