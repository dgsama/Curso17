package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManagement {

	public static StringBuilder readFile(String filename) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(filename));
		String line;
		StringBuilder result = new StringBuilder();
		while ((line = input.readLine()) != null) {
			result.append(line);
		}
		input.close();
		return result;
	}

}
