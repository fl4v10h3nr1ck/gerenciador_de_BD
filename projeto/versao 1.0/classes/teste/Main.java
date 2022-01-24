package teste;

import java.sql.Connection;

import principal.ConfigDAO;

public class Main {

	
	
	public static void main(String[] args){
		
		
	ConfigDAO dao = new ConfigDAO("DiaLab");	
		
	Connection 	conection  = dao.getConexao();
		
	if(conection==null){
	System.out.println("erro");
	return;
	}
	
	System.out.println("sucesso");
	}
	
	
	
	
}
