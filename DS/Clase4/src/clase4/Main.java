package clase4;

import campos.CampoCodigoPostal;
import campos.CampoCodigoProducto;
import campos.CampoCodigoPromocion;
import campos.CampoEdad;
import campos.CampoNumero;
import campos.CampoPredefinido;
import campos.CampoSueldo;
import campos.CampoTexto;
import campos.CampoUbicacion;
import comprobaciones.ComprobacionCodigoPostal;
import comprobaciones.ComprobacionCodigoProducto;
import comprobaciones.ComprobacionCodigoPromocion;
import comprobaciones.ComprobacionEdad;
import comprobaciones.ComprobacionNumero;
import comprobaciones.ComprobacionPredefinido;
import comprobaciones.ComprobacionSueldo;
import comprobaciones.ComprobacionTexto;

public class Main {

	public static void main(String[] args) {
		Formulario formulario = new Formulario();

		formulario.addCampo(new CampoTexto("Nombre", new ComprobacionTexto()));
		formulario.addCampo(new CampoTexto("Apellido", new ComprobacionTexto()));
		formulario.addCampo(new CampoNumero("Tel�fono", new ComprobacionNumero()));
		formulario
				.addCampo(new CampoPredefinido("Ciudad", new ComprobacionPredefinido("Santander", "Oviedo", "C�diz")));
		formulario.addCampo(new CampoCodigoProducto("Codigo producto", new ComprobacionCodigoProducto()));
		formulario.addCampo(new CampoCodigoPostal("Codigo postal", new ComprobacionCodigoPostal()));
		formulario.addCampo(new CampoEdad("Edad", new ComprobacionEdad()));
		formulario.addCampo(new CampoSueldo("Sueldo", new ComprobacionSueldo()));
		formulario
				.addCampo(new CampoUbicacion("Ubicacion", new ComprobacionPredefinido("Santander", "Oviedo", "C�diz")));
		formulario.addCampo(new CampoCodigoPromocion("Codigo Promocion", new ComprobacionTexto(),
				new ComprobacionCodigoPromocion()));

		formulario.PideDatos();
	}
}