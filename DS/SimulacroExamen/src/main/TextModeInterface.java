package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.regex.Pattern;

import util.Macro;
import actions.EjecutarMacro;
import io.FileManagement;

public class TextModeInterface {

	private static BufferedReader in;
	private static StringBuilder texto;
	private static FileManagement fileManagement;
	private static List<Macro> macros;

	public TextModeInterface(Reader input) {
		macros = new ArrayList<Macro>();
		in = new BufferedReader(input);
		fileManagement = new FileManagement();
		texto = new StringBuilder("");
		mostrarInfo();

	}

	private void mostrarInfo() {
		System.out.println("Acciones");
		System.out.println("--------");
		System.out.println("abre <fichero>");
		System.out
				.println("inserta <texto>\t\t// inserta las palabras al final del texto");
		System.out.println("borra\t\t\t// borra la ultima palabra");
		System.out
				.println("reemplaza <a> <b>\t// reemplaza la cadena <a> por la <b> en todo el texto");
		System.out.println("salir");
		System.out.println();
		System.out.println("Tareas");
		System.out.println("------");
		System.out
				.println("graba <macro>\t\t// comienza la grabacion de una macro");
		System.out.println("para\t\t\t// finaliza la grabacion");
		System.out
				.println("ejecuta <macro>\t\t// ejecuta la macro cuyo nombre se indique");
		System.out.println();
	}

	public void execute() throws IOException {

		do {
			System.out.print("> ");

			String[] line = in.readLine().split(" ");

			// No se comprueba que el numero de palabras sea el adecuado

			if (line[0].equals("salir"))
				return;

			if (line[0].equals("abre")) {
				texto = fileManagement.readFile(line[1]);
			} else if (line[0].startsWith("ins")) {
				for (int i = 1; i < line.length; i++) {
					texto.append(line[i] + " ");
				}
			} else if (line[0].startsWith("borr")) {
				int indexOfLastWord = texto.toString().trim().lastIndexOf(" ");
				if (indexOfLastWord == -1)
					texto = new StringBuilder("");
				else
					texto.setLength(indexOfLastWord + 1);
			} else if (line[0].startsWith("reem")) {
				texto = new StringBuilder(texto.toString().replaceAll(
						Pattern.quote(line[1]), line[2]));
			} else if (line[0].startsWith("graba")) {
				;
			} else if (line[0].startsWith("para")) {
				;
			} else if (line[0].startsWith("ejecuta")) {
				new EjecutarMacro(texto, line[1].trim().toString(), macros)
						.execute();
			} else {
				System.out.println("Instruccion desconocida");
			}

			System.out.println(texto);

		} while (true);
	}
}
