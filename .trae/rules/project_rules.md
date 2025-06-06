Este é o mu arquivo build.gradle deste projeto:
plugins {
  id 'java' // Diz "vamos trabalhar com Java!"
  id 'org.springframework.boot' version '3.1.1' //  Adiciona o Spring Boot, que é como um kit de peças pré-montadas para fazer aplicações web
  id 'io.spring.dependency-management' version '1.1.0' // Ajuda a organizar todas as peças que seu projeto precisa
}

group = 'me.dio' // É como o sobrenome do seu projeto (me.dio)
version = '0.0.1-SNAPSHOT' // Diz qual versão é (0.0.1-SNAPSHOT significa que ainda está em desenvolvimento)
sourceCompatibility = '17' // Diz que estamos usando Java versão 17

repositories {
  mavenCentral() // É de lá que vamos buscar todas as peças que precisamos para nosso projeto.
}

dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-web' // Peças para fazer um site
  implementation 'org.springframework.boot:spring-boot-starter-data-jpa' // Peças para guardar informações em um banco de dados
  implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0' // Peças para criar uma documentação automática do seu site

  runtimeOnly 'com.h2database:h2' // DB em memória para testes e desenvolvimento.
  runtimeOnly 'org.postgresql:postgresql' // DB para produção. runtimeOnly: Peças que só são necessárias quando o programa está rodando
  
  testImplementation 'org.springframework.boot:spring-boot-starter-test' // Peças para testar se tudo está funcionando corretamente
}

tasks.jar {
  manifest {
    attributes["Main-Class"] = "me.dio.Application"
  } // Este trecho significa que: o arquivo .jar será executado pelo method main da classe "me.dio.Application".
}

tasks.named('test') {
  useJUnitPlatform()
} // Este trecho significa que: o method test() será executado usando o JUnit Platform.
