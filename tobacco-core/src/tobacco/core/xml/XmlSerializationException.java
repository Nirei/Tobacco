package tobacco.core.xml;

public class XmlSerializationException extends Exception {
	
	private Object element;

	public XmlSerializationException(String message, Object element) {
		super(message);
		this.element = element;
	}
	
	public XmlSerializationException(String message, Throwable cause, Object element) {
		super(message, cause);
		this.element = element;
	}
	
	public XmlSerializationException(Throwable cause, Object element) {
		super(cause);
		this.element = element;
	}
	
	public Object getElement() {
		return element;
	}

	@Override
	public void printStackTrace() {
		System.out.println(element);
		super.printStackTrace();
	}
	
	private static final long serialVersionUID = 2511585420020146585L;

}
