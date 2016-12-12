package uo.ri.conf;

import uo.ri.persistence.PedidosGateway;
import uo.ri.persistence.ProveedoresGateway;
import uo.ri.persistence.RepuestosGateway;
import uo.ri.persistence.impl.PedidosGatewayImpl;
import uo.ri.persistence.impl.ProveedoresGatewayImpl;
import uo.ri.persistence.impl.RepuestosGatewayImpl;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;

public class PersistenceFactory {
	
	public static AveriasGateway getAveriasGateway(){
		return new AveriasGatewayImpl();
	}
	
	public static FacturasGateway getFacturasGateway(){
		return new FacturasGatewayImpl();	
	}
	
	public static MecanicosGateway getMecanicosGateway(){
		return new MecanicosGatewayImpl();	
	}
	
	public static ProveedoresGateway getProveedorGateway() {
		return new ProveedoresGatewayImpl();
	}

	public static RepuestosGateway getRepuestosGateway() {
		return new RepuestosGatewayImpl();
	}

	public static PedidosGateway getPedidoGateway() {
		return new PedidosGatewayImpl();
	}
}
