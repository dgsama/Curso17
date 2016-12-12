package uo.ri.business.conf;

import uo.ri.persistence.*;
import uo.ri.persistence.impl.*;


public class PersistenceFactory {

	public static AveriasGateway getAveriaGateway(){
		return new AveriasGatewayImpl();
	}
	
	public static MecanicosGateway getMecanicoGateway(){
		return new MecanicosGatewayImpl();
	}
	
	public static FacturasGateway getFacturaGateway(){
		return new FacturasGatewayImpl();
	}
	
	public static MediosPagoGateway getMediosPagoGateway(){
		return new MediosPagoGatewayImpl();
	}
	
	public static CargosGateway getCargosGateway(){
		return new CargosGatewayImpl();
	}

	public static ProveedoresGateway getProveedorGateway() {
		return new ProveedoresGatewayImpl();
	}
}
