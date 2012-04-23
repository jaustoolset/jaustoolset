package src.urn_jts_example_ocu_1_0.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;


public class Vehicle implements MapObject {
	
	private double poseX, poseY, yaw;
	private String name;
	private int width, height;
	private Color vehicleColor;
	
	public Vehicle(double poseX, double poseY, double yaw, String name,
			int width, int height, Color vehicleColor) {
		this.poseX = poseX;
		this.poseY = poseY;
		this.yaw = yaw;
		this.name = name;
		this.width = width;
		this.height = height;
		this.vehicleColor = vehicleColor;
	}

	public Vehicle() {
		this(0.0,0.0,0.0,"SIM",15,20,Color.RED);
	}
	
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return "<html>"+name+
			   "<br> x: "+df.format(poseX)+" y: "+df.format(poseY)+" yaw: "+df.format(Math.toDegrees(yaw))+
			   "</html>";
	}

	public void draw(Graphics g, Map map) {
		Point p = map.localToScrCoord((int)poseX, (int)poseY);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(vehicleColor);
        g2d.translate(p.x, p.y);
        g2d.rotate(yaw);
        int[] px = {0, -width/2, -width/2, width/2,  width/2};
        int[] py = {-height/2, 0, height/2, height/2, 0};
        g.fillPolygon(px, py, 5);
        g2d.setColor(Color.black);
        g.drawPolygon(px, py, 5);
        g.drawOval(-1, 1, 2, 2);
		g2d.rotate(-yaw);
        g2d.translate(-p.x, -p.y);
	}

	public double getPoseX() {
		return poseX;
	}

	public void setPoseX(double poseX) {
		this.poseX = poseX;
	}

	public double getPoseY() {
		return poseY;
	}

	public void setPoseY(double poseY) {
		this.poseY = poseY;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(Color vehicleColor) {
		this.vehicleColor = vehicleColor;
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
