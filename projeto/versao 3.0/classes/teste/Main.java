package teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import principal.ConfigDAO;
import principal.DAOBase;
import principal.GestorDeBD;

public class Main {

	
	
	public static void main(String[] args){
	
		
	Main main = new Main();
	main.processa();
	}
	
	
	
	
	
	public void processa(){
		
		ConfigDAO dao = new ConfigDAO(new gestor(), "gerenciador_bd");	
		
		Connection 	conection  = dao.getConexao();
			
		if(conection==null){
		System.out.println("erro");
		return;
		}
	}
	
	
	
	
	
	
	
	
	public class DAO<T> extends DAOBase<T>{

		
		public DAO(Class<T> tipo, Connection conector) {
			
		super(tipo, conector, true);
		
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	public class gestor implements GestorDeBD{

		@Override
		public String[] getListaDeCriacaoDeTabelas() {
		
			return new String[]{
					"CREATE TABLE  IF NOT EXISTS usuarios (" +
							"id  INT NOT NULL AUTO_INCREMENT, " +
							"email  VARCHAR(150) NULL, " +
							"nome_completo  VARCHAR(150) NULL,"+ 
							" PRIMARY KEY(id))ENGINE = InnoDB;",


					"CREATE TABLE  IF NOT EXISTS categorias (" +
		                    "id_cat  INT NOT NULL AUTO_INCREMENT, " +
		                    "nome  VARCHAR(150) NULL, " +
		                    "fk_usuario INT NULL," + 
		                    "PRIMARY KEY (id_cat)," + 
		                    "INDEX fk_usuario_idx (fk_usuario ASC), "+
		                    "CONSTRAINT fk_usuario FOREIGN KEY (fk_usuario) REFERENCES usuarios(id) ON DELETE NO ACTION ON UPDATE NO ACTION)ENGINE = InnoDB;"};
		}

		@Override
		public int getVersaoAtual() {
			
			System.out.println("pediu versao");
			return 5;
		}

		
		
		@Override
		public boolean atualizaBase(Connection conexao, int versao_antiga) {
			
			System.out.println("atualizando base...");
			
			try{	
			
				Statement st = conexao.createStatement();
			    
				String[] tabelas = getListaDeCriacaoDeTabelas();
				
				if(tabelas!=null && tabelas.length>0){
					for(String query: tabelas)
						st.executeUpdate(query);
				}
			}
			catch(SQLException erroSQL){
				erroSQL.printStackTrace();
				return false;
			}
		
		return true;
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
