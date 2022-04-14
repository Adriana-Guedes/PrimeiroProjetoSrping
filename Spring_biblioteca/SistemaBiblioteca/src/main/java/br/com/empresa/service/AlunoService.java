//CAMADA DE SERVICE // FALA ENTRE A CAMADA DE REPOSITORIO E A CAMADA DE RESOURCE ( ONDE FICA OS METODOS)

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

import br.com.empresa.entity.Aluno;
import br.com.empresa.repository.AlunoRepository;





@Service 
public class AlunoService { 
	
	

	
	
	@Autowired   //INJEÇÃO DE INDEPENCIA, É ONDE EU NÃO PRECISO INSTANCIAR MAIS UM NOVO OBJETO EX: ALUNO MATEUS = NEW ALUNO(); NÃO É MAIS NECESSÁRIO
	private AlunoRepository alunoRepo;
	
	
	//METODOS DO TIPO DA CLASSE, NESSE CASO , TIPO ALUNO// UTILIZANDO METODOS DO PROPRIO JPA
	
	public List<Aluno> listaTodosAlunos(){
		
	 return	alunoRepo.findAll();  // MESMA COISA QUE SELECT * FROM ALUNO
		
		
	}
	
	
	//****************************************METODO DE PAGINAÇÃO***************************************************************
	
										//QUAL A PAGINA INICIAL, A QUANTIDADE DE PAGINAS, A DIREÇÃO ASC OU DESC, ORDENAÇÃO QUAL CAMPO SERÁ A ORDENAÇÃO)
	public Page<Aluno> buscaPorPaginacao(int pagina, int linhasPorPagina, String direction, String orderBy){
		                 
		              		
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direction) , orderBy);
		//PEGAR O RETONRO DA LISTA E APLICAR A PAGINAÇÃO
		return new PageImpl<>(alunoRepo.findAll(),pageRequest,linhasPorPagina);
		
		
		
	}
	

	//****************************************METODO DE PAGINAÇÃO***************************************************************
	
	
	
	
	
	//METODO BUSCA POR ID
	
	public Aluno buscarPorId(Integer id) throws ObjectNotFoundException{
		
		Optional<Aluno> aluno = alunoRepo.findById(id) ;
		//SE ELE ENCONTRAR ELE RETORNA O ALUNO SE NÃO RETORNA UMA AERO FUNCTION 
		return aluno.orElseThrow(() -> new ObjectNotFoundException(null, "Objeto não encontrado") );
	}

	//METODO SALVAR 
	
	
	public Aluno salvar (Aluno aluno) {
		
		return alunoRepo.save(aluno);
	}
	
	//METODO SALVAR 
	public void excluir (Integer id) {
		alunoRepo.deleteById(id);
	}
	
	//METODO ALTERAR
	
	public Aluno alterar(Aluno objAluno) {//NOVO DADO DE ENTRADA (NOVO NOME)
		Aluno aluno = buscarPorId(objAluno.getId()); // VOU USAR O METODO BUSCAR POR ID (bucarPorId) E ALTERA SOMENTE O NOME
		      aluno.setNome(objAluno.getNome());// SETAR O NOVO NOME
		      aluno.setTurma(objAluno.getTurma()); // ALTERA TURMA
		      aluno.setDisciplinas(objAluno.getDisciplinas()); // ALTERA DISCIPLINA
		return salvar(aluno); //SE O ID JÁ EXISTIR ELE ATUALIZA, SE NÃO ELE INSERE O NOVO ALUNO
		
	}
	
}
