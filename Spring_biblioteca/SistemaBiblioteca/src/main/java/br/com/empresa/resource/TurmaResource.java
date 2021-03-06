
//CAMADA DE RESOUCE RESPONSAVEL PARA QUE O  ANGULAR,  GO OU REACT ACESSE ( API REST )
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
import br.com.empresa.entity.Turma;
import br.com.empresa.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;





@Tag(name = Messages.SWAGGER_TAG_TURMA_ENDPOINT )//NOME DO ENDPOINT NO SWAGGER
@RestController
@RequestMapping("/turma")//EndPoint =MAPEAMENTO A NOTAÇÃO RESTMAPPING , POIS QUANDO ALGUÉM CHAMAR A LOCALHOST.../ALUNO, VAI ENCONTRAR ESSA CLASSE
public class TurmaResource {
	
	@Autowired
	public TurmaService turmaService;
	
	
	
	//METODO LISTAR  - ENDPOINT
	
	@Operation(description = Messages.SWAGGER_GET_ALL) //DESCRIÇÃO PARA O SWAGGER DO QUE ESSE METODO FAZ
	@RequestMapping(method = RequestMethod.GET) // MESMA COISA QUE @GetMapping    
	public ResponseEntity<List<Turma>> listarTurmas(){	
		List<Turma> turmas = turmaService.listaTodasTurmas();
		return ResponseEntity.ok().body(turmas);
			
		
	}
	
	
	
	//***************************************CHAMADA DO METODO DE  PAGINAÇÃO E VERSIONAMENTO DE API********************************************
	
	//*******************************************PAGINAÇÃO *** LOCALHOST:8080/API-SISTEMA/PAGE?PAGINA******************************************
	
	@Operation(description = Messages.SWAGGER_PAGINACAO)
	@GetMapping(value="/V1/page")
	public ResponseEntity<Page<Turma>> listarTurmasPorPaginacaoV1(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value="linhasPorPagina", defaultValue = "24") int linhasPorPagina,
			@RequestParam(value="direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy
			){
		
		Page<Turma> turmas =  turmaService.buscaPorPaginacao(pagina, linhasPorPagina, direcao, orderBy);
		return ResponseEntity.ok().body(turmas);
	}
	
	
	//NESSA VERSÃO NÃO TEM OPÇÃO DE ALTERAÇÃO DE QUANTIDADE POR LINHAS
	
	@Operation(description = Messages.SWAGGER_PAGINACAO)
	@GetMapping(value="/V2/page")
	public ResponseEntity<Page<Turma>> listarTurmasPorPaginacaoV2(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
		
			@RequestParam(value="direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy
			){
		
		Page<Turma> turmas =  turmaService.buscaPorPaginacao(pagina, 10, direcao, orderBy);
		return ResponseEntity.ok().body(turmas);
	}
	
	
	
	//*******************************************PAGINAÇÃO *** LOCALHOST:8080/API-SISTEMA/PAGE?PAGINA******************************************
	
	
	
	
	
	
	
	//METODO BUSCAR POR ID - ENDPOINT
	@Operation(description = Messages.SWAGGER_GET )
	@RequestMapping(value="/{id}", method = RequestMethod.GET) // MESMA COISA QUE @GetMapping    
	public ResponseEntity<Turma> buscaPorId(@PathVariable Integer id) throws ObjectNotFoundException{
		Turma turma = turmaService.buscarPorID(id);
		return  ResponseEntity.ok().body(turma);
	}
	
	
	
	
	//METODO INSERIR - ENDPOINT
	@Operation(description = Messages.SWAGGER_INSERT )
	@RequestMapping( method = RequestMethod.POST) //MESMA COISA QUE @POSTMAPPING
	public ResponseEntity<Void> inserir(@RequestBody Turma turma){  //@RequestBody CONSEGUE ENTENDER O QUE FOI ENVIADO NO BODY E ATRIBUIR A VARIAVEL NO CASO turma
		Turma objTurma = turmaService.salvar(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objTurma.getId()).toUri();  
		return ResponseEntity.created(uri).build();
		
		}
	
	
	
	//METODO DELETAR - ENDPOINT
	@Operation(description =Messages.SWAGGER_DELETE )
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> excluir(@PathVariable Integer id ){
		turmaService.excluir(id);
		return  ResponseEntity.noContent().build();
		
	}
	
	
	
	//METODO ALTERAR - ENDPOINT
	@Operation(description= Messages.SWAGGER_UPDATE)
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Turma objTurma,@PathVariable Integer id){
		objTurma.setId(id);
		turmaService.alterar(objTurma);
		return ResponseEntity.noContent().build();
	}
	
	
}
