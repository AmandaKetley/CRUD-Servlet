package br.com.pucgo.Cadastro_Pessoa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	public BaseDao() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/cadastro_pessoa";
		
		Connection conn = DriverManager.getConnection(url, "root", "root");
		
		return conn;
	}
	
	public static void main(String[] args) throws SQLException{
		BaseDao bd = new BaseDao();
		Connection conn = bd.getConnection();
		
		if(conn != null)
			System.out.println("Conectou :)");
		else
			System.out.println("Não Conectou :(");
	}
}

