package src.urn_jts_example_ocu_1_0.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class GUI {
	protected MapPanel mapPanel;		
	protected Controls controlPanel;
	protected VehicleList vList;

	public GUI( ) {
        /* Use an appropriate Look and Feel */
        try {
        	boolean useNimbuslnf = false;
        	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                	useNimbuslnf = true;
                	UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        	if (!useNimbuslnf)
        		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private void createAndShowGUI( ) {
		// Create and set up the window.
		JFrame frame = new JFrame("ExampleOCU");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel vehPanel;

		frame.getContentPane().setLayout(new BorderLayout());
		
		vList = new VehicleList();

		vehPanel = new JPanel(new BorderLayout());
		vehPanel.setBorder(BorderFactory.createTitledBorder("Vehicle List"));
		vehPanel.add(new JScrollPane(vList), BorderLayout.CENTER);
		
		controlPanel = new Controls();
		mapPanel = new MapPanel();
		
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(vehPanel, BorderLayout.CENTER);
        leftPane.add(controlPanel, BorderLayout.PAGE_END);
        
        frame.getContentPane().add(leftPane, BorderLayout.LINE_START);
        frame.getContentPane().add(mapPanel, BorderLayout.CENTER);
		
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public MapPanel getMapPanel() {
		return mapPanel;
	}
	
    public Controls getControls() {
		return controlPanel;
	}
    
    public VehicleList getVehicleList() {
    	return vList;
    }

}
