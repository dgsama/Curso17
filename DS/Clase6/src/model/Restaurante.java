package model;

public class Restaurante {

	private String name;
	private String address;
	private String phone;

	public Restaurante(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getNombre() {
		return name;
	}

	public String getDirecci�n() {
		return address;
	}

	public String getTel�fono() {
		return phone;
	}

	// Marca el n�mero de tel�fono del restaurante para hacer una reserva
	public void llamar() {
		System.out.println("Llamando al " + phone);
	}
}
