package tobacco.tools.ui;

import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class EntityTree extends JTree {
	
	private static final long serialVersionUID = 8023561973102937786L;
	
	private TreeModelListener keepExpansionAndSelection = new TreeModelListener() {

		@Override
		public void treeStructureChanged(TreeModelEvent e) {
			final TreePath selection = getSelectionModel().getSelectionPath();
			expandAll();
			if(selection != null) {
				setSelectionPath(selection);
			}
		}
		@Override public void treeNodesChanged(TreeModelEvent e) {}
		@Override public void treeNodesInserted(TreeModelEvent e) {}
		@Override public void treeNodesRemoved(TreeModelEvent e) {}
	};

	EntityTree(EntityTreeModel model) {
		super(model);
		model.addTreeModelListener(keepExpansionAndSelection);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setPreferredSize(new Dimension(200, 200));
	}
	
    private void expandAll() {
        int r = 0;
        while (r < this.getRowCount()) {
            this.expandRow(r);
            r++;
        }
    }
}
