package uo.ri.business.impl.cash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentMeansForInvoiceFinder {

	private long factura;
	
	public PaymentMeansForInvoiceFinder(Long idFactura) {
		this.factura = idFactura;
	}

	public List<Map<String,Object>> execute(){
		List<Map<String,Object>> means = new ArrayList<Map<String,Object>>();
		
		return means;
	}
}
