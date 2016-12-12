package uo.ri.business.impl.cash;

import java.util.Map;

public class SettleInvoice {

	private long id;
	private Map<Long,Double> datosFactura;
	
	public SettleInvoice(Long idFactura, Map<Long, Double> datos) {
		this.id = idFactura;
		this.datosFactura = datos;
	}

	public void execute(){
		
	}
}
