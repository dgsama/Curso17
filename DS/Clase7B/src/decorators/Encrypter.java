package decorators;

public class Encrypter extends AbstractDecorator {

	public Encrypter(Decorator decorator) {
		super(decorator);

	}

	@Override
	public String format2(String str) {
		char[] chars = str.toCharArray();

		for (char c : chars) {
			if (Character.isLetter(c) || Character.isDigit(c)) {
				c = (char) (c + 1);
			}
		}

		String result = chars.toString();
		return result;
	}
}
