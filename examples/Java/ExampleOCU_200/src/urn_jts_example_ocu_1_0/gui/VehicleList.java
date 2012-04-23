package src.urn_jts_example_ocu_1_0.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

public class VehicleList extends JList{
	
	public class VehicleListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (isSelected) {
				setBackground(((Vehicle)value).getVehicleColor());
			} else {
				setBorder(new MatteBorder(1,1,1,10, ((Vehicle)value).getVehicleColor().brighter()));
			}
			setFont(new Font(getFont().getFontName(), Font.BOLD, 12));
			return this;
		}
		
	}
	
	private DefaultListModel vListModel;
	private Vehicle selectedVehicle;

	public VehicleList() {
		vListModel = new DefaultListModel();
		setModel(vListModel);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setCellRenderer(new VehicleListCellRenderer());
		setOpaque(false);
	}
	
	public void add(Vehicle v) {
		vListModel.addElement(v);
	}
}
