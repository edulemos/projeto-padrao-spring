/* Para gerar as tabelas use o atributo hibernate.generate_dll=true em application.properties */

/*cria os usuarios - senha criptogafada = 123456 */
insert into TB_USUARIO (ID, EMAIL, NAME, PASSWORD) values (1 , 'admin@email.com', 'Administrador do sistema', 'e10adc3949ba59abbe56e057f20f883e');
insert into TB_USUARIO (ID, EMAIL, NAME, PASSWORD) values (2 , 'usuario@email.com', 'Usuario do sistema', 'e10adc3949ba59abbe56e057f20f883e');

/*cria os perfis de acesso*/
insert into TB_PERFIL (ID, NOME) values (1, 'PERFIL ADMIN');
insert into TB_PERFIL (ID, NOME) values (2, 'PERFIL USUARIO');

/*associa os perfis aos usuarios*/
insert into TB_USUARIO_PERFIL (USUARIO_ID, PERFIL_ID) values (1, 1);
insert into TB_USUARIO_PERFIL (USUARIO_ID, PERFIL_ID) values (2, 2);

/* inserido ao iniciar o sistema pela classe AppStart.java
insert into TB_ROLE (ID, NOME, DESCRICAO) values (1, 'ROLE_MANTER_USUARIOS', 'Manter usuários sistema');
insert into TB_ROLE (ID, NOME, DESCRICAO) values (2, 'ROLE_MANTER_PERFIS', 'Manter perfis sistema');
insert into TB_ROLE (ID, NOME, DESCRICAO) values (3, 'ROLE_MANTER_PRODUTOS', 'Manter produtos');*/

/*associa as roles aos perfis*/
insert into TB_PERFIL_ROLES (PERFIL_ID, ROLE_ID) values (1, 1);
insert into TB_PERFIL_ROLES (PERFIL_ID, ROLE_ID) values (1, 2);
insert into TB_PERFIL_ROLES (PERFIL_ID, ROLE_ID) values (1, 3);
insert into TB_PERFIL_ROLES (PERFIL_ID, ROLE_ID) values (2, 3);

/*consulta perfis usuarios*/
select u.name nome_usuario, p.nome perfis_associados
from tb_perfil p, tb_usuario u, tb_usuario_perfil up
where  u.id = up.usuario_id
and up.perfil_id = p.id;

/*consulta permissoes perfis*/
select p.nome perfil, r.descricao permissao
  from tb_perfil p, tb_role r, tb_perfil_roles pr
 where p.id = pr.perfil_id
   and pr.role_id = r.id;


