package outputs;

import java.io.*;

import outputs.*;

public class FileOutput implements Output {

	private boolean encrypt;

	public FileOutput(String fileName, boolean encrypt) throws IOException {
		file = new FileWriter(fileName);
		this.encrypt = encrypt;
	}

	public void send(char c) throws IOException {
		char aux = codificar(c);
		file.append(aux);
	}

	public void close() throws IOException {
		file.close();
	}

	private FileWriter file;

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