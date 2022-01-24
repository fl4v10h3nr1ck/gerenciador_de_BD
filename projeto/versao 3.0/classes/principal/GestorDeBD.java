package principal;

import java.sql.Connection;

public interface GestorDeBD {

	
	String[] getListaDeCriacaoDeTabelas();
	
		
	int getVersaoAtual();
	
	
	boolean atualizaBase(Connection conexao, int versao_antiga);
	
	
}
