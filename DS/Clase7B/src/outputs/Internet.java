package outputs;

import java.io.*;

import outputs.*;

public class Internet implements Output {

	private boolean encrypt;

	public Internet(String url, boolean encrypt) {
		this.encrypt = encrypt;
		stringWriter = new StringWriter();
		stringWriter.append("\n--- START Internet[" + url + "]\n");
	}

	public void send(char c) throws IOException {
		char aux = codificar(c);
		stringWriter.append(aux);
	}

	public void close() throws IOException {
		System.out.print(stringWriter.toString());
		System.out.println("--- END   Internet");
	}

	private StringWriter stringWriter = new StringWriter();

	@Override
	public char codificar(char c) {
		if (encrypt == true) {
			if (Character.isLetter(c) || Character.isDigit(c)) {
				int value = c;
				return (char) (c + 1);
			}
		}
		return c;

	}
}