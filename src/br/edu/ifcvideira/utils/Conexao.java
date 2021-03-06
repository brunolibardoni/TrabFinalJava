package br.edu.ifcvideira.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String usuario = "root";
	private final static String senha = "";
	private final static String serverName = "127.0.0.1";
	private final static String porta = "3306";
	private final static String banco = "trabalho";
	private final static String url ="jdbc:mysql://" + serverName +  "/" + banco;
	private static Connection conexao = null;
	    
	public static Connection conectar(){
		 try {
			 Class.forName(driver);
			 conexao = DriverManager.getConnection(url, usuario, senha);
			 System.out.println("Conex�o efetuada com sucesso");
	       
		 } catch (Exception ex) {
			 ex.printStackTrace();
		 }
		return conexao; 
	}

	public void fechar() {
		try {
			conexao.close();
			System.out.println("Conex�o encerrada");
		} 
	        
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
	
	

