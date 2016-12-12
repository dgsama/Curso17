package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.CreateInvoiceFor;
import uo.ri.business.impl.cash.InvoiceFinder;
import uo.ri.business.impl.cash.PaymentMeansForInvoiceFinder;
import uo.ri.business.impl.cash.RepairsByClientFinder;
import uo.ri.business.impl.cash.SettleInvoice;
import uo.ri.common.BusinessException;

public class CashsServiceImpl implements CashService {

	@Override
	public Map<String, Object> createInvoiceFor(List<Long> list) {
		CreateInvoiceFor cif = new CreateInvoiceFor(list);
		return cif.execute();
	}

	@Override
	public Map<String, Object> findInvoice(Long l) {
		InvoiceFinder inf = new InvoiceFinder(l);
		return inf.execute();
		
	}

	@Override
	public List<Map<String, Object>> findPayMethodsForInvoice(Long l) {
		PaymentMeansForInvoiceFinder pmfif = new PaymentMeansForInvoiceFinder(l);
		return pmfif.execute();
	}

	@Override
	public void settleInvoice(Long l, Map<Long, Double> map) {
		SettleInvoice si = new SettleInvoice(l, map);
		si.execute();
		
	}

	@Override
	public List<Map<String, Object>> findRepairsByClient(String s) {
		RepairsByClientFinder rcf = new RepairsByClientFinder(s);
		return rcf.execute();
	}
		
}
