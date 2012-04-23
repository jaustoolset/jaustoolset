

#include "urn_jaus_jss_environmentSensing_StillImage_1_0/StillImage_ReceiveFSM.h"
#if (defined WINDOWS) || (defined WIN32)
#include <windows.h>
#else
#include <unistd.h>
#endif




using namespace JTS;

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{

static JausAddress toJausAddress(Receive::Body::ReceiveRec transportData)
{
   return JausAddress( transportData.getSrcSubsystemID(),
					   transportData.getSrcNodeID(),
					   transportData.getSrcComponentID());
}
static int imageFileCapture(unsigned char* jpeg_data, int& jpeg_size)
{
    static int frameCount = 1;

#ifdef WINDOWS
	// This way is for Windows using <windows.h>.
    HANDLE hFile = INVALID_HANDLE_VALUE;
	char filename[MAX_PATH];
	// jpg located in video/frameXXX.jpg
    sprintf(filename, "../video/frame%03d.jpg", frameCount);

    hFile = CreateFile(filename,              // file to open
                       GENERIC_READ,          // open for reading
                       FILE_SHARE_READ,       // share for reading
                       NULL,                  // default security
                       OPEN_EXISTING,         // existing file only
                       FILE_ATTRIBUTE_NORMAL, // normal file
                       NULL);                 // no attr. template
 
    if (hFile == INVALID_HANDLE_VALUE) 
    { 
        printf("Invalid handle returned for %s\n", filename);
        return NULL; 
    }

    // Copy it to the shared buffer.
    if( FALSE == ReadFile(hFile, jpeg_data, 100000, (LPDWORD)&jpeg_size, NULL) )
    {
        printf("Terminal failure: Unable to read from file %s\n", filename);
    }
    
    // close handle
    CloseHandle(hFile);
    // increment frame count or restart
    if (++frameCount == 31) frameCount = 1;
    
	return frameCount;
#else
    // other
    FILE * hFile = NULL;
	char filename[FILENAME_MAX];
    // jpg located in video/frameXXX.jpg
    sprintf(filename, "../video/frame%03d.jpg", frameCount);

    if ((hFile = fopen(filename, "r")) == NULL)
    { 
        printf("Invalid handle returned for %s\n", filename);
        return 0; 
    }

    // Copy it to the shared buffer.
    jpeg_size = fread(jpeg_data, 1, 100000, hFile);
    
    // close handle
    fclose(hFile);
    // increment frame count or restart
    if (++frameCount == 31) frameCount = 1;
    
	return frameCount;
#endif
}


StillImage_ReceiveFSM::StillImage_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_environmentSensing_VisualSensor_1_0::VisualSensor_ReceiveFSM* pVisualSensor_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new StillImage_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this->pEvents_ReceiveFSM = pEvents_ReceiveFSM;
	this->pAccessControl_ReceiveFSM = pAccessControl_ReceiveFSM;
	this->pVisualSensor_ReceiveFSM = pVisualSensor_ReceiveFSM;
}



StillImage_ReceiveFSM::~StillImage_ReceiveFSM() 
{
	delete context;
}

void StillImage_ReceiveFSM::setupNotifications()
{
	pVisualSensor_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled", ieHandler, "InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlled", "VisualSensor_ReceiveFSM");
	pVisualSensor_ReceiveFSM->registerNotification("Receiving_Ready_Controlled", ieHandler, "InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_Controlled", "VisualSensor_ReceiveFSM");
	pVisualSensor_ReceiveFSM->registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlled", "VisualSensor_ReceiveFSM");
	pVisualSensor_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_StillImage_ReceiveFSM_Receiving_Ready_NotControlled", "VisualSensor_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled", pVisualSensor_ReceiveFSM->getHandler(), "InternalStateChange_To_VisualSensor_ReceiveFSM_Receiving_Ready_NotControlled", "StillImage_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled", pVisualSensor_ReceiveFSM->getHandler(), "InternalStateChange_To_VisualSensor_ReceiveFSM_Receiving_Ready_Controlled", "StillImage_ReceiveFSM");
	registerNotification("Receiving_Ready", pVisualSensor_ReceiveFSM->getHandler(), "InternalStateChange_To_VisualSensor_ReceiveFSM_Receiving_Ready", "StillImage_ReceiveFSM");
	registerNotification("Receiving", pVisualSensor_ReceiveFSM->getHandler(), "InternalStateChange_To_VisualSensor_ReceiveFSM_Receiving", "StillImage_ReceiveFSM");

}

void StillImage_ReceiveFSM::SendAction(std::string arg0, Receive::Body::ReceiveRec transportData)
{
	if (arg0 == "ReportStillImageData")
	{
		// form the response
		ReportStillImageData::Body::StillImageDataList::StillImageDataRec image;
		image.setSensorID(1);
		image.setReportCoordinateSystem(0);

		// get the next image and copy from the shared buffer to the message
		unsigned char jpeg_data[100000]; int jpeg_size = 0;
		imageFileCapture(jpeg_data, jpeg_size);
		image.getImageFrame()->set(0, jpeg_size, jpeg_data);
        
		// set the image data in the response list and send back to the requestor
		ReportStillImageData response;
		response.getBody()->getStillImageDataList()->addElement( image );
		sendJausMessage( response, toJausAddress(transportData) );
                fprintf(stdout, "Sending image size %d bytes\r", jpeg_size);
                fflush(stdout);

	}
}

void StillImage_ReceiveFSM::updateStillImageSensorConfigurationAction()
{
	/// Insert User Code HERE
}



bool StillImage_ReceiveFSM::isControllingClient(Receive::Body::ReceiveRec transportData)
{
	//// By default, inherited guards call the parent function.
	//// This can be replaced or modified as needed.
	return pAccessControl_ReceiveFSM->isControllingClient(transportData );
}

bool StillImage_ReceiveFSM::isCoordinateTransformSupported()
{
	/// Insert User Code HERE
	return false;
}



};
