package tobacco.core.systems.debugging;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import tobacco.core.systems.AbstractSystem;

public class SystemCellRenderer implements ListCellRenderer<AbstractSystem> {
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends AbstractSystem> list, AbstractSystem value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JPanel cell = new JPanel();
		cell.setBorder(BorderFactory.createEtchedBorder());
		cell.setLayout(new GridLayout(0,1));
		JLabel name = new JLabel(value.toString());
		JPanel content = new JPanel(new GridLayout(0,2));
		
		JLabel tickLabel = new JLabel("Ticks");
		JLabel tickValue = new JLabel(String.valueOf(value.getTicks()));
		JLabel enableLabel = new JLabel("Enabled");
		JCheckBox enableValue = new JCheckBox();
		enableValue.setEnabled(false);
		enableValue.setSelected(value.isEnabled());
		
		content.add(tickLabel);
		content.add(tickValue);
		content.add(enableLabel);
		content.add(enableValue);
		
		cell.add(name);
		cell.add(new JSeparator(SwingConstants.HORIZONTAL));
		cell.add(content);
		
		if(isSelected) name.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
		return cell;
	}

}
