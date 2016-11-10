package decorators;

public class Normalizer extends AbstractDecorator {

	public Normalizer(Decorator decorator) {
		super(decorator);
	}

	@Override
	public String format2(String str) {
		String aux = "";
		if (decorator != null) {
			char[] c = str.toCharArray();
			for (char ch : c) {
				if (ch != '\r') {
					aux += ch;
				}
			}
		}
		return aux;
	}

}
