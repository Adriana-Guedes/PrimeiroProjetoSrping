
//CAMADA DE RESOUCE RESPONSAVEL PARA QUE O  ANGULAR,  GO OU REACT ACESSE ( API REST )
package br.com.empresa.resource; 

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.empresa.entity.Turma;
import br.com.empresa.service.TurmaService;

@RestController
@RequestMapping("/turma")//EndPoint =MAPEAMENTO A NOTAÇÃO RESTMAPPING , POIS QUANDO ALGUÉM CHAMAR A LOCALHOST.../ALUNO, VAI ENCONTRAR ESSA CLASSE
public class TurmaResource {
	
	@Autowired
	public TurmaService turmaService;
	
	
	//METODO LISTAR  - ENDPOINT
	@RequestMapping(method = RequestMethod.GET) // MESMA COISA QUE @GetMapping    
	public ResponseEntity<List<Turma>> listarTurmas(){
		
		List<Turma> turmas = turmaService.listaTodasTurmas();
		return ResponseEntity.ok().body(turmas);
		
	}
	//METODO BUSCAR POR ID - ENDPOINT
	@RequestMapping(value="/{id}", method = RequestMethod.GET) // MESMA COISA QUE @GetMapping    
	public ResponseEntity<Turma> buscaPorId(@PathVariable Integer id) throws ObjectNotFoundException{
		Turma turma = turmaService.buscarPorID(id);
		return  ResponseEntity.ok().body(turma);
	}
	
	//METODO INSERIR - ENDPOINT
	@RequestMapping( method = RequestMethod.POST) //MESMA COISA QUE @POSTMAPPING
	public ResponseEntity<Void> inserir(@RequestBody Turma turma){  //@RequestBody CONSEGUE ENTENDER O QUE FOI ENVIADO NO BODY E ATRIBUIR A VARIAVEL NO CASO turma
		Turma objTurma = turmaService.salvar(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objTurma.getId()).toUri();  
		return ResponseEntity.created(uri).build();
		
		}
	
	//METODO DELETAR - ENDPOINT
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> excluir(@PathVariable Integer id ){
		turmaService.excluir(id);
		return  ResponseEntity.noContent().build();
		
	}
	
	//METODO ALTERAR - ENDPOINT
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Turma objTurma,@PathVariable Integer id){
		objTurma.setId(id);
		turmaService.alterar(objTurma);
		return ResponseEntity.noContent().build();
	}
}
