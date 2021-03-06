//CAMADA DE RESOUCE RESPONSAVEL PARA QUE O  ANGULAR,  GO OU REACT ACESSE ( API REST )
//CRIAR OS METODOS EM RELAÇÃO AS REQUISIÇÕES

package br.com.empresa.resource;



import java.net.URI;
import java.util.List;


import org.hibernate.ObjectNotFoundException;
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
import br.com.empresa.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name= Messages.SWAGGER_TAG_ALUNO_ENDEPOINT)
@RestController
@RequestMapping("/aluno")  //EndPoint =MAPEAMENTO A NOTAÇÃO RESTMAPPING , POIS QUANDO ALGUÉM CHAMAR A LOCALHOST.../ALUNO, VAI ENCONTRAR ESSA CLASSE
public class AlunoResource {
	
	//METODO PARA RETORNAR PARA O FRONT END A LISTA DE ALUNOS
	
	@Autowired
	private AlunoService alunoService; // ACESSANDO OS METODOS DE ALUNO SERVICE
	
	//METODO LISTAS ALUNOS(GET)
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@RequestMapping(method=RequestMethod.GET) //REQUISIÇÃO DO TIPO GET
	public ResponseEntity<List<Aluno>> listarAluno(){ //METODO RESPONSAVEL PELA REQUISIÇÃO DO TIPO GET
		
	 //ACESSANDO A CAMADA DE SERVICE DO METODO LSITATODOS ALUNOS ( VARIAVEL VAI RECEBER)
		List<Aluno> alunos = alunoService.listaTodosAlunos(); //PREENCHE OS ALUNOS
		
		//NECESSARIO RETORNAR UM RESPONSEENTITY (REQUISIÇÃO PRECISA DO TIPO DE PROTOCOLO = VERBOS )
		return ResponseEntity.ok().body(alunos); //SE DEU TUDO CERTO DEVOLVE A TUA REQUISIÇÃO NO CORPOO A LISTA PREENCHIDA
	}
	
	
	
	
	//***************************************CHAMADA DO METODO DE  PAGINAÇÃO E VERSIONAMENTO DE API********************************************
	
	//*******************************************PAGINAÇÃO *** LOCALHOST:8080/API-SISTEMA/PAGE?PAGINA******************************************
	
	@Operation(description = Messages.SWAGGER_PAGINACAO)
	@GetMapping(value="/V1/page")
	public ResponseEntity<Page<Aluno>> listarTurmasPorPaginacaoV1(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value="linhasPorPagina", defaultValue = "24") int linhasPorPagina,
			@RequestParam(value="direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy
			){
		
		Page<Aluno> alunos =  alunoService.buscaPorPaginacao(pagina, linhasPorPagina, direcao, orderBy);
		return ResponseEntity.ok().body(alunos);
	}
	
	

	//NESSA VERSÃO NÃO TEM OPÇÃO DE ALTERAÇÃO DE QUANTIDADE POR LINHAS
	
	@Operation(description = Messages.SWAGGER_PAGINACAO)
	@GetMapping(value="/V2/page")
	public ResponseEntity<Page<Aluno>> listarTurmasPorPaginacaoV2(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value="linhasPorPagina", defaultValue = "24") int linhasPorPagina,
			@RequestParam(value="direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy
			){
		
		Page<Aluno> alunos =  alunoService.buscaPorPaginacao(pagina, linhasPorPagina, direcao, orderBy);
		return ResponseEntity.ok().body(alunos);
	}
	//*******************************************PAGINAÇÃO *** LOCALHOST:8080/API-SISTEMA/PAGE?PAGINA******************************************
	
	
	
	
	
	
	//METODO BUSCAR POR ID ( GET)  - ENDPOINT
	@Operation(description = Messages.SWAGGER_GET)
	@RequestMapping(value="/{id}", method = RequestMethod.GET ) //EndPoint 
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id) throws ObjectNotFoundException{
		Aluno aluno = alunoService.buscarPorId(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	
	//METODO INSERIR  - ENDPOINT
	@Operation(description = Messages.SWAGGER_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inseir(@RequestBody Aluno objAluno){
		Aluno aluno = alunoService.salvar(objAluno);
		//DIZER QUE FOI CRIADO COM SUCESSO E QUAL A URL DELE
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();	
	}
	

	//METODO ALTERAR  - ENDPOINT
	@Operation(description= Messages.SWAGGER_UPDATE)
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Aluno objAluno,@PathVariable Integer id){
		objAluno.setId(id);
		alunoService.alterar(objAluno);
		return ResponseEntity.noContent().build();
		
	}
	
	//METODO DELETAR - ENDPOINT
	    @Operation(description = Messages.SWAGGER_DELETE)
		@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
		public ResponseEntity<Void> excluir(@PathVariable Integer id ){
			alunoService.excluir(id);
			return  ResponseEntity.noContent().build();
	
}

}