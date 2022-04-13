package br.com.empresa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



@Configuration
public class SwaggerConfig {
	
	
	@Value("${app.name}") //O NOME É CONFIGURADO NO APPLICATION.PROPERTIES
	private String appName;
	
	
	@Value("{app.description}")
	private String appDescription;
	
	@Value("{app.version}")
	private String appVersion;
	
	
	
	//METODO PADRÃO NECESSÁRIO PARA INICIALIZAR
	public OpenAPI openApi() {
		
		return new OpenAPI()
				.info(new Info()
				.title(appName)	
				.description(appDescription)
				.version(appVersion));
	}
	
}
