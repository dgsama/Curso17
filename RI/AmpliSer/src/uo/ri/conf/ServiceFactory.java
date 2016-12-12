package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashsServiceImpl;

public class ServiceFactory {
	
	public ServiceFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static AdminService getAdminService(){
		AdminService as = new AdminServiceImpl();
		return as;
	}
	
	public static CashService getCashService(){
		CashService cs = new CashsServiceImpl();
		return cs;
	}

}
