package components;

public class GPS {

	public void navigate(String address) {
		System.out.println("GPS: Gire a la derecha. Ha llegado a '" + address
				+ "'");
	}

	public Coordenadas getCoordenadas(String direcci�n) {
		// C�lculo de relleno.
		// Devuelve la posici�n en funci�n de la primera letra
		double number = (direcci�n.toLowerCase().charAt(0) - 'a') * 10 + 10;
		return new Coordenadas(number, number);
	}

	public String getDirecci�n(Coordenadas coordenadas) {
		return coordenadas.toString();
	}
}