package me.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Initializes our RESTful API.
 * 
 * <p>
 * The {@link OpenAPIDefinition} annotation was used to enable HTTPS in the Swagger UI.
 * For more details, see the following post on Stack Overflow: 
 * https://stackoverflow.com/a/71132608/3072570
 * </p>
 */

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")}) // Esta anotação foi usada para habilitar HTTPS no Swagger UI, ou seja, para que o Swagger UI seja acessível via HTTPS. Pois o Railway funciona com HTTPS. Daí antes estava dando conflito.
@SpringBootApplication
public class Application {
	public static void main(String[] args) {SpringApplication.run(Application.class, args);	}
}
/**
 * Explicação:
1. Entrada no method main
public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
}

Este é o ponto de entrada da aplicação. O method estático run() da classe SpringApplication é chamado, passando a classe Application como classe de configuração principal e os argumentos da linha de comando.

2. Criação da instância SpringApplication
Internamente, o Spring Boot:

Detecta se é uma aplicação web ou não
Carrega os "ApplicationContextInitializers" do classpath
Carrega os "ApplicationListeners" do classpath
Identifica a classe principal (neste caso, Application.class)

3. Configuração da aplicação
Configura fontes adicionais de configuração (properties, YAML, etc.)
Prepara o ambiente (profiles, properties)
Registra beans específicos para gerenciamento da aplicação
Carrega recursos de spring.factories

4. Criação do ApplicationContext
Cria o contexto apropriado (WebApplicationContext para aplicações web)
Prepara o contexto, configurando o ambiente e inicializadores

5. Refresh do ApplicationContext
Esta é uma fase crítica onde:

Escaneia o classpath procurando por componentes (@Component, @Service, @Controller, etc.)
Registra todos os beans definidos
Processa anotações como @Autowired, @Value, etc.
Inicializa todos os beans singleton que não são lazy
Dispara eventos específicos do ciclo de vida

6. Processamento das anotações específicas
Neste caso, processa:

@SpringBootApplication - que combina:
@Configuration: Marca a classe como fonte de definições de beans
@EnableAutoConfiguration: Ativa a configuração automática baseada no classpath
@ComponentScan: Escaneia pacotes para encontrar componentes
@OpenAPIDefinition: Configura o Swagger/OpenAPI para documentação da API
Configura o servidor como "/" para compatibilidade com HTTPS no Railway
7. Execução de CommandLineRunners e ApplicationRunners
Após a inicialização completa, o Spring Boot executa todos os beans que implementam as interfaces CommandLineRunner ou ApplicationRunner.

8. Aplicação pronta
A aplicação está inicializada e pronta para receber requisições. Se for uma aplicação web, o servidor embutido (como Tomcat) já está rodando.

Detalhes sobre a configuração do OpenAPI/Swagger
A anotação @OpenAPIDefinition com a configuração de servidor:

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
Esta configuração é especificamente para garantir que o Swagger UI funcione corretamente quando a aplicação está hospedada em um ambiente HTTPS como o Railway. Sem esta configuração, o Swagger UI poderia tentar usar URLs absolutas com HTTP, causando problemas de mixed content quando acessado via HTTPS.
 */