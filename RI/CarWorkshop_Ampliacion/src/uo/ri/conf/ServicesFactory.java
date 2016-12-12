package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;

public class ServicesFactory {

	public ServicesFactory() {
	}

	public static AdminService getAdminService() {
		AdminService as = new AdminServiceImpl();
		return as;
	}

	public static CashService getCashService() {
		CashService cs = new CashServiceImpl();
		return cs;
	}

}