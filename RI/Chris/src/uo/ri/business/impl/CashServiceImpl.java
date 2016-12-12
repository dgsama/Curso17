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

public class CashServiceImpl implements CashService{

	@Override
	public Map<String, Object> createInvoiceFor(List<Long> averias) {
		try {
			return new CreateInvoiceFor(averias).execute();
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> findInvoice(Long idFactura) {
		return new InvoiceFinder(idFactura).execute();
	}

	@Override
	public List<Map<String, Object>> findPayMethodsForInvoice(Long idFactura) {
		return new PaymentMeansForInvoiceFinder(idFactura).execute();
	}

	@Override
	public List<Map<String, Object>> findRepairsByClient(String nombre) {
		return new RepairsByClientFinder(nombre).execute();
	}

	@Override
	public void settleInvoice(Long idFactura, Map<Long, Double> datosFactura) {
		new SettleInvoice(idFactura,datosFactura).execute();		
	}

}
