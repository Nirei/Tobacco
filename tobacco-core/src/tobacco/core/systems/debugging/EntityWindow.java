package tobacco.core.systems.debugging;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.NameComponent;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;

public class EntityWindow extends JFrame {

	private static final long serialVersionUID = -3422892085498435560L;
	
	public EntityWindow() throws HeadlessException {
		setUp();
	}

	public EntityWindow(GraphicsConfiguration gc) {
		super(gc);
		setUp();
	}

	public EntityWindow(String title) throws HeadlessException {
		super(title);
		setUp();
	}

	public EntityWindow(String title, GraphicsConfiguration gc) {
		super(title, gc);
		setUp();
	}
	
	private class EntityTreeNode implements TreeNode {
		
		private TreeNode parent;
		private Entity entity;
		
		public EntityTreeNode(TreeNode parent, Entity entity) {
			this.parent = parent;
			this.entity = entity;
		}
		
		private ContainerComponent fetchContainer() {
			return (ContainerComponent) entity.get(Component.CONTAINER_C);
		}

		@Override
		public Enumeration<EntityTreeNode> children() {
			ContainerComponent cComp = fetchContainer();
			if(cComp != null) {
				List<EntityTreeNode> children = new LinkedList<>();
				for(Entity e : cComp) children.add(new EntityTreeNode(this, e));
				return Collections.enumeration(children);
			} else {
				return Collections.emptyEnumeration();
			}
		}

		@Override
		public boolean getAllowsChildren() {
			return true;
		}

		@Override
		public TreeNode getChildAt(int index) {
			return new EntityTreeNode(this, fetchContainer().children().get(index));
		}

		@Override
		public int getChildCount() {
			return fetchContainer().children().size();
		}

		@Override
		public int getIndex(TreeNode node) {
			if(!(node instanceof EntityTreeNode)) return -1;
			return fetchContainer().children().indexOf(((EntityTreeNode) node).entity);
		}

		@Override
		public TreeNode getParent() {
			return parent;
		}

		@Override
		public boolean isLeaf() {
			ContainerComponent cComp = fetchContainer();
			return cComp == null || cComp.children().isEmpty();
		}

		@Override
		public String toString() {
			String tag = "nameless";
			if(entity.has(Component.NAME_C)) {
				tag = ((NameComponent) entity.get(Component.NAME_C)).getName();
			}
			return tag;
		}
		
	}

	private class EntityTreeModel implements TreeModel {
		
		@Override
		public void addTreeModelListener(TreeModelListener l) {
			// TODO Auto-generated method stub
		}

		@Override
		public Object getChild(Object parent, int index) {
			return ((EntityTreeNode) parent).getChildAt(index);
		}

		@Override
		public int getChildCount(Object parent) {
			return ((EntityTreeNode) parent).getChildCount();
		}

		@Override
		public int getIndexOfChild(Object parent, Object child) {
			return ((EntityTreeNode) parent).getIndex(((EntityTreeNode) child));
		}

		@Override
		public Object getRoot() {
			return new EntityTreeNode(null, Directory.getEntityService().getRoot());
		}

		@Override
		public boolean isLeaf(Object node) {
			return ((EntityTreeNode) node).isLeaf();
		}

		@Override
		public void removeTreeModelListener(TreeModelListener l) {
			// TODO Auto-generated method stub
		}

		@Override
		public void valueForPathChanged(TreePath path, Object newValue) {}
	}
	
	private EntityTreeModel model = new EntityTreeModel();
	private JTree tree = new JTree(model);
	
	private void setUp() {
		this.add(tree);
		this.setBounds(500, 60, 800, 400);
	}
}
