package src.urn_jts_example_ocu_1_0.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class Map extends Component implements MouseListener,
		MouseMotionListener {

	private Vector<MapObject> mapObjectList;
	private Point mousePos;
	private Dimension scrResolution;
	private MapPanel mp;
	private double gridSize;
	private Point localOrigin;
	private double pixelSize;

	public Map(MapPanel mp) {
		mapObjectList = new Vector<MapObject>();
		scrResolution = mp.getPreferredSize();
		mousePos = new Point();
		localOrigin = new Point(0, 0);
		pixelSize = 1;
		gridSize = 100;
		addMouseListener(this);
		addMouseMotionListener(this);
		this.mp = mp;
	}

	public void paint(Graphics g) {
		((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON  );
		drawGrid(g);
        synchronized(mapObjectList) {
        	for(MapObject mObj:mapObjectList) {
        		mObj.draw(g, this);
        	}
        }
		g.dispose();
	}

	private void drawGrid(Graphics g) {
		// / getting grid origin on the current screen.
		double lowerLeftX = getLocalOrigin().x - scrResolution.width * 1 / 2;
		double lowerLeftY = getLocalOrigin().y - scrResolution.height * 1 / 2;
		double xGridSize = lowerLeftX - lowerLeftX % gridSize;
		double yGridSize = lowerLeftY - lowerLeftY % gridSize;
		((Graphics2D) g).setStroke(new BasicStroke(1));
		g.setColor(Color.GRAY);
		for (double x = xGridSize; x < xGridSize + scrResolution.width
				* pixelSize + gridSize; x += gridSize) {
			Point screen = localToScrCoord(0, (int) x);
			if (screen.x >= 0 && screen.x <= scrResolution.width) {
				g.drawLine(screen.x, 0, screen.x, getHeight());
			}
		}
		for (double y = yGridSize; y < yGridSize + scrResolution.height
				* pixelSize + gridSize; y += gridSize) {
			Point screen = localToScrCoord((int)y, 0);
			if (screen.y >= 0 && screen.y <= scrResolution.height) {
				g.drawLine(0, screen.y, scrResolution.width, screen.y);
			}
		}
	}

	public void addMapObject(MapObject mObj) {
		synchronized (mapObjectList) {
			mapObjectList.add(mObj);
			repaint();
		}
	}

	public MapObject getMapObject(String name) {
		for (MapObject mobj : mapObjectList) {
			if (mobj.getName().equalsIgnoreCase(name))
				return mobj;
		}
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int index = 0;
		MapObject mObj = null;
		try {
			synchronized (mapObjectList) {
				mObj = mapObjectList.get(index++);
			}
			while (true) {
				mObj.mouseClicked(e, this);
				synchronized (mapObjectList) {
					mObj = mapObjectList.get(index++);
				}
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) { }
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		// Pass this event to any map objects
		int index = 0;
		MapObject mObj = null;
		try {
			synchronized (mapObjectList) {
				mObj = mapObjectList.get(index++);
			}
			while (true) {
				mObj.mousePressed(e, this);
				synchronized (mapObjectList) {
					mObj = mapObjectList.get(index++);
				}
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) { }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Pass this event to any map objects
		int index = 0;
		MapObject mObj = null;
		try {
			synchronized (mapObjectList) {
				mObj = mapObjectList.get(index++);
			}
			while (true) {
				mObj.mouseReleased(e, this);
				synchronized (mapObjectList) {
					mObj = mapObjectList.get(index++);
				}
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) { }
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseTracking(e);

		// Pass this event to any map objects
		int index = 0;
		MapObject mObj = null;
		try {
			synchronized (mapObjectList) {
				mObj = mapObjectList.get(index++);
			}
			while (true) {
				mObj.mouseDragged(e, this);
				synchronized (mapObjectList) {
					mObj = mapObjectList.get(index++);
				}
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) { }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseTracking(e);
		
		// Pass this event to any map objects
		int index = 0;
		MapObject mObj = null;
		try {
			synchronized (mapObjectList) {
				mObj = mapObjectList.get(index++);
			}
			while (true) {
				mObj.mouseMoved(e, this);
				synchronized (mapObjectList) {
					mObj = mapObjectList.get(index++);
				}
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) { }
	}

	private void mouseTracking(MouseEvent e) {
		mousePos = scrToLocalCoord(e.getX(), e.getY());
		mp.updatePointerLocation();
	}

	public Point scrToLocalCoord(int x, int y) {
		return new Point(scrResolution.height / 2 - y, x - scrResolution.width / 2);
	}

	public Point localToScrCoord(int x, int y) {
		return new Point(y + scrResolution.width / 2, scrResolution.height / 2 - x);
	}

	public void setScrResolution(Dimension dimension) {
		this.scrResolution = dimension;
	}

	public Dimension getScrResolution() {
		return scrResolution;
	}

	public Point getMousePos() {
		return mousePos;
	}

	public void setMousePos(Point mousePos) {
		this.mousePos = mousePos;
	}

	public MapPanel getMp() {
		return mp;
	}

	public void setMp(MapPanel mp) {
		this.mp = mp;
	}

	public double getGridSize() {
		return gridSize;
	}

	public void setGridSize(double gridSize) {
		this.gridSize = gridSize;
	}

	public Point getLocalOrigin() {
		return localOrigin;
	}

	public void setLocalOrigin(Point localOrigin) {
		this.localOrigin = localOrigin;
	}

}
