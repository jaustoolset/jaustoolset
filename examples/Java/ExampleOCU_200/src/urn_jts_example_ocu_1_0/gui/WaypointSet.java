package src.urn_jts_example_ocu_1_0.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;


public class WaypointSet implements MapObject {
	
	private Vector<Point> wpset;
	private String name;
	private Color setColor;
	private int currentWaypoint;

	public WaypointSet(){
		this(new Vector<Point>(), "newList", Color.BLUE);
	}
	
	public WaypointSet(Vector<Point> wpset, String name, Color setColor){
		this.wpset = wpset;
		this.name = name;
		this.setColor = setColor;
		this.currentWaypoint = 0;
	}
	
	public void addWaypoint(double x, double y) {
		addWaypoint(new Point((int)x,(int)y));
	}
	
	public void addWaypoint(Point pt) {
		wpset.add(pt);
	}
	
	public void removeWaypoint(Point pt) {
		wpset.remove(pt);
	}
	
	public void clear() {
		wpset.clear();
	}
	
	public void setCurrentWaypoint(int currentWaypoint) {
		this.currentWaypoint = currentWaypoint;
	}
	
	@Override
	public void draw(Graphics g, Map map) {
		//g.setColor(setColor);
		((Graphics2D) g).setStroke(new BasicStroke( 1.0f ));
        Point prevPoint = null;
        for(int i=0; i<wpset.size();i++) {
    		g.setColor(setColor);
        	Point screen = map.localToScrCoord(wpset.get(i).x, wpset.get(i).y);
            // draw normal waypoint icon
            g.fillRect(screen.x-3,screen.y-3,6,6);
            if (prevPoint!=null) {
                g.drawLine(prevPoint.x, prevPoint.y, screen.x, screen.y);
            }
            prevPoint=screen;
            if (currentWaypoint == i) {
        		g.setColor(Color.GREEN);
        		((Graphics2D) g).translate(screen.x, screen.y-6);
                int[] px = {0, -6/2, 6/2};
                int[] py = {6/2, -6/2, -6/2};
                g.fillPolygon(px, py, 3);
        		((Graphics2D) g).translate(-screen.x, -(screen.y-6));
            	//g.drawOval(screen.x-5,screen.y-5,10,10);
            }
        }
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void mouseClicked(MouseEvent e, Map map) { }

	@Override
	public void mouseDragged(MouseEvent e, Map map) { }

	@Override
	public void mouseMoved(MouseEvent e, Map map) { }

	@Override
	public void mousePressed(MouseEvent e, Map map) { }

	@Override
	public void mouseReleased(MouseEvent e, Map map) { }

}
