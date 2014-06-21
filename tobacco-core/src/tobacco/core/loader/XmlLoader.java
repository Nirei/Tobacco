package tobacco.core.loader;

import java.net.URL;

import tobacco.core.components.Entity;
import tobacco.core.systems.MainSystem;

public class XmlLoader implements Loader {
	
	URL systemsList, entityTree;
	
	XmlLoader(URL _systemsList, URL _entityTree) {
		systemsList = _systemsList;
		entityTree = _entityTree;
	}

	@Override
	public MainSystem loadMainSystem(Entity root) {
		return null;
	}

	@Override
	public Entity loadEntityTree() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}
