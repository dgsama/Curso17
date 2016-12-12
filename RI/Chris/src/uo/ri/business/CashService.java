package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface CashService {

	Map<String,Object> createInvoiceFor(List<Long> averias);
	
	Map<String,Object> findInvoice(Long idFactura);
	
	List<Map<String,Object>> findPayMethodsForInvoice(Long idFactura);
	
	void settleInvoice(Long idFactura, Map<Long,Double> datosFactura);
	
	List<Map<String,Object>> findRepairsByClient(String nombre);
	
}
