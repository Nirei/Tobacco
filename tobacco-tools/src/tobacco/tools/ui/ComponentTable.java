package tobacco.tools.ui;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ComponentTable extends JTable {

	private static final long serialVersionUID = -1051713023947211710L;

	public ComponentTable(TableModel model) {
		super(model);
		setFillsViewportHeight(true);
		setPreferredScrollableViewportSize(getPreferredSize());
		setRowHeight(20);
		setRowSelectionAllowed(false);
		setCellSelectionEnabled(false);
	}
}
