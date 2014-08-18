package tobacco.core.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import tobacco.core.components.CollisionMapComponent;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.KeymapComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.RemoveComponent;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.ScaleComponent;
import tobacco.core.components.ScreenComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.components.TrajectoryComponent;
import tobacco.core.util.Vector2D;

public class EntityToXml {

	public static void main(String[] args) throws JAXBException {
		JAXBContext contextObj = JAXBContext.newInstance(new Class[] {
			tobacco.core.components.Entity.class,
			tobacco.core.components.CollisionMapComponent.class,
			tobacco.core.components.ContainerComponent.class,
			tobacco.core.components.DebuggingComponent.class,
			tobacco.core.components.DurationComponent.class,
			tobacco.core.components.KeymapComponent.class,
			tobacco.core.components.MovementComponent.class,
			tobacco.core.components.PlayerComponent.class,
			tobacco.core.components.PositionComponent.class,
			tobacco.core.components.RemoveComponent.class,
			tobacco.core.components.RotationComponent.class,
			tobacco.core.components.ScaleComponent.class,
			tobacco.core.components.ScreenComponent.class,
			tobacco.core.components.SizeComponent.class,
			tobacco.core.components.SolidityComponent.class,
			tobacco.core.components.TextureComponent.class,
			tobacco.core.components.TrajectoryComponent.class
			});
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Entity entity = new Entity();
		ContainerComponent contComp = new ContainerComponent();
		contComp.addChild(new Entity());
		entity.put(contComp);
		
		entity.put(new CollisionMapComponent());
		entity.put(new DebuggingComponent());
		entity.put(new DurationComponent());
		entity.put(new KeymapComponent());
		entity.put(new MovementComponent());
		entity.put(new PlayerComponent());
		entity.put(new PositionComponent(Vector2D.ZERO));
		entity.put(new RemoveComponent());
		entity.put(new RotationComponent());
		entity.put(new ScaleComponent(Vector2D.ONE));
		entity.put(new ScreenComponent(Vector2D.ZERO));
		entity.put(new SizeComponent(Vector2D.ONE));
		entity.put(new SolidityComponent());
		entity.put(new TextureComponent("none"));
		entity.put(new TrajectoryComponent());

		marshallerObj.marshal(entity, System.out);
	}
}
