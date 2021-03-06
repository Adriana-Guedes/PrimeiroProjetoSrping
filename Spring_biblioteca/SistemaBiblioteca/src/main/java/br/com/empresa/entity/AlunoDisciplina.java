//ENTIDADE DE CHAVE COMPOSTA

package br.com.empresa.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

//TABELA DE AVALIAÇÕES (NOTAS DAS DISCIPLINAS)



@Embeddable // ESSA NOTAÇÃO CARACTERIZA QUE É UMA CHAVE COMPOSTA E QUE SERÁ USADA PARA ALGO
public class AlunoDisciplina implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 8225929723047775610L;
	//COMPOSTA
	@ManyToOne  //VARIAS AVALIAÇÕES PARA UM ALUNO
	private Aluno aluno;
	@ManyToOne //VARIAS AVALIAÇÕES PARA UMA DISCIPLINA
	private Disciplina disciplina;

	
	
	//-------------------------------------NÃO ESQUECER OS GETTERS E SETTERS ------------------------------------------
	
	
	
	//GETTER E SETTER DE ALUNO
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	//GETTER E SETTER DE DISCIPLINA
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	

}
