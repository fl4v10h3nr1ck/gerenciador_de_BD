package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import util.Mensagens;
import CamposDeTexto.campos.CampoLimitado;
import CamposDeTexto.formatadores.Format_TextField_MaxLength;





public class FormDeConfiguracaoDeBD extends JDialog {


private static final long serialVersionUID = 1L;

	
	
private CampoLimitado tf_url;
private CampoLimitado tf_user;
private JPasswordField tf_pass;
private CampoLimitado tf_nomebd;


private ConfigDAO dao;



	public FormDeConfiguracaoDeBD( ConfigDAO dao){
	
	
	super();
	
	this.setTitle("Utilitário de configuração de base de dados");
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(500, 390);
	this.setLocationRelativeTo(null);
	this.setLayout(new GridBagLayout());
	this.setModal(true);
	this.getContentPane().setBackground(Color.white);  

	this.dao = dao;
	adicionarComponentes();
	}
	
	
	
	
	
	public void adicionarComponentes(){

		
	GridBagConstraints cons = new GridBagConstraints();  
	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth = 1;
	cons.weightx = 0.7;
	cons.weighty =1;
	JPanel backgroundPane = new JPanel(){

		private static final long serialVersionUID = 1L;

			@Override
			public final void paintComponent( Graphics g){	
			    	    	
			    	 	    
			super.paintComponents(g);	
			    	    		
			Graphics2D g2 = (Graphics2D) g.create();	
			 
			g2.setColor(Color.WHITE );
			g2.fillRect(  0, 0,  this.getWidth(),  this.getHeight()  );
			
			try{ g2.drawImage( ImageIO.read( getClass().getResource("/icons/icon_bd.png" ) ), 10, this.getHeight() - 120, this  );}catch (IOException e) {}
		    	  	
			} 	
		};
	backgroundPane.setLayout(new GridBagLayout());
	this.add(backgroundPane, cons);	
	
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weightx = 0.3;
	JPanel panel2 = new JPanel();
	panel2.setLayout(new GridBagLayout());
	panel2.setBackground(new Color(221, 221, 221));
	this.add(panel2, cons);	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weightx = 1;
	cons.weighty =0;
	cons.insets = new Insets(20, 5, 0, 10);
	panel2.add(new JLabel("<html><font color=red><b>Atenção:</b> somente altere os dados abaixo se</font></html>"), cons);
	cons.insets = new Insets(0, 5, 0, 10);
	panel2.add(new JLabel("<html><font color=red>tiver certeza que as credenciais estão corretas.</font></html>"), cons);
	panel2.add(new JLabel("<html><font color=red>Dúvidas, entre em contato com o desenvolvedor.</font></html>"), cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weightx = 1;
	cons.weighty =0;
	cons.insets = new Insets(20, 5, 0, 10);
	panel2.add(new JLabel("<html>URL:<font color=red>*</font></html>"), cons);
	cons.insets = new Insets(2, 5, 0, 10);
	
	cons.insets = new Insets(8, 5, 0, 10);
	tf_url = new CampoLimitado(150, this.dao.getUrlBD().length() == 0? "localhost": this.dao.getUrlBD());
	cons.insets = new Insets(2, 5, 0, 10);
	panel2.add(tf_url, cons);
	
	cons.insets = new Insets(8, 5, 0, 10);
	panel2.add(new JLabel("<html>Usuário:<font color=red>*</font></html>"), cons);
	cons.insets = new Insets(2, 5, 0, 10);
	tf_user = new CampoLimitado(100, this.dao.getUserBD().length() == 0? "root": this.dao.getUserBD());
	tf_user.setText(this.dao.getUserBD().length() == 0? "root": this.dao.getUserBD());
	panel2.add(tf_user, cons);
	
	cons.insets = new Insets(8, 5, 0, 10);
	panel2.add(new JLabel("Senha:"), cons);
	cons.insets = new Insets(2, 5, 0, 10);
	tf_pass = new JPasswordField();
	tf_pass.setDocument( new Format_TextField_MaxLength( 100 ,  tf_pass )  );
	tf_pass.setText(this.dao.getPassBD().length() == 0? "": this.dao.getPassBD());
	panel2.add(tf_pass, cons);
	

	cons.insets = new Insets(8, 5, 0, 10);
	panel2.add(new JLabel("Nome do BD:"), cons);
	cons.insets = new Insets(2, 5, 0, 10);
	tf_nomebd = new CampoLimitado(100, this.dao.getNameBD().length() == 0? "": this.dao.getNameBD());
	panel2.add(tf_nomebd, cons);
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.anchor = GridBagConstraints.EAST;
	cons.weightx = 0;
	cons.weighty =0;
	cons.insets = new Insets(10, 20, 0, 15);
	JButton bt_salvar = new JButton("Salvar e Conectar");
	panel2.add(bt_salvar, cons);

		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		    	
		if(acaoPrincipal())
		dispose();
			
		}});
	this.getRootPane().setDefaultButton(bt_salvar);


	cons.fill = GridBagConstraints.BOTH;
	cons.weighty =1;
	cons.weightx = 1;
	cons.insets = new Insets(0, 0, 0, 0);
	panel2.add(new JLabel(""), cons);
	}
	
	
	
	

	public boolean acaoPrincipal(){
	
		if(this.tf_url.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe a URL do banco de dados.");
		return false;	
		}
				
		if(this.tf_user.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o usuário do banco de dados.");
		return false;	
		}
		
		
		if(this.tf_nomebd.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o nome do banco de dados.");
		return false;	
		}
		
				
			
		this.dao.setUserBD(this.tf_user.getText());
		this.dao.setPassBD(String.valueOf(this.tf_pass.getPassword()));
		this.dao.setUrlBD(this.tf_url.getText());
		this.dao.setNameBD(this.tf_nomebd.getText());
		this.dao.setVersaoBD(0);
		
		
		if(this.dao.conecta()){	
		
			this.dao.setPreferencias();	
			return true;
		}
	
	
	return false;
	}





	
	
	public void mostrar(){
		
	this.setVisible(true);
	}
	
	
	
	

	
	
}
