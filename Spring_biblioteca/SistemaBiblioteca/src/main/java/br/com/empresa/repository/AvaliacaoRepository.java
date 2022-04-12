package br.com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;




@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina> {
	
	
	//SELECT * FROM AVALIACAO QHERE  IDALUNO =   and IDDISCIPLINA =
	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina); //ESSE É UM SELECT DO JPA

}
