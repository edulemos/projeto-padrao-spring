# projeto-padrao-spring
projeto spring mvc configurado
 
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
* Rodar o Projeto no tomcat
* Acessar o console do H2:
 * http://localhost:8080/spring/console
 * JDBC URL: jdbc:h2:~/h2db/spring_db 
 * User Name: sa 
 * Password:
* Rodar script de insert inicial que está em:
 * src/main/resources/script.sql
* Acessar projeto em
 * http://localhost:8080/spring
* configurações do banco de dados em:
 * src/main/resources/application.properties
* configurações de envio de email em:
 * src/main/resources/email.properties
