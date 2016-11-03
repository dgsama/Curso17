package outputs;

import java.io.*;

import outputs.*;

public class Bluetooth implements Output {
	private char previous;

	public Bluetooth(String device) {
		stringWriter = new StringWriter();
		stringWriter.append("\n--- START Bluetooth[" + device + "]\n");
	}

	public void send(char c) throws IOException {
		if (!espacioRepetido(c)) {
			previous = c;
			char aux = codificar(c);
			stringWriter.append(aux);
		}
	}

	public void close() throws IOException {
		System.out.print(stringWriter.toString());
		System.out.println("--- END   Bluetooth");
	}

	private StringWriter stringWriter;

	@Override
	public char codificar(char c) {
		if (Character.isLetter(c) || Character.isDigit(c)) {
			int value = c;
			return (char) (c + 1);
		} else {
			return c;
		}
	}

	public boolean espacioRepetido(char c) {
		if (c == ' ' && previous == ' ') {
			return true;
		}
		return false;
	}
}
