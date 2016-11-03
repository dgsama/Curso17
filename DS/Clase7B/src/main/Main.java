package main;
import java.io.*;

import outputs.*;

public class Main {

	public static void main(String[] args) throws IOException {

		FileSystem system = new FileSystem();

		system.copy("privado.txt", new FileOutput("copia.txt"));
		system.copy("privado.txt", new Internet("1.1.1.1"));
		system.copy("privado.txt", new Bluetooth("iPhone"));
	}

}
