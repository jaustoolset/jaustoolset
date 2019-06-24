
package src.urn_jts_StillImageClient_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_StillImageClient_1_0.Messages.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class StillImageClient_StillImageClientFSM extends StateMachine{
	protected boolean running;
    StillImageClient_StillImageClientFSMContext context;
    StillImageClientGUI gui;
    
	public StillImageClient_StillImageClientFSM()
	{
		/*
		 * If there are other variables, context must be constructed last so that all 
		 * class variables are available if an EntryAction of the InitialState of the 
		 * statemachine needs them. 
		 */
		context = new StillImageClient_StillImageClientFSMContext(this);

		gui = new StillImageClientGUI();

	}

	
	/// Handle notifications on parent state changes
	public void setupNotifications()
	{

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	public void DisplayStillImageAction(ReportStillImageData msg)
	{
        // post still image to gui frame 
        gui.updateImage(msg.getBody().getStillImageDataList().getElement(0).getImageFrame().getData().array());
	}

	public void SendQueryStillImageDataAction()
	{
        /// Insert User Code HERE
        // Send the QueryStillImageData message to the local component
        QueryStillImageData query = new QueryStillImageData(); 
        
        JausAddress dest = new JausAddress( jausRouter.getJausAddress().getSubsystemID(),
                  jausRouter.getJausAddress().getNodeID(),
                  (short)200);
                  
        sendJausMessage( query, dest);
	}



	
}

