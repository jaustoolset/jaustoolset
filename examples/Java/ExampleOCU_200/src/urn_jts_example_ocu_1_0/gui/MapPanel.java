package src.urn_jts_example_ocu_1_0.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class MapPanel extends JPanel {
    
	private JLabel mouseX, mouseY;
    private Map map;
    
	public MapPanel() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300,300));
		
        addComponentListener(new ComponentAdapter() {
        	public void componentMoved(ComponentEvent arg0) {
    			redrawMap();
    		}
    		public void componentResized(ComponentEvent arg0) {
    			redrawMap();
    		}
        	public void componentShown(ComponentEvent arg0) {
    			redrawMap();
        	}
    		private void redrawMap() {
        		map.setScrResolution(new Dimension(getWidth(), getHeight()));
        		map.repaint();
        	}
        });

		mouseX = new JLabel("N/A");
		mouseY = new JLabel("N/A");
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	panel.setBorder(new LineBorder(Color.BLACK,1));

    	panel.add(new JLabel("X: "));
        panel.add(mouseX);
        panel.add(new JLabel("Y: "));
        panel.add(mouseY);

		map = new Map(this);
        add(map, BorderLayout.CENTER);
        add(panel, BorderLayout.PAGE_END);
        
	}
	
	public Map getMap() {
		return map;
	}

	public void updatePointerLocation() {
		mouseX.setText(Integer.toString(map.getMousePos().x));
		mouseY.setText(Integer.toString(map.getMousePos().y));
    }
}
