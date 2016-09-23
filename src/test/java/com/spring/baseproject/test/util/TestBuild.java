package com.spring.baseproject.test.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.spring.baseproject.entity.Usuario;

public class TestBuild {
	
	protected List<Usuario> testUsers = new ArrayList<Usuario>();	
	protected String NOME_USUARIO = "Usuario Test1";
	protected final String EMAIL_USUARIO = "Usuariotest1@email.com";
	protected String SENHA_USUARIO = "123456";
	protected String CHAVE_MD5 = "#";
	protected String CHAVE_MD5_ERRADA = "##";

	/**
	 * grava o usuario de teste
	 * 
	 * @param usuario
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertUsuarioDeTeste(Usuario usuario) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		Long userId = null;
		try {
			conn = getConnection();

			stmt1 = conn.prepareStatement("select max(id) + 1 from tb_usuario");
			ResultSet rs = stmt1.executeQuery();
			
			if (rs.next()) {
				userId = rs.getLong(1);
			} else {
				userId = 1L;
			}

			stmt2 = conn.prepareStatement("insert into tb_usuario (id, email, name, password) values (?, ?, ?, ?)");
			stmt2.setLong(1, userId);
			stmt2.setString(2, usuario.getEmail());
			stmt2.setString(3, usuario.getName());
			stmt2.setString(4, usuario.getPassword());
			stmt2.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt1.close();
			stmt2.close();
			conn.close();
		}
	}

	/**
	 * deleta o usuario usado nos testes
	 * 
	 * @param email
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void deleteUsuarioDeTeste(String email) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from tb_usuario where email = ?");
			stmt.setString(1, email);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
	}

	/**
	 * consulta a senha gravada no banco
	 * 
	 * @param email
	 * @return senha do banco
	 * @throws SQLException
	 */
	public static String getSenhaCripto(String email) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String senhaCripto = "";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select password from tb_usuario where email = ?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				senhaCripto = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
		return senhaCripto;
	}

	/**
	 * consulta a senha gravada no banco
	 * 
	 * @param email
	 * @return senha do banco
	 * @throws SQLException
	 */
	public static Long getMaxIdUser() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Long id = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select max(id) from tb_usuario");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
		return id;
	}

	/**
	 * Cria a conexão com o bando de dados
	 * 
	 * @return conexao
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection("jdbc:h2:~/h2db/spring_db", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Encryta uma string em MD5
	 * 
	 * @param s
	 * @return string encriptada
	 */
	public static String encryptMD5(String s) {
		if (null == s || "".equals(s.trim()))
			return null;
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(), 0, s.length());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new BigInteger(1, m.digest()).toString(16);
	}

	/**
	 * Cria um usuario de teste
	 * 
	 * @return usuario
	 */
	public static Usuario getTestUser() {
		String dataHoraString = dataHoraString();
		Usuario u = new Usuario();
		u.setEmail(dataHoraString + "@email.com");
		u.setName("User" + dataHoraString);
		u.setPassword(encryptMD5("123456"));
		return u;
	}
	
	/**
	 * Cria um usuario de teste com a senha
	 * 
	 * @return usuario
	 */
	public static Usuario getTestUser(String senha) {
		String dataHoraString = dataHoraString();
		Usuario u = new Usuario();
		u.setEmail(dataHoraString + "@email.com");
		u.setName("User" + dataHoraString);
		u.setPassword(encryptMD5(senha));
		return u;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		insertUsuarioDeTeste(getTestUser());
	}
	
	/**
	 * Retorna a data e hora em string
	 * 
	 * @return string
	 */
	public static String dataHoraString() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
}
