/*
* 	Tobacco - A portable and reusable game engine written in Java.
*	Copyright © 2014 Nirei
*
*	This file is part of Tobacco
*
*   Tobacco is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/
package tobacco.core.systems.debugging;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tobacco.core.components.Component;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.systems.AbstractTreeSystem;

public class InfoSystem extends AbstractTreeSystem {

	private static final Type[] requiredComponents = { Component.DEBUGGING_C };
	private StringBuilder sb;
	private JFrame entityWindow = new JFrame();
	private JTextArea ta = new JTextArea();
	private JScrollPane entityScroll = new JScrollPane(ta);
	private SystemWindow sysWindow = new SystemWindow();

	public InfoSystem() {
		super(requiredComponents);
		
		entityWindow.setBounds(500, 60, 800, 400);
		entityWindow.setTitle("Entities");
		entityWindow.add(entityScroll);
		entityWindow.setVisible(true);
		
		sysWindow.setVisible(true);
	}

	@Override
	public Object process(Entity entity, Object data) {
		String str = (String) data;
		if (str == null)
			str = "";

		if (qualifies(entity)) {
			sb.append(str + entity + " {\n");
			for (Component c : entity)
				if (c.getComponentType() != Component.DEBUGGING_C)
					sb.append(str + "\t" + c + "\n");
			sb.append(str + "}\n");
		}

		return str + "\t";
	}

	@Override
	public void setUp() {
		if(getTicks() % 100 == 0) {
			enable(true);
			sb = new StringBuilder();
			sb.append("Tick: " + getTicks() + " ---\n");
			sb.append(Directory.getEntityService().getEntityList() + "\n");
			
			sysWindow.setList(Directory.getDataService().getMainSystem().getSystems());
		} else {
			enable(false);
		}
	}

	@Override
	public void tearDown() {
		if(isEnabled())
			ta.setText(sb.toString());
	}
}
