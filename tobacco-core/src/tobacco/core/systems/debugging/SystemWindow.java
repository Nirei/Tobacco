package tobacco.core.systems.debugging;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import tobacco.core.systems.AbstractSystem;

public class SystemWindow extends JFrame {

	private static final long serialVersionUID = -3122088661889815438L;
	private DefaultListModel<AbstractSystem> model = new DefaultListModel<AbstractSystem>();
	private JList<AbstractSystem> sysList = new JList<AbstractSystem>(model);
	private JScrollPane scroll = new JScrollPane(sysList);
	
	public SystemWindow() {
		super();
		setTitle("Systems");
		setBounds(500, 490, 800, 200);
		sysList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		sysList.setVisibleRowCount(1);
		sysList.setCellRenderer(new SystemCellRenderer());
		add(scroll);
	}
	
	public void setList(List<AbstractSystem> systems) {
		model.ensureCapacity(systems.size());
		if(model.isEmpty())
			for(AbstractSystem s : systems) model.addElement(s);
		else
			for(int i = 0; i < systems.size(); i++) model.set(i, systems.get(i));
	}
}
