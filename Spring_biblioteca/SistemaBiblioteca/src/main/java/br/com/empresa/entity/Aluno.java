//CAMADA DE ENTITY



package br.com.empresa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity // PARA INFORMAR AO SPRING QUE ESSA É UMA CLASSE DE DOMINIO
public class Aluno {
	
	
 @Id	// PARA INFORMAR AO SPRING QUE ESSE É UMA CHAVE PRIMARIA / CRLT + SPACE E O SPRING IMPORTA
 @GeneratedValue(strategy = GenerationType.IDENTITY)    //sequence É INDICAR POR QUAL SEQUENCIA QUE IRA INICIAR A CHAVE PRIMARIA ( INICIAR EM 1-  IDENTITY AUTO - BANCO DE DADOS RESOLVE ISSO, OU INICIAR EM 100? SEQUENCE)
  private Integer id;
 
 private String nome;
 
 
 
 

 //---------------------------------------------------------RELACIONAMENTOS------------------------------------------ 
 
 // -------------------------------- NÃO ESQUECER DE CRIAR OS GETTERS E SETTER DOS RELACIONAMENTOS-------------------------------
 
 
 //EU TENHO VARIOS ALUNOS PODEM ESTAR SOMENTE A UNICA TURMA
 @ManyToOne// UM ALUNO PODE ESTAR SOMENTE EM UMA TURMA
 @JoinColumn(name="id_turma")// QUANDO FOR GRAVAR A TURMA , NECESSÁRIO INFORMAR A TURMA DELE, VOU DIZER ISSO ATRAVES DA COLUNA
 private Turma turma;
 
 //-------------------VARIOS ALUNOS PODEM ESTAR MATRICULADOS EM VARIAS  DISCIPLINAS E VARIAS DISCIPLINAS PODEM TER MATRICULADO VARIOS ALUNOS------------------------------------------
 
 
 @ManyToMany
 @JoinTable(name= "matricula", //CRIANDO UMA TABELA DE  MATRICULA
 joinColumns = {@JoinColumn(name="id_aluuno" )}, //COLUNAS QUE ESTÃO SE REFERENCIANDO A TABELA  ALUNO (A CHAVE PRIMARIA DE ALUNO SERÁ GRAVA NA TABELA DE MATRICULA)
 inverseJoinColumns = {@JoinColumn(name = "id_disciplina")}) //COLUNAS QUE ESTÃO SE REFERENCIANDO A TABELA DISCIPLINA (A CHAVE PRIMARIA DE DISCIPLINA SERÁ GRAVA NA TABELA DE MATRICULA)
 private List<Disciplina> disciplinas; //LISTA DE DISCIPLINA MUITOS PRA MUITOS
 
 

 
 // -------------------------------- NÃO ESQUECER DE CRIAR OS GETTERS E SETTER DOS RELACIONAMENTOS-------------------------------
 //---------------------------------------------------------RELACIONAMENTOS------------------------------------------ 
 
 
 
 //GETTER E SETTER ID 
public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


//GETTER E SETTER NOME 

public String getNome() {
	return nome;
}


public void setNome(String nome) {
	this.nome = nome;
}



//**************************************************************GETTERS E SETTERS DOS RELACIONAMENTOS *************************************

//GETTER E SETTER TURMA (RELACIONAMENTO) 
public Turma getTurma() {
	return turma;
}


public void setTurma(Turma turma) {
	this.turma = turma;
}





//GETTER E SETTER DISCIPLINA (RELACIONAMENTO) 
public List<Disciplina> getDisciplinas() {
	return disciplinas;
}


public void setDisciplinas(List<Disciplina> disciplinas) {
	this.disciplinas = disciplinas;
}

//**************************************************************GETTERS E SETTERS DOS RELACIONAMENTOS *************************************



 
 
 
	
	

}
