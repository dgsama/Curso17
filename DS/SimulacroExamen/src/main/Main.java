package main;

import io.FileManagement;

import java.io.*;
import java.util.regex.*;

public class Main {

	public static void main(String[] args) throws IOException {
		TextModeInterface userInterface = new TextModeInterface(
				new InputStreamReader(System.in));
		userInterface.execute();
	}

}
