package model;

public class Monumento {

	private String nombre;
	private String autor;
	private String dirección;

	public Monumento(String name, String author, String address) {
		this.nombre = name;
		this.autor = author;
		this.dirección = address;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDirección() {
		return dirección;
	}

	public String getAutor() {
		return autor;
	}

}
