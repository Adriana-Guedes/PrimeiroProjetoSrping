package br.com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.Turma;
import br.com.empresa.repository.TurmaRepository;

@Service

public class TurmaService {
	
	@Autowired
	TurmaRepository repo;
	
	
	
	//METODO DE LISTAR TODAS AS TURMAS
	public List<Turma> listaTodasTurmas(){
		
		return repo.findAll();
		
	}
	
	
	
	//METODO DE LISTAR TURMA POR ID 
	
	public Turma buscarPorID(Integer id) throws ObjectNotFoundException {
		
    //QUE OBJETO VC ESPERA? 		 O QUE VC QUER QUE ACONTECE? TRAZR UMA TURMA POR ID
		     Optional<Turma> turma = repo.findById(id);    // REPOSITORIO POSSUI UM METODO DE BUSCAR POR AI
				
		     //ORELSETROW (SE EXISTER O VALOR, LANÇA O VALOR, SE NÃO, TRAZ O EXCEPTION
		     return  turma.orElseThrow(() ->	new ObjectNotFoundException (null, "Turma não encontrada") );	
		
	}
	
	
	
	//METODO PARA INSERIR UMA TURMA
	
	public Turma salvar(Turma turma) {
		
		return repo.save(turma); //MESMA COISA QUE INSERT INTO TURMA(NOME) VALUES ( "NOME DA TURMA)
	}
	
	
	//METODO PARA FAZER UPDATE DA TURMA 
	
	public Turma alterar (Turma objTurma) {
		
		//BUSCA-SE PRIMEIRO OS DADOS QUE DESEJA ALTERAR (UTILIZANDO O BUSCAR POR ID)
		Turma turma = buscarPorID(objTurma.getId());
		//PEGA O VALOR DO NOME QUE VEJO NO OJBTURMA E COLOCAR UM NOME NOVO
		turma.setNome(objTurma.getNome());
		return salvar(turma);
	}
	
	
	
	//METODO DE EXCLUIR TURMA 
	
	public void excluir (Integer id) {
		repo.deleteById(id);
	}
}
