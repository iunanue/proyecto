package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	private Connection con;

	private String server = "localhost";
	private String database = "proyecto";
	private String userName = "root";
	private String pass = "";

	private Dao dao;

	public Connect() {

		dao = new Dao(this);
	}

//	public Connection getConnection() throws SQLException {
//
//		if (con == null) {
//			try {
//				Class.forName("org.gjt.mm.mysql.Driver");
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			this.con = DriverManager.getConnection("jdbc:mysql://" + this.server + "/" + this.database, this.userName,
//					this.pass);
//		}
//		return con;
//	}

	public Dao getDao() {
		return this.dao;
	}

//	public String getMessage(Exception e) {
//		if (e.getMessage().indexOf("Duplicate entry") != -1) {
//			return "Ya existe una entrada con este valor.";
//		} else if (e.getMessage().indexOf("a foreign key constraint fails") != -1) {
//			return "Se ha producido un error interno.";
//		} else {
//			return e.getMessage();
//		}
//	}
//
//	public void close() throws SQLException {
//
//		this.getConnection().close();
////		this.dao = null;
//
//	}

}
