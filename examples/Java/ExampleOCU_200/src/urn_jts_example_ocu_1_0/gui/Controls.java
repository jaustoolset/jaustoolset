package src.urn_jts_example_ocu_1_0.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import framework.internalEvents.*;
import src.urn_jts_example_ocu_1_0.InternalEvents.*;


public class Controls extends JPanel implements ActionListener, ChangeListener{

	private JButton sendButton, goButton;
	private JSlider speedSlider;
	private InternalEventHandler ieHandler;
	
	public Controls () {
        ieHandler = null;
    
		setBorder(BorderFactory.createTitledBorder("Controls"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		sendButton = new JButton("Send Waypoints");
		sendButton.addActionListener(this);
		sendButton.setAlignmentX(CENTER_ALIGNMENT);
        
		goButton = new JButton("Go");
		goButton.addActionListener(this);
		goButton.setAlignmentX(CENTER_ALIGNMENT);
        
		speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
		speedSlider.addChangeListener(this);
		speedSlider.setAlignmentX(CENTER_ALIGNMENT);
		speedSlider.setMajorTickSpacing(5);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setSnapToTicks(true);
        
        sendButton.setMaximumSize(speedSlider.getPreferredSize());
		goButton.setMaximumSize(speedSlider.getPreferredSize());
		
		add(sendButton);
		add(goButton);
		add(speedSlider);
	}
    
    public void setInternalEventHandler(InternalEventHandler ieHandler){
        //System.out.println("Setting Controls ieHandler to "+ieHandler);
        this.ieHandler = ieHandler;
    }
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource()==sendButton) {
			//System.out.println("Control Change: sendButton");
            GuiControlEntered command = new GuiControlEntered();
            command.getGuiControlEnteredBody().getGuiControlEnteredRecord().setCommand( 1 );
            if (ieHandler!=null) ieHandler.invoke(command);
            else {System.out.println("Controls ieHandler is Null");}
		}
		if (ae.getSource()==goButton) {
			//System.out.println("Control Change: goButton");
            GuiControlEntered command = new GuiControlEntered();
            command.getGuiControlEnteredBody().getGuiControlEnteredRecord().setCommand( 2 );
            // set value to current speedSlider value
            command.getGuiControlEnteredBody().getGuiControlEnteredRecord().setValue( speedSlider.getValue() );
            if (ieHandler!=null) ieHandler.invoke(command);
            else {System.out.println("Controls ieHandler is Null");}
		}
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		JSlider source = (JSlider)ce.getSource();
		// only updates when slider stops moving
		if (!source.getValueIsAdjusting() && source == speedSlider) {
			//System.out.println("Control Change: speedSlider: value= " + source.getValue());
            GuiControlEntered command = new GuiControlEntered();
            command.getGuiControlEnteredBody().getGuiControlEnteredRecord().setCommand( 3 );
            command.getGuiControlEnteredBody().getGuiControlEnteredRecord().setValue( source.getValue() );
            if (ieHandler!=null) ieHandler.invoke(command);
            else {System.out.println("Controls ieHandler is Null");}
		}
	}

}
