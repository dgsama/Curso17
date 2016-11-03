package main;

import java.io.*;

import outputs.*;

public class Main {

	public static void main(String[] args) throws IOException {

		FileSystem system = new FileSystem();

		system.copy("privado.txt", new FileOutput("a.txt", false));
		system.copy("privado.txt", new FileOutput("b.txt", true));
		system.copy("privado.txt", new Internet("1.1.1.1 sin encriptar", false));
		system.copy("privado.txt", new Internet("1.1.1.1 encriptado", true));
		system.copy("privado.txt", new Bluetooth("iPhone"));
	}

}
