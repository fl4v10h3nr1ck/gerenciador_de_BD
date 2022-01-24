package teste;

import java.util.Date;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import anotacoes.Anot_TB_Coluna;
import anotacoes.Anot_TB_Coluna_Selecao;



@Anot_BD_Tabela(nome="clientes", prefixo="cl")
public class Cliente {
	
@Anot_TB_Coluna_Selecao(posicao=0, comprimento=20)
@Anot_TB_Coluna(posicao=0, rotulo="Código", comprimento = 15)	
@Anot_BD_Campo(nome = "id_cliente", tipo=int.class, set = "setId_cliente", get = "getId_cliente", getTab="getId_clienteTab", ehId=true)				
private int id_cliente;

@Anot_TB_Coluna_Selecao(posicao=1, comprimento=80)
@Anot_TB_Coluna(posicao=1, rotulo="Nome", comprimento = 50)
@Anot_BD_Campo(nome = "nome", set = "setNome", get = "getNome")				
private String nome;

@Anot_TB_Coluna(posicao=2, rotulo="Sexo", comprimento = 10)	
@Anot_BD_Campo(nome = "sexo", set = "setSexo", get = "getSexo")
private String sexo;

@Anot_BD_Campo(nome = "cpf", set = "setCpf", get = "getCpf")
private String cpf;

@Anot_BD_Campo(nome = "rg", set = "setRg", get = "getRg")
private String rg;

@Anot_TB_Coluna(posicao=3, rotulo="Nasc.", comprimento = 10)	
@Anot_BD_Campo(nome = "nascimento", tipo=Date.class, set = "setNascimento", get = "getNascimento", getTab="getNascimentoTab")
private Date nascimento;

@Anot_TB_Coluna(posicao=4, rotulo="TEL", comprimento = 15)	
@Anot_BD_Campo(nome = "tel", set = "setTel", get = "getTel")
private String tel;

@Anot_BD_Campo(nome = "cel", set = "setCel", get = "getCel")
private String cel;

@Anot_BD_Campo(nome = "data_cadastro", tipo=Date.class, set = "setData_cadastro", get = "getData_cadastro")
private Date data_cadastro;

@Anot_BD_Campo(nome = "status", set = "setStatus", get = "getStatus")
private String status;

@Anot_BD_Campo(nome = "fk_convenio", tipo=int.class, set = "setFk_convenio", get = "getFk_convenio")
private int fk_convenio;

@Anot_BD_Campo(nome = "fk_posto", tipo=int.class, set = "setFk_posto", get = "getFk_posto")
private int fk_posto;	

@Anot_BD_Campo(nome = "nome_pai", set = "setNome_pai", get = "getNome_pai")	
private String nome_pai;

@Anot_BD_Campo(nome = "nome_mae", set = "setNome_mae", get = "getNome_mae")
private String nome_mae;

@Anot_BD_Campo(nome = "estado_civil", set = "setEstado_civil", get = "getEstado_civil")
private String estado_civil;

@Anot_BD_Campo(nome = "diabetico", set = "setDiabetico", get = "getDiabetico")
private String diabetico;

@Anot_BD_Campo(nome = "email", set = "setEmail", get = "getEmail")
private String email;

@Anot_BD_Campo(nome = "matricula_convenio", set = "setMatricula_convenio", get = "getMatricula_convenio")
private String matricula_convenio;

@Anot_BD_Campo(nome = "fk_endereco", tipo=int.class, set = "setFk_endereco", get = "getFk_endereco")
private int fk_endereco;




public int getId_cliente() {	return id_cliente;}
public void setId_cliente(int id_cliente) {	this.id_cliente = id_cliente;}
public String getId_clienteTab(){return "";}


public String getNome() {	return nome;}
public void setNome(String nome) {	this.nome = nome;}

public String getSexo() {	return sexo;}
public void setSexo(String sexo) {	this.sexo = sexo;}

public String getCpf() {	return cpf;}
public void setCpf(String cpf) {	this.cpf = cpf;}

public String getRg() {	return rg;}
public void setRg(String rg) {this.rg = rg;}

public Date getNascimento() {	return nascimento;}
public void setNascimento(Date nascimento) {	this.nascimento = nascimento;}
public String getNascimentoTab() {	return "";}

public String getTel() {	return tel;}
public void setTel(String tel) {	this.tel = tel;}

public Date getData_cadastro() {	return data_cadastro;}
public void setData_cadastro(Date data_cadastro) {	this.data_cadastro = data_cadastro;}

public String getCel() {	return cel;}
public void setCel(String cel) {	this.cel = cel;}

public int getFk_convenio() {	return fk_convenio;}
public void setFk_convenio(int fk_convenio) {	this.fk_convenio = fk_convenio;}

public int getFk_posto() {	return fk_posto;}
public void setFk_posto(int fk_posto) {	this.fk_posto = fk_posto;}

public String getNome_pai() {	return nome_pai;}
public void setNome_pai(String nome_pai) {	this.nome_pai = nome_pai;}

public String getNome_mae() {	return nome_mae;}
public void setNome_mae(String nome_mae) {	this.nome_mae = nome_mae;}

public String getEstado_civil() {	return estado_civil;}
public void setEstado_civil(String estado_civil) {	this.estado_civil = estado_civil;}

public String getDiabetico() {	return diabetico;}
public void setDiabetico(String diabetico) {	this.diabetico = diabetico;}

public String getEmail() {	return email;}
public void setEmail(String email) {	this.email = email;}

public String getMatricula_convenio() {	return matricula_convenio;}
public void setMatricula_convenio(String matricula_convenio) {	this.matricula_convenio = matricula_convenio;}

public int getFk_endereco() {	return fk_endereco;}
public void setFk_endereco(int fk_endereco) {	this.fk_endereco = fk_endereco;}

public String getStatus() {	return status;}
public void setStatus(String status) {	this.status = status;}

	
	
	
	
	
}
