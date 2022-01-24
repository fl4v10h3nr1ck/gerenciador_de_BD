
package principal;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import util.Mensagens;


public final class ConfigDAO {




private  String userBD;	
private  String passBD;	
private  String urlBD;		
private  String nameBD;	
private  int versaoBD;	
	
private String nome_do_sistema;		

private Connection conexao;

private boolean iniciar_sem_conexao;

private  final String REG_PATH = "com/msc/";


	
private GestorDeBD gestorDeBD;




	public ConfigDAO(GestorDeBD gestorDeBD, String nome_do_sistema){
	
		this(gestorDeBD, nome_do_sistema, false);
	}
		
	
	
	
	
	public ConfigDAO(GestorDeBD gestorDeBD, String nome_do_sistema, boolean iniciar_sem_conexao){
	
	this.nome_do_sistema = nome_do_sistema;
	
	this.iniciar_sem_conexao= iniciar_sem_conexao;
	
	this.gestorDeBD  = gestorDeBD;
		
	this.setUserBD("");
	this.setPassBD("");
	this.setUrlBD("");
	this.setNameBD("");
	this.setVersaoBD(0);
	
	this.conexao = null;
	}
	
	
	

	
	public Connection getConexao(){
			
		if(this.getPreferencias()){
		
			if(this.conecta())
				return this.conexao;
		}
	
		if(!iniciar_sem_conexao){	
		
			if(Mensagens.
					dialogoDeConfirmacao("Impossível acessar a base de dados. Deseja configurar as credenciais de acesso?")){
						
				FormDeConfiguracaoDeBD bDForm = new FormDeConfiguracaoDeBD(this);
				bDForm.mostrar();
			
				return this.conexao!=null?this.conexao:null;
			}
		}

	return null;
	}
	
	
	
	
	
	
	protected boolean getPreferencias(){
		
		Preferences preferences	= null;
		
		try {
					
			preferences = Preferences.userRoot().node(REG_PATH+this.nome_do_sistema);
					
			if(!preferences.nodeExists(""))
				return false;
				
			this.setUserBD(preferences.get("userbd", ""));
			this.setPassBD(preferences.get("passbd", ""));
			this.setUrlBD(preferences.get("urlbd", ""));
			this.setNameBD(preferences.get("namebd", ""));
			this.setVersaoBD(preferences.getInt("versaobd", 0));
			
			if(userBD.length() == 0 || urlBD.length() == 0 || nameBD.length() == 0)
				return false;
			
		}
		catch (BackingStoreException e) {return false;}
		
		return true;
	}
		
		
	
	

	
	protected boolean setPreferencias(){
		
		Preferences preferences	= null;
		
		try {
				
			preferences = Preferences.userRoot().node(REG_PATH+this.nome_do_sistema);
					
			if(!preferences.nodeExists(""))
				return false;
				
			preferences.put("urlbd", this.urlBD);
			preferences.put("userbd", this.userBD);
			preferences.put("passbd", this.passBD);
			preferences.put("namebd", this.nameBD);	
			preferences.putInt("versaobd", this.getVersaoBD());	
			
			
		}
		catch (BackingStoreException e) {
				
			Mensagens.msgDeErro("Impossível acessar os registros do sistema operacional.");
			return false;
		}
	
	return true;	
	}
	
	
	
	

	
	protected boolean conecta(){
		
		try{	
			
			this.conexao = DriverManager.getConnection("jdbc:mysql://"+this.urlBD, this.userBD, this.passBD);		
			
			if(this.conexao == null)
				return false;
			
	        Statement st = this.conexao.createStatement();
	        DatabaseMetaData meta = this.conexao.getMetaData();
	        ResultSet rs = meta.getCatalogs();
	        
	        boolean banco_existe = false;
	        
	        while (rs.next()) {    
	        	if(rs.getString("TABLE_CAT").compareTo(this.getNameBD())==0){
	        		banco_existe = true;
	        		System.out.println("ConfigDAO ----------- Banco de dados já existe.");
	        		break;
	        	}
	        }
	        	
	        if(!banco_existe){
	        	System.out.println("ConfigDAO ----------- Banco de dados não já existe, criando....");
	        	st.executeUpdate("CREATE DATABASE IF NOT EXISTS "+this.getNameBD()+";");
	        }
			    
	        st.executeUpdate("USE "+this.getNameBD()+";");
	        
	        if( this.gestorDeBD!=null && this.getVersaoBD()< this.gestorDeBD.getVersaoAtual()){
				
				System.out.println("Base de dados atualizada da versão "+this.getVersaoBD()+" para "+this.gestorDeBD.getVersaoAtual());
			
				if(this.gestorDeBD.atualizaBase(conexao, this.getVersaoBD())){
				
					this.setVersaoBD(this.gestorDeBD.getVersaoAtual());
					
					this.setPreferencias();
				}
			}
		
	        //this.conexao = DriverManager.getConnection("jdbc:mysql://"+this.urlBD+"/"+this.nameBD+"?user="+this.userBD+"&password="+this.passBD);		
		}
		catch(SQLException | IllegalArgumentException |  SecurityException erroSQL){
			erroSQL.printStackTrace();
			return false;
		}

		return true;
	}
	
	
	
	
	
	public void setUserBD(String userBD){this.userBD = userBD;}
	public String getUserBD(){return this.userBD;}
		
	
	public void setPassBD(String passBD){this.passBD = passBD;}
	public String getPassBD(){return this.passBD;}
	
		
	public void setUrlBD(String urlBD){this.urlBD = urlBD;}
	public String getUrlBD(){return this.urlBD;}
		

	public void setNameBD(String nameBD){this.nameBD = nameBD;}
	public String getNameBD(){return this.nameBD;}


	public int getVersaoBD() {return versaoBD;}
	public void setVersaoBD(int versaoBD) {this.versaoBD = versaoBD;}

	
	
	
		
		
	
	
	
	
}
