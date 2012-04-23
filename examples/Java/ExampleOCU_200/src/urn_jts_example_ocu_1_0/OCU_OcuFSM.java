
package src.urn_jts_example_ocu_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.transport.OS;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_example_ocu_1_0.InternalEvents.*;
import src.urn_jts_example_ocu_1_0.Messages.*;
import src.urn_jts_example_ocu_1_0.gui.*;

import java.util.Timer;
import java.util.TimerTask;



public class OCU_OcuFSM extends StateMachine{
	protected boolean running;

    OCU_OcuFSMContext context;
	protected JausAddress waypoint_driver;
	protected JausAddress discoveryCmpt;
    protected JausAddress pose_sensor;
	protected ReportLocalWaypoint.Body.LocalWaypointRec target = new ReportLocalWaypoint.Body.LocalWaypointRec();
    protected ReportLocalPose.Body.LocalPoseRec vehicle_pose = new ReportLocalPose.Body.LocalPoseRec();

    protected Timer timer;

    protected GUI gui;
	protected MapPanel mapPanel;
    protected Vehicle sim;
    protected WaypointSet wps;


	public OCU_OcuFSM()
    {
        // user variables
        waypoint_driver = null;
        discoveryCmpt = null;
        pose_sensor = null;
        mapPanel = null;

        gui = new GUI();
        while ( mapPanel == null)
            mapPanel = gui.getMapPanel();

	/*
	 * If there are other variables, context must be constructed last so that all
	 * class variables are available if an EntryAction of the InitialState of the
	 * statemachine needs them.
	 */
		context = new OCU_OcuFSMContext(this);

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

	public void findWaypointDriverAction(ReportServices msg)
    {
           // Loop through all services, searching for the input string
        for (int i = 0; i < msg.getBody().getNodeList().getNumberOfElements(); i++)
        {
            ReportServices.Body.NodeList.NodeSeq node =
                msg.getBody().getNodeList().getElement(i);

            for (int j = 0; j < node.getComponentList().getNumberOfElements(); j++)
            {
                ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq comp =
                    node.getComponentList().getElement(j);

                for (int k = 0; k < comp.getServiceList().getNumberOfElements(); k++)
                {
                    if (comp.getServiceList().getElement(k).getURI().compareTo("urn:jaus:jss:mobility:LocalWaypointListDriver") == 0)
                    {
                        // event register vehicle with gui
                        waypoint_driver = new JausAddress(
                            discoveryCmpt.getSubsystemID(),
                            node.getNodeRec().getNodeID(),
                            comp.getComponentRec().getComponentID());
                        System.out.printf("Found service: Waypoint Driver at %d:%d:%d\n",
                            waypoint_driver.getSubsystemID(), waypoint_driver.getNodeID(), waypoint_driver.getComponentID());
                    }

                    if (comp.getServiceList().getElement(k).getURI().compareTo("urn:jaus:jss:mobility:LocalPoseSensor") == 0)
                    {
                        // event register vehicle with gui
                        pose_sensor = new JausAddress(
                            discoveryCmpt.getSubsystemID(),
                            node.getNodeRec().getNodeID(),
                            comp.getComponentRec().getComponentID());
                        System.out.printf("Found service: Pose Sensor at %d:%d:%d\n",
                            pose_sensor.getSubsystemID(), pose_sensor.getNodeID(), pose_sensor.getComponentID());
                    }
                }
            }
        }

        // add the vehicle to the gui
        if (pose_sensor != null && waypoint_driver != null) {
            sim = new Vehicle();
            mapPanel.getMap().addMapObject(sim);
            gui.getVehicleList().add(sim);
            mapPanel.getMap().repaint(); // refresh gui
        }

        gui.getControls().setInternalEventHandler(ieHandler);


    }

    public void printDebugAction(ReportActiveElement msg)
    {
        //Update Current Waypoint with gui
        if (wps!=null)
            wps.setCurrentWaypoint(msg.getBody().getActiveElementRec().getElementUID()-1);

        System.out.printf("Current waypoint: %d     Target: X=%.2f  Y=%.2f    VEHICLE POS: X=%.2f    Y=%.2f     YAW=%.2f                    \r",
        msg.getBody().getActiveElementRec().getElementUID(),
        target.getX(), target.getY(),
        vehicle_pose.getX(), vehicle_pose.getY(), vehicle_pose.getYaw()*180.0/3.1415926);
    }

    public void saveDiscoveryServerIDAction(long source_id)
    {
        this.discoveryCmpt = new JausAddress(source_id);
    }

    public void saveLocalPoseAction(ReportLocalPose msg)
    {
        // Save the vehicle pose data to be outputted by the printDebugAction
        vehicle_pose = msg.getBody().getLocalPoseRec();

        sim.setPoseX(vehicle_pose.getX());
        sim.setPoseY(vehicle_pose.getY());
		sim.setYaw(vehicle_pose.getYaw());
		mapPanel.getMap().repaint(); // refresh gui
        gui.getVehicleList().repaint();
    }

    public void saveTargetDataAction(ReportLocalWaypoint msg)
    {
        // Save the target data to be outputted by the printDebugAction
        target = msg.getBody().getLocalWaypointRec();
    }

    public void sendControlMessageAction(GuiControlEntered msg)
    {
        switch(msg.getGuiControlEnteredBody().getGuiControlEnteredRecord().getCommand()) {
            case 1:
                System.out.println( "\nGui Command: Send Waypoints" );
                sendWaypointsAction();
                break;
            case 2:
                System.out.println( "\nGui Command: Go" );
                sendExecuteListAction();
                break;
            case 3:
                System.out.println( "\nGui Command: Speed Change: "+msg.getGuiControlEnteredBody().getGuiControlEnteredRecord().getValue());
                ExecuteList execute_msg = new ExecuteList();
                execute_msg.getBody().getExecuteListRec().setSpeed(msg.getGuiControlEnteredBody().getGuiControlEnteredRecord().getValue());
                sendJausMessage( execute_msg, waypoint_driver );
                break;
            default:
                System.out.println( "\nUnknown input: " + msg.getGuiControlEnteredBody().getGuiControlEnteredRecord().getCommand() );
                break;
        }
    }

    public void sendExecuteListAction()
    {
        // Send ExecuteList message to Waypoint Driver Component
        ExecuteList execute_msg = new ExecuteList();
        execute_msg.getBody().getExecuteListRec().setSpeed(10);
        execute_msg.getBody().getExecuteListRec().setElementUID(1);
        sendJausMessage( execute_msg, waypoint_driver );
    }

    public void sendQueryIdentificationAction()
    {
        /// Broadcast query_id message so we can find the Discovery component
        QueryIdentification query_id_msg = new QueryIdentification();
        sendJausMessage( query_id_msg, new JausAddress(0xFFFF, (short) 0xFF, (short) 0xFF) );
    }

    public void sendQueryServicesAction()
    {
        if (discoveryCmpt != null)
        {
            // Send a request to the Discovery component to find the Simulation controller...
            QueryServices.Body.NodeList.NodeSeq.ComponentList.ComponentRec comp_rec = new QueryServices.Body.NodeList.NodeSeq.ComponentList.ComponentRec();
            comp_rec.setComponentID((short)255);
            QueryServices.Body.NodeList.NodeSeq seq = new QueryServices.Body.NodeList.NodeSeq();
            seq.getNodeRec().setNodeID((short)255);
            seq.getComponentList().addElement(comp_rec);
            QueryServices query_msg = new QueryServices();
            query_msg.getBody().getNodeList().addElement(seq);
            sendJausMessage( query_msg, discoveryCmpt );
        }
    }

    public void sendQueryStatusAction()
    {
        // Send QueryStatus message to Waypoint Driver Component
        QueryStatus query_status_msg = new QueryStatus();
        sendJausMessage( query_status_msg, waypoint_driver );
    }

    public void sendRequestControlAction()
    {
        // Send Request Control message to Waypoint Driver Component
        RequestControl request_control_msg = new RequestControl();
        request_control_msg.getBody().getRequestControlRec().setAuthorityCode((short)50);
        sendJausMessage( request_control_msg, waypoint_driver );
    }

    public void sendResumeAction()
    {
        // Send Request Control message to Waypoint Driver Component
        Resume resume_msg = new Resume();
        sendJausMessage( resume_msg, waypoint_driver );
    }

    // GUI control "Send Waypoints" action
    public void sendWaypointsAction()
    {

        // Create the list request
        SetElement set_element_msg = new SetElement();
        set_element_msg.getBody().getSetElementSeq().getRequestIDRec().setRequestID((short)1);

        if (wps == null) {
            wps = new WaypointSet();
            mapPanel.getMap().addMapObject(wps);
        } else {
            wps.clear();
            mapPanel.getMap().repaint(); // refresh gui
        }

        // Populate with a list of four sample waypoints, each connected to the next in a simple square
        for (int i=1; i<=4; i++)
        {
            SetLocalWaypoint waypoint = new SetLocalWaypoint();
            waypoint.getBody().getLocalWaypointRec().setX( 100.0 * ((i==2 || i==3) ? -1.0 : 1.0 ));
            waypoint.getBody().getLocalWaypointRec().setY( 100.0 * ((i==3 || i==4) ? -1.0 : 1.0 ));
            waypoint.getBody().getLocalWaypointRec().setWaypointTolerance( 2 );
            ByteBuffer buffer = ByteBuffer.allocate(500);
            waypoint.encode(buffer, 0); //int pos = 0

            SetElement.Body.SetElementSeq.ElementList.ElementRec element = new SetElement.Body.SetElementSeq.ElementList.ElementRec();
            element.setElementUID(i);
            element.setPreviousUID(i-1);
            element.setNextUID((i==4) ? 0 : i+1);
            element.getElementData().set((short)0, (int)waypoint.getSize(), buffer);
            set_element_msg.getBody().getSetElementSeq().getElementList().addElement(element);

            // Add the waypoiny to the gui waypointSet
            wps.addWaypoint(waypoint.getBody().getLocalWaypointRec().getX(), waypoint.getBody().getLocalWaypointRec().getY());
            mapPanel.getMap().repaint(); // refresh gui
        }

        // Update WaypointSet in GUI

        // Now create the SetElement message that contains the encoded waypoint list
        System.out.printf("Done encoding %d waypoints.  Sending...\n", set_element_msg.getBody().getSetElementSeq().getElementList().getNumberOfElements());

        // Create and send as a component message to the Waypoint Component
        sendJausMessage( set_element_msg, waypoint_driver );
    }


    public void setupQueryTimerAction()
    {
        long delay = 0; // delay in milliseconds before task is to be executed
        long period = 100; // time in milliseconds between successive task executions
        System.out.println("\nSetting up Timer for querys: delay= "+delay+" period: "+period);
        timer = new Timer();
        timer.schedule(new MsgQueryTask(), delay, period);
    }

    class MsgQueryTask extends TimerTask {
        @Override
        public void run() {
        // Send query active element to the waypoint driver
        QueryActiveElement query_active_element_msg = new QueryActiveElement();
        sendJausMessage( query_active_element_msg, waypoint_driver );

        // Send query local pose to PoseSensor
        QueryLocalPose query_local_pose_msg = new QueryLocalPose();
        query_local_pose_msg.getBody().getQueryLocalPoseRec().setPresenceVector(67); // x, y, and yaw (heading)
        sendJausMessage( query_local_pose_msg, pose_sensor );

        // Create and send as a component message to the Waypoint Component
        QueryLocalWaypoint query_waypoint_msg = new QueryLocalWaypoint();
        sendJausMessage( query_waypoint_msg, waypoint_driver );
        }
    }


    public boolean inReadyState(ReportStatus msg)
    {
        return (msg.getBody().getReportStatusRec().getStatus() == 1);
    }

    public boolean isControlAccepted(ConfirmControl msg)
    {
        return (msg.getBody().getConfirmControlRec().getResponseCode() == 0);
    }

}

