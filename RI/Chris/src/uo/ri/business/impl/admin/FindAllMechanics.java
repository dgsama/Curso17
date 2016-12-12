package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class FindAllMechanics {
		
	public List<Map<String,Object>> execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			MecanicosGateway mGate = PersistenceFactory.getMecanicoGateway();
			
			mGate.setConnection(c);
			
			return mGate.findAll();
			
//			pst = c.prepareStatement(Settings.get("SQL_FIND_ALL_MECHANICS"));
//			
//			rs = pst.executeQuery();
//			while(rs.next()) {
//				mecánico = new HashMap<String,Object>();
//					mecánico.put("id",rs.getLong(1));
//					mecánico.put("nombre",rs.getString(2)); 
//					mecánico.put("apellidos",rs.getString(3));
//				mechanics.add(mecánico);
//			}
			} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}

}
