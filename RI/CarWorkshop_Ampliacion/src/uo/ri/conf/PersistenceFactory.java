package uo.ri.conf;

import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.PedidosGateway;
import uo.ri.persistence.RepuestosGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;
import uo.ri.persistence.impl.PedidoGatewayImpl;
import uo.ri.persistence.impl.RepuestosGatewayImpl;

public class PersistenceFactory {

	public static AveriasGateway getAveriasGateway() {
		return new AveriasGatewayImpl();
	}

	public static FacturasGateway getFacturasGateway() {
		return new FacturasGatewayImpl();
	}

	public static MecanicosGateway getMecanicosGateway() {
		return new MecanicosGatewayImpl();
	}

	public static RepuestosGateway getRepuestosGateway() {
		return new RepuestosGatewayImpl();
	}

	public static PedidosGateway getPedidosGateway() {
		return new PedidoGatewayImpl();
	}

}
