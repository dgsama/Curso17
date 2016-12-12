package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.FacturarReparaciones;
import uo.ri.common.BusinessException;

public class CashServiceImpl implements CashService {

	@Override
	public Map<String, Object> facturarReparaciones(List<Long> idsAveria) {
		try {
			return new FacturarReparaciones().execute(idsAveria);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
