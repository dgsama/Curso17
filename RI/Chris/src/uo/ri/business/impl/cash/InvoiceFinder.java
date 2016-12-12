package uo.ri.business.impl.cash;

import java.util.HashMap;
import java.util.Map;

public class InvoiceFinder {

	private long idFactura;
	
	public InvoiceFinder(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Map<String, Object> execute(){
		Map<String,Object> factura = new HashMap<String,Object>();
		
		return factura;
	}
}
