package teste;

import java.sql.Connection;

import principal.ConfigDAO;
import principal.DAOBase;

public class Main {

	
	
	public static void main(String[] args){
	
		
	Main main = new Main();
	main.processa();
	}
	
	
	
	
	
	public void processa(){
		
		ConfigDAO dao = new ConfigDAO("gerenciador_bd");	
		
		Connection 	conection  = dao.getConexao();
			
		if(conection==null){
		System.out.println("erro");
		return;
		}
		
	Cliente cliente = new DAO<Cliente>(Cliente.class, conection).get(1);
		
		
	System.out.println("sucesso "+(cliente!=null? cliente.getNome():""));	
	}
	
	
	
	
	
	
	
	
	public class DAO<T> extends DAOBase<T>{

		
		public DAO(Class<T> tipo, Connection conector) {
			
		super(tipo, conector, true);
		
		}
		
		
		
		
		
	}
	
	
	
}
