/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2016 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package tobacco.tools.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;

public class EntityWindow extends JFrame {

	private static final long serialVersionUID = -3422892085498435560L;

	public EntityWindow(String title) throws HeadlessException {
		super(title);
		setUp();
	}
	
    private JPanel panel = new JPanel();
    private JSplitPane split;
	private JScrollPane scrollPaneTree, scrollPaneTable;
    private EntityTreeModel treeModel = new EntityTreeModel();
	private EntityTree tree = new EntityTree(treeModel);
	private ComponentTableModel tableModel = new ComponentTableModel();
	private ComponentTable table = new ComponentTable(tableModel);
	
	private void setUp() {
		tree.addTreeSelectionListener(tableModel);
		panel.setLayout(new BorderLayout());
		scrollPaneTree = new JScrollPane(tree);
		scrollPaneTable = new JScrollPane(table);
		split = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT,
				scrollPaneTree,
				scrollPaneTable);
		
		panel.add(split);
		panel.setPreferredSize(new Dimension(700, 400));
		this.add(panel);
		this.setAutoRequestFocus(false);
		this.pack();
		this.setLocation(500, 60);
		
		Timer treeTimer = new Timer(500, treeModel);
		treeTimer.start();
		Timer tableTimer = new Timer(250, tableModel);
		tableTimer.start();
	}
}
