package main;

import java.io.*;

import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

import decorators.Encrypter;
import decorators.Normalizer;
import decorators.Spaces;
import outputs.*;

public class Main {

	public static void main(String[] args) throws IOException {

		FileSystem system = new FileSystem();

		system.copy("privado.txt", new FileOutput("a.txt", new Spaces(
				new Normalizer(new Encrypter(null)))));
		system.copy("privado.txt", new Internet("1.1.1.1", new Spaces(
				new Normalizer(new Encrypter(null)))));
		system.copy("privado.txt", new Bluetooth("iPhone", new Spaces(
				new Normalizer(new Encrypter(null)))));
	}

}