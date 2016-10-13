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

public class Main {

	public static void main(String[] args) {
		Formulario formulario = new Formulario();

		formulario.addCampo(new CampoTexto("Nombre"));
		formulario.addCampo(new CampoTexto("Apellido"));
		formulario.addCampo(new CampoNumero("Tel�fono"));
		formulario.addCampo(new CampoPredefinido("Ciudad", "Santander",
				"Oviedo", "C�diz"));
		formulario.addCampo(new CampoCodigoProducto("Codigo producto"));
		formulario.addCampo(new CampoCodigoPostal("Codigo postal"));
		formulario.addCampo(new CampoEdad("Edad"));
		formulario.addCampo(new CampoSueldo("Sueldo"));
		formulario.addCampo(new CampoUbicacion("Ubicacion", "Santander","Oviedo", "C�diz"));
		formulario.addCampo(new CampoCodigoPromocion("Codigo Promocion"));

		formulario.PideDatos();
	}
}
