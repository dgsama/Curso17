package model;

public class Monumento {

	private String nombre;
	private String autor;
	private String direcci�n;

	public Monumento(String name, String author, String address) {
		this.nombre = name;
		this.autor = author;
		this.direcci�n = address;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDirecci�n() {
		return direcci�n;
	}

	public String getAutor() {
		return autor;
	}

}
