package br.com.empresa.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.empresa.constantes.Messages;
import br.com.empresa.entity.Aluno;
import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name=Messages.SWAGGER_TAG_AVALIACAO_ENDPOINT)

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;
	
	@Operation(description= Messages.SWAGGER_GET_ALL)
	//@GetMapping
	@RequestMapping(method =RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> listarAvaliacao(){
		List<Avaliacao> listaAvaliacao = service.findAll();
		return  ResponseEntity.ok().body(listaAvaliacao);
		
		
	}
	
	
	
	//***************************************CHAMADA DO METODO DE  PAGINAÇÃO E VERSIONAMENTO DE API********************************************
	
	//*******************************************PAGINAÇÃO *** LOCALHOST:8080/API-SISTEMA/PAGE?PAGINA******************************************
	
	@Operation(description = Messages.SWAGGER_PAGINACAO)
	@GetMapping(value="/v1/page")
	public ResponseEntity<Page<Avaliacao>> listarTurmasPorPaginacao(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value="linhasPorPagina", defaultValue = "24") int linhasPorPagina,
			@RequestParam(value="direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy
			){
		
		Page<Avaliacao> avaliacoes =  service.buscaPorPaginacao(pagina, linhasPorPagina, direcao, orderBy);
		return ResponseEntity.ok().body(avaliacoes);
	}	
	
	
		//NESSA VERSÃO NÃO TEM OPÇÃO DE ALTERAÇÃO DE QUANTIDADE POR LINHAS
		
		@Operation(description = Messages.SWAGGER_PAGINACAO)
		@GetMapping(value="v2//page")
		public ResponseEntity<Page<Avaliacao>> listarTurmasPorPaginacaov2(
				@RequestParam(value="pagina", defaultValue = "0") int pagina,
			
				@RequestParam(value="direcao", defaultValue = "ASC") String direcao,
				@RequestParam(value="orderBy", defaultValue = "nome") String orderBy
				){
			
			Page<Avaliacao> avaliacoes =  service.buscaPorPaginacao(pagina, 10, direcao, orderBy);
			return ResponseEntity.ok().body(avaliacoes);
		
	}
	//*******************************************PAGINAÇÃO *** LOCALHOST:8080/API-SISTEMA/PAGE?PAGINA******************************************
	
	
	
	
	
	
	@Operation(description= Messages.SWAGGER_INSERT )
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody  Avaliacao objAvaliacao){
		objAvaliacao = service.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
       return ResponseEntity.created(uri).build();		
		
		
	}
	
	
	@Operation(description = Messages.SWAGGER_GET)
	@RequestMapping(value = "/{idAluno}/{idDisciplina}", method = RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoPorDisciplina (@PathVariable Integer idAluno, @PathVariable Integer idDisciplina){
	Aluno aluno = new Aluno();
	aluno.setId(idAluno);
	
	Disciplina disciplina = new Disciplina();
	disciplina.setId(idDisciplina);
	
	AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
	alunoDisciplina.setAluno(aluno);
	alunoDisciplina.setDisciplina(disciplina);
	
	
	Avaliacao avaliacao =service.buscarNotaAlunoDisciplina(alunoDisciplina); 
	
	return ResponseEntity.ok().body(avaliacao);
	
	
	
		
  } 
	
}
