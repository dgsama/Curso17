package decorators;

public abstract class AbstractDecorator implements Decorator {

	protected Decorator decorator;

	public AbstractDecorator(Decorator decorator) {
		this.decorator = decorator;
	}

	public String format(String str) {
		String result = format2(str);
		if (decorator != null) {
			return decorator.format(result);
		} else {
			return result;
		}

	}

	public abstract String format2(String str);

}
