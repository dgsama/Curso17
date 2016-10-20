package formulario;

import comprobaciones.ComprobacionCodigoPostal;
import comprobaciones.ComprobacionCodigoProducto;
import comprobaciones.ComprobacionCodigoPromocion;
import comprobaciones.ComprobacionEdad;
import comprobaciones.ComprobacionNumero;
import comprobaciones.ComprobacionPredefinido;
import comprobaciones.ComprobacionSueldo;
import comprobaciones.ComprobacionTexto;
import comprobaciones.composite.ComprobacionAlguna;

public class Main {

	public static void main(String[] args) {
		Formulario formulario = new Formulario();

		formulario.addCampo(new Campo("Nombre", new ComprobacionTexto()));
		formulario.addCampo(new Campo("Apellido", new ComprobacionTexto()));
		formulario.addCampo(new Campo("Telefono", new ComprobacionNumero()));

		formulario.addCampo(new Campo("Ciudad", new ComprobacionPredefinido(
				"Santander", "Oviedo", "Cadiz")));

		formulario.addCampo(new Campo("Codigo producto",
				new ComprobacionCodigoProducto()));

		formulario.addCampo(new Campo("Codigo postal",
				new ComprobacionCodigoPostal()));

		formulario.addCampo(new Campo("Edad", new ComprobacionEdad()));

		formulario.addCampo(new Campo("Sueldo", new ComprobacionSueldo()));

		formulario.addCampo(new Campo("Ubicacion", new ComprobacionAlguna(
				new ComprobacionPredefinido("Santander", "Oviedo", "Cadiz"),
				new ComprobacionCodigoPostal())));

		formulario.addCampo(new Campo("Codigo Promocion",
				new ComprobacionAlguna(new ComprobacionTexto(),
						new ComprobacionCodigoPromocion())));

		formulario.PideDatos();
	}
}
