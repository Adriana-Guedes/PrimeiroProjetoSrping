package br.com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.repository.DisciplinaRepository;



@Service
public class DisciplinaService {
	
	
	@Autowired
	private DisciplinaRepository disciplinaRepo;
	
	
	public List<Disciplina> listaTodasDisciplinas(){
		
		return disciplinaRepo.findAll();
		
	}
	
	
//****************************************METODO DE PAGINAÇÃO***************************************************************
	
	//QUAL A PAGINA INICIAL, A QUANTIDADE DE PAGINAS, A DIREÇÃO ASC OU DESC, ORDENAÇÃO QUAL CAMPO SERÁ A ORDENAÇÃO)
public Page<Disciplina> buscaPorPaginacao(int pagina, int linhasPorPagina, String direction, String orderBy){


PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direction) , orderBy);
//PEGAR O RETONRO DA LISTA E APLICAR A PAGINAÇÃO
return new PageImpl<>(disciplinaRepo.findAll(),pageRequest,linhasPorPagina);


}


//****************************************METODO DE PAGINAÇÃO***************************************************************
	
	
	
	
	
	public Disciplina buscarPorID(Integer id) throws ObjectNotFoundException{
		Optional<Disciplina> disciplina = disciplinaRepo.findById(id);
		
		return disciplina.orElseThrow(() -> new ObjectNotFoundException(null, "Disciplina não encontrada"));
		
		
	}
	
	public Disciplina salvar(Disciplina disciplina) {
		
		return disciplinaRepo.save(disciplina);
		
	}
	
	public void excluir(Integer id) {
		disciplinaRepo.deleteById(id);
		
		
	}
	 
	public Disciplina alterar(Disciplina objDisciplina) {
		
		Disciplina disc = buscarPorID(objDisciplina.getId());
		disc.setNome(objDisciplina.getNome());
		return salvar(disc);
	}
}	
