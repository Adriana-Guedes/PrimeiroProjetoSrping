//CLASSE DE INICIALIZAÇÃO PARA TESTES

package br.com.empresa.inicializacao;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.empresa.entity.Aluno;
import br.com.empresa.entity.AlunoDisciplina;
import br.com.empresa.entity.Avaliacao;
import br.com.empresa.entity.Disciplina;
import br.com.empresa.entity.Turma;
import br.com.empresa.repository.AlunoRepository;
import br.com.empresa.service.AvaliacaoService;
import br.com.empresa.service.DisciplinaService;
import br.com.empresa.service.TurmaService;

@Component //CLASSE PARA SER INICIALIZADA SEMPRE QUE A APLICAÇÃO FOR STARTADA (NECESSÁRIO IMPLEMENTAR ESSA INTERFACE DO JPA ( ApplicationListener<ContextRefreshedEvent>  )
public class Init implements ApplicationListener<ContextRefreshedEvent> {
	
	//ASSIM QUE A APLICAÇÃO SUBIR O METODO A SER USADO SERÁ
	@Autowired
	AlunoRepository alunoRepo; // NÃO É MUITO INDICADO CHAMAR O REPOSITORIO
	
	@Autowired  //PARA CHAMAR A CLASSE DE SERVIÇO
	TurmaService  turmaService; //CHAMAR SEMPRE A DE SERVIÇO
	
	@Autowired
	DisciplinaService disciplinaService;
	
	
	@Autowired
	AvaliacaoService avaliacaoService;
	



	@Override  //PERSISTENCIA DO ALUNO
	public void onApplicationEvent(ContextRefreshedEvent event) { //TESTAR SE ESTA TUDO FUNCIONANDO , E FAZER UMA CARGA DE DADOS
		
	
		

		//CARGA DE ALUNO
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Lucas");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Arthur");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("João");
		
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Claudio");
		//alunoRepo.saveAll(Arrays.asList(aluno1,aluno2,aluno3,aluno4)); // MESMA COISA QUE INSERTO INTO ALUNO( NOME ) VALUES ('NOME INSERIDO')
		
	
		
		
		
		//Turma turma1 = turmaService.buscarPorID(1);
		 //System.out.println(turma1.getNome());
		
		
		 
		//turmaService.excluir(1);
		 
		 //List<Turma> listaTurmas = turmaService.listaTodasTurmas();
		//for (Turma turma : listaTurmas) {
	   //System.out.println("Nome da turma: " + turma.getNome());
				
				
				
			//}
			//Turma turmaAlterar = new Turma();
			//turmaAlterar.setId(1);
			//turmaAlterar.setNome("Redes");
			//turmaService.alterar(turmaAlterar);
			
		
		  //CARGA DE DISCIPLINA (PERSISTIR )
			
			Disciplina Java = new Disciplina();
			Java.setNome("Java");
			disciplinaService.salvar(Java);
			
			
			Disciplina Java2 = new Disciplina();
			Java2.setNome("Java2");
			disciplinaService.salvar(Java2);
			
			Disciplina Angular = new Disciplina();
			Angular.setNome("Java2");
			disciplinaService.salvar(Angular);
			
			
			Disciplina Arquitetura = new Disciplina();
			Arquitetura.setNome("Arquitetura");
			disciplinaService.salvar(Arquitetura);
			
			
		
			
				
			
			//CARGA DE TURMA (PERSISTIR )
			
			Turma ads = new Turma();
			ads.setNome("ADS");
			turmaService.salvar(ads);
			
			
			Turma rede = new Turma();
			rede.setNome("REDE");
			turmaService.salvar(rede);
			
						
						
						aluno1.setTurma(ads);
						aluno2.setTurma(rede);
						aluno3.setTurma(ads);
						aluno4.setTurma(rede);
						
						
						aluno1.setDisciplinas(Arrays.asList(Java,Arquitetura,Angular));
						aluno2.setDisciplinas(Arrays.asList(Java,Angular));
						aluno3.setDisciplinas(Arrays.asList(Java));
						aluno4.setDisciplinas(Arrays.asList(Angular));
						
						
						
						alunoRepo.save(aluno1);
						alunoRepo.save(aluno2);
						alunoRepo.save(aluno3);
						alunoRepo.save(aluno4);
	
	
						
						
						
						
						Avaliacao avaliacaoAluno1 = new Avaliacao();
						
						
						AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
						
						alunoDisciplina.setAluno(aluno1);
						alunoDisciplina.setDisciplina(Arquitetura);
						avaliacaoAluno1.setAlunoDisciplina(alunoDisciplina);//AQUI ELE ESTA SETANDO O ALUNODISCIPLINA COM OS DADOS SETADOS ACIMA( QUE TEM ALUNO1 E ARQUITETURA)
						avaliacaoAluno1.setConceito("A");
						avaliacaoService.save(avaliacaoAluno1);
						
						
						
						////// SEGUNDO ALUNO /////

						AlunoDisciplina JoaoJava = new AlunoDisciplina();
						JoaoJava.setAluno(aluno4);
						JoaoJava.setDisciplina(Java);
						
						Avaliacao avaliacaoJoaoJava = new Avaliacao();
						avaliacaoJoaoJava.setAlunoDisciplina(JoaoJava);
						avaliacaoJoaoJava.setConceito("B");
						avaliacaoService.save(avaliacaoJoaoJava);
						
						
						
						Avaliacao aval = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
						System.out.println("Aluno: " + aval.getAlunoDisciplina().getAluno().getNome() );
						System.out.println("Disciplina: " + aval.getAlunoDisciplina().getDisciplina().getNome());
						System.out.println("Avaliacão: " + aval.getConceito());
	
	}

}
