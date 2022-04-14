package br.com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	
	@Autowired //INJEÇÃO DE DEPENDENCIA
	AvaliacaoRepository repo;
	
	
	public Avaliacao save (Avaliacao avaliacao) {
		
		return repo.save(avaliacao);
		
	}
	
	public List<Avaliacao> findAll(){
		return repo.findAll();
		
	}
	
	
	public Avaliacao buscarNotaAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		return repo.findByAlunoDisciplina(alunoDisciplina);
	}
	
	
	//****************************************METODO DE PAGINAÇÃO***************************************************************
	
	//QUAL A PAGINA INICIAL, A QUANTIDADE DE PAGINAS, A DIREÇÃO ASC OU DESC, ORDENAÇÃO QUAL CAMPO SERÁ A ORDENAÇÃO)
public Page<Avaliacao> buscaPorPaginacao(int pagina, int linhasPorPagina, String direction, String orderBy){


PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direction) , orderBy);
//PEGAR O RETONRO DA LISTA E APLICAR A PAGINAÇÃO
return new PageImpl<>(repo.findAll(),pageRequest,linhasPorPagina);



}


//****************************************METODO DE PAGINAÇÃO***************************************************************

}
