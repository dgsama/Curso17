package decorators;

public class Spaces extends AbstractDecorator {

	public Spaces(Decorator decorator) {
		super(decorator);
	}

	@Override
	public String format2(String str) {
		String aux = "";
		if (decorator != null) {
			char[] c = str.toCharArray();
			char previous;
			for (char ch : c) {
				previous = ch;
				if (ch != ' ') {
					aux += ch;
				} else if (ch == ' ' && previous != ' ') {
					aux += ch;
				}
			}
		}
		return aux;
	}

}
