package src.urn_jts_example_ocu_1_0.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;


public interface MapObject {
	String getName();
	void draw(Graphics g, Map map);
	void mouseClicked(MouseEvent e, Map map);
	void mouseDragged(MouseEvent e, Map map);
	void mouseMoved(MouseEvent e, Map map);
	void mousePressed(MouseEvent e, Map map);
	void mouseReleased(MouseEvent e, Map map);
}
