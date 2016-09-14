# projeto-padrao-spring
projeto spring mvc configurado

## Link projeto rodando: http://52.67.155.222:8080/spring
 
### Tecnologias:
 
 * spring mcv
 * srping security
 * spring data
 * Spring anotations config
 * botstrap 3 template
 * tomcat 8 
 * java 7
 * maven
 
### Caracteristicas:
 
 * login
 * recuperar senha
 * administração de usuários
 * perfis de usuários sistema
 * envio de email
 

### Para executar:

* Importar Projeto como maven project
* Rodar o Projeto no tomcat a 1º vez para criar as tabelas
* Acessar o console do H2 em: http://localhost:8080/spring/console
 * JDBC URL: jdbc:h2:~/h2db/spring_db 
 * User Name: sa 
 * Password:
* Rodar script insert inicial que está em: src/main/resources/script.sql
* Acessar projeto http://localhost:8080/spring
