

//CAMADA DE PERSISTENCIA
//METODOS DEVERÃO SER INCLUIDOS NA CAMADA SERVICE


package br.com.empresa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.entity.Aluno;






@Repository // ANOTAÇÃO PARA SPRING SABER QUE ISSO É UM REPOSITORIO <CLASSE , E O TIPO DA CHAVE PRIMARIA>
public interface AlunoRepository extends JpaRepository< Aluno, Integer>{
	
	
	

}
