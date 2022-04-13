/***********           LICENSE HEADER   *******************************
JR Middleware
Copyright (c)  2008-2019, DeVivo AST, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the copyright holder nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

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

    template<typename T> T swapBytes(T value) const
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
    void getValueAt(int index, std::string& value) const
    {
        int size = *((int*)(data+index));
        value.assign( data+index+sizeof(int), size);
    }

    // templated function to access an individual piece of the archive
    // based on some index from the front.
    template<class T> void getValueAt(int index, T& value) const
    {
        memcpy(&value,(data+index),sizeof(value));
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
			T temp = swapBytes(value);
			memcpy(data+index, &temp, sizeof(value));
        }
        else 
			memcpy(data+index, &value, sizeof(value));
    }

    // Set the data explicitly from a raw character buffer
    void setData( const char* buffer, unsigned int length);

    // Insert and remove data in the middle of the buffer
    void insertAt( int index, Archive& archive );
    void removeAt( int index, int length );

    // Clear the archive completely
    void clear() {data_length = 0;}
    bool empty() const {return (data_length==0);}

    // Set the packing mode (little versus big endian)
    enum PackMode { LittleEndian, BigEndian };
    void setPackMode(enum PackMode mode) { pack_mode = mode; }
    enum PackMode getPackMode() { return pack_mode; }

    // Access the raw data for message passing
    unsigned int getArchiveLength() const { return data_length; }
    char*          getArchive()       { return data; }
    const char*          getArchive()  const  { return data; }

    // Debugging
    void printArchive(int size) const;

protected:

    char*          data;
    unsigned int buffer_size;
    unsigned int data_length;
    int            offset;
    enum PackMode  pack_mode; 

    void growBuffer(unsigned int newLength);

};

inline void Archive::printArchive(int size) const
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

inline void Archive::setData( const char* buffer, unsigned int length )
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
    unsigned int length = archive.getArchiveLength();

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
    memmove( &data[index], tail, data_length - length - index);
    data_length -= length;
}


}} // namespace DeVivo::Junior
#endif
