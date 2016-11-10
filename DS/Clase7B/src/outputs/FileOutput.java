package outputs;

import java.io.*;

import decorators.Decorator;
import outputs.*;

public class FileOutput implements Output {

	private String mensaje;
	private Decorator decorator;

	public FileOutput(String fileName, Decorator decorator) throws IOException {
		this.decorator = decorator;
		file = new FileWriter(fileName);
	}

	public void send(char c) throws IOException {
		mensaje += c;
	}

	public void close() throws IOException {
		decorator.format(mensaje);
		for (char c : mensaje.toCharArray()) {
			file.append(c);
		}
		file.close();
	}

	private FileWriter file;

}