package tobacco.tools.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;

import tobacco.core.entities.Entity;

public class ComponentTableModel extends AbstractTableModel implements TreeSelectionListener, ActionListener {
	private static final long serialVersionUID = 16276131148488760L;
	
	private static final String headings[] = {"Component", "Value"};
	private Entity entity;
	
	public ComponentTableModel() {}
	
	@Override public Object getValueAt(int rowIndex, int columnIndex) { 
		if(entity == null) return "";
		switch (columnIndex) {
		case 0:
			return entity.components().get(rowIndex).getComponentType().getName();
		case 1:
			return entity.components().get(rowIndex);
		}
		
		return "";
	}
	
	@Override public int getRowCount() {
		if(entity == null) return 0;
		return entity.components().size();
	}
	
	@Override public int getColumnCount() {	return 2; }
	
	@Override public String getColumnName(int column) {
		return headings[column];
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Entity selected = ((EntityTreeModel.EntityTreeNode) e.getPath().getLastPathComponent()).entity();
		if(!selected.equals(entity)) {
			entity = selected;
			fireTableChanged(new TableModelEvent(this));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fireTableChanged(new TableModelEvent(this));
	};
}
