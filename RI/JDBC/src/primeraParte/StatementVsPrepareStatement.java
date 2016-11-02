package primeraParte;

import java.sql.*;

public class StatementVsPrepareStatement {

	private static Connection con;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static long tInicio = 0, tFin = 0, tTotal = 0;
	private static int numeroVehiculos;
	private static int iteraciones = 100;

	// Base de datos Hsql
	private static String URL_HSQL = "jdbc:hsqldb:hsql://localhost";
	private static String USER_HSQL = "SA";
	private static String PASS_HSQL = "";

	// Base de datos Oracle
	private static String URL_ORACLE = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private static String USER_ORACLE = "UO237464";
	private static String PASS_ORACLE = "UO237464";

	public static void main(String[] args) {
		try {
			conFuera();
			conDentro();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static Connection getConnectionHsql() {
		con = null;
		try {
			con = DriverManager.getConnection(URL_HSQL, USER_HSQL, PASS_HSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	private static Connection getConnectionOracle() {
		con = null;
		try {
			con = DriverManager.getConnection(URL_ORACLE, USER_ORACLE,
					PASS_ORACLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	private static void conDentro() throws SQLException {
		System.out.println("\n dentro \n");
		withHQSL('d');
		withOracle('d');
	}

	private static void conFuera() throws SQLException {
		System.out.println("\n fuera \n");
		withHQSL('f');
		withOracle('f');
	}

	private static void withHQSL(char sitio) throws SQLException {
		System.out.println("\nComienza el programa. DB: " + URL_HSQL);
		if (sitio == 'f') {
			statementConFuera('h');
			prepareStatementConFuera('h');
		} else if (sitio == 'd') {
			statementConDentro('h');
			prepareStatementConDentro('h');
		}
	}

	private static void withOracle(char sitio) throws SQLException {
		System.out.println("\nComienza el programa. DB: " + URL_ORACLE);
		if (sitio == 'f') {
			statementConFuera('o');
			prepareStatementConFuera('o');
		} else if (sitio == 'd') {
			statementConDentro('o');
			prepareStatementConDentro('o');
		}
	}

	private static void statementConFuera(char conex) throws SQLException {
		tInicio = System.currentTimeMillis();
		if (conex == 'h') {
			con = getConnectionHsql();
		} else if (conex == 'o') {
			con = getConnectionOracle();
		}

		for (int i = 0; i < iteraciones; i++) {
			st = con.createStatement();
			rs = st.executeQuery("SELECT count (*) FROM TVEHICULOS WHERE ID = "
					+ i);
			rs.next();
			numeroVehiculos += rs.getInt(1);
			rs.close();
			st.close();
		}

		tFin = System.currentTimeMillis();
		tTotal = tFin - tInicio;

		System.out.println("Con Statement se han contado " + numeroVehiculos
				+ " en un tiempo de -->" + tTotal);

		numeroVehiculos = 0;
		rs.close();
		st.close();
		con.close();

	}

	private static void prepareStatementConFuera(char conex)
			throws SQLException {
		tInicio = System.currentTimeMillis();
		if (conex == 'h') {
			con = getConnectionHsql();
		} else if (conex == 'o') {
			con = getConnectionOracle();
		}

		ps = con.prepareStatement("SELECT count(*) FROM TVEHICULOS v WHERE v.ID = ?");
		for (int i = 0; i < iteraciones; i++) {
			ps.setInt(1, i);
			rs = ps.executeQuery();
			rs.next();
			numeroVehiculos += rs.getInt(1);
		}

		tFin = System.currentTimeMillis();
		tTotal = tFin - tInicio;

		System.out.println("Con PrepareStatement se han contado "
				+ numeroVehiculos + " en un tiempo de --> " + tTotal);

		numeroVehiculos = 0;
		rs.close();
		st.close();
		con.close();
	}

	private static void statementConDentro(char conex) throws SQLException {

		tInicio = System.currentTimeMillis();

		for (int i = 0; i < iteraciones; i++) {
			if (conex == 'h') {
				con = getConnectionHsql();
			} else if (conex == 'o') {
				con = getConnectionOracle();
			}
			st = con.createStatement();
			rs = st.executeQuery("SELECT count (*) FROM TVEHICULOS WHERE ID = "
					+ i);
			rs.next();
			numeroVehiculos += rs.getInt(1);
			rs.close();
			st.close();
		}

		tFin = System.currentTimeMillis();
		tTotal = tFin - tInicio;

		System.out.println("Con Statement se han contado " + numeroVehiculos
				+ " en un tiempo de -->" + tTotal);

		numeroVehiculos = 0;
		rs.close();
		st.close();
		con.close();

	}

	private static void prepareStatementConDentro(char conex)
			throws SQLException {

		tInicio = System.currentTimeMillis();

		for (int i = 0; i < iteraciones; i++) {
			if (conex == 'h') {
				con = getConnectionHsql();
			} else if (conex == 'o') {
				con = getConnectionOracle();
			}
			ps = con.prepareStatement("SELECT count(*) FROM TVEHICULOS v WHERE v.ID = ?");
			ps.setInt(1, i);
			rs = ps.executeQuery();
			rs.next();
			numeroVehiculos += rs.getInt(1);
		}

		tFin = System.currentTimeMillis();
		tTotal = tFin - tInicio;

		System.out.println("Con PrepareStatement se han contado "
				+ numeroVehiculos + " en un tiempo de --> " + tTotal);

		numeroVehiculos = 0;
		rs.close();
		st.close();
		con.close();
	}

}