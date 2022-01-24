
package principal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;


public final class ConfigDAO {

	

private  String userBD;	
private  String passBD;	
private  String urlBD;		
private  String nameBD;		
	
private String nome_do_sistema = "sistema";		

private Connection conexao;




	public ConfigDAO(){
		
	this("sistema");
	}


	
	
	
	public ConfigDAO(String nome_do_sistema){
	
	this.nome_do_sistema = nome_do_sistema;
	
	this.setUserBD("");
	this.setPassBD("");
	this.setUrlBD("");
	this.setNameBD("");
	
	this.conexao = null;
	}
	
	
	

	
	public Connection getConexao(){
			
		if(this.getPreferencias()){
		
		if(this.conecta())
		return this.conexao;
		}
			
	FormDeConfiguracaoDeBD bDForm = new FormDeConfiguracaoDeBD(this);
	bDForm.mostrar();
	

	return this.conexao!=null?this.conexao:null;
	}
	
	
	
	
	
	protected boolean getPreferencias(){
		
	Preferences preferences	= null;
		
		try {
					
		preferences = Preferences.userRoot().node("com/example/app/prefs/"+this.nome_do_sistema);
					
		if(!preferences.nodeExists(""))
		return false;
				
		this.setUserBD(preferences.get("userBD", ""));
		this.setPassBD(preferences.get("passBD", ""));
		this.setUrlBD(preferences.get("urlBD", ""));
		this.setNameBD(preferences.get("nameBD", ""));
		
		
		if(userBD.length() == 0 || urlBD.length() == 0 || nameBD.length() == 0)
		return false;
			
		}
		catch (BackingStoreException e) {return false;}
		
	return true;
	}
		
		
	
	
	
	protected boolean setPreferencias(){
		
	Preferences preferences	= null;
		
		try {
				
		preferences = Preferences.userRoot().node("com/example/app/prefs/"+this.nome_do_sistema);
				
		if(!preferences.nodeExists(""))
		return false;
			
		preferences.put("urlBD", this.urlBD);
		preferences.put("userBD", this.userBD);
		preferences.put("passBD", this.passBD);
		preferences.put("nameBD", this.nameBD);	
		}
		catch (BackingStoreException e) {
			
		this.msgDeErro("Impossível acessar os registros do sistema operacional.");
		return false;
		}
	
	return true;	
	}
	
	
	
	
	
	protected boolean conecta(){
		
		try{	
			
		Class.forName("com.mysql.jdbc.Driver");		
		this.conexao = DriverManager.getConnection("jdbc:mysql://"+this.urlBD+"/"+this.nameBD+"?user="+this.userBD+"&password="+this.passBD);		
		
		if(this.conexao == null)
		return false;
		
		}
		catch(ClassNotFoundException driveNaoEncontrado ){
				
		this.msgDeErro("Drive de conexão não encontrado.");	
		return false;
		}
		catch(SQLException erroSQL){ 
				
		this.msgDeErro("Impossível se conectar a base de dados.");	
		return false;
		}

	return true;
	}
	
	
	
	
	public void setUserBD(String userBD){
		
	this.userBD = userBD;
	}
	
	
	
	
	public void setPassBD(String passBD){
		
	this.passBD = passBD;
	}
	
	
	
		
	public void setUrlBD(String urlBD){
		
	this.urlBD = urlBD;
	}
	
	
	
	
	public void setNameBD(String nameBD){
		
	this.nameBD = nameBD;
	}
		
	
	
	
	public String getUserBD(){
		
	return this.userBD;
	}
		
		
		
		
	public String getPassBD(){
			
	return this.passBD;
	}
		
		
		
		
		
	public String getUrlBD(){
			
	return this.urlBD;
	}
		
		
	
	
	public String getNameBD(){
		
	return this.nameBD;
	}

	
	

	public void msgDeErro(String msg){
		
	JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.ERROR_MESSAGE);	
	}
	
	
	
}
