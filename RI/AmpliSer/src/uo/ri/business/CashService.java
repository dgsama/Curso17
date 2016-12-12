package uo.ri.business;

import java.util.Map;
import java.util.List;

public interface CashService {

	Map<String,Object> createInvoiceFor(List<Long> list);
	Map<String,Object> findInvoice(Long l);
	List<Map<String, Object>> findPayMethodsForInvoice(Long l);
	void settleInvoice(Long l, Map<Long, Double> map);
	List<Map<String, Object>> findRepairsByClient(String s);
	
}
