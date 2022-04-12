package br.com.empresa.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacoes")//NOVO NOME DA TABELA A SER CRIADA
public class Avaliacao implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4420697889536342298L;

	@EmbeddedId // INSERI OS ATRIBUTOS DAS CLASSE MENCIONADA ABAIXO AUTOMATICAMENTE
	private AlunoDisciplina alunoDisciplina;
	
	//IDALUNO 
	//IDDISCIPLINA
	private String conceito;

	
	
	

	
	//GETTER E SETTER ALUNODISCIPLINA
	public AlunoDisciplina getAlunoDisciplina() {
		return alunoDisciplina;
	}

	public void setAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		this.alunoDisciplina = alunoDisciplina;
	}
	
	
	
	

	//GETTER E SETTER CONCEITO

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	
	

}
