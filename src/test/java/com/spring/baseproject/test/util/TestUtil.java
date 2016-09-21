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
import java.util.Date;

import com.spring.baseproject.entity.Usuario;

public class TestUtil {

	public static String dataHoraString() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

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

	public static Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection("jdbc:h2:~/h2db/spring_db", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

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

	public static Usuario getTestUser() {
		String dataHoraString = dataHoraString();
		Usuario u = new Usuario();
		u.setEmail(dataHoraString + "@email.com");
		u.setName("User" + dataHoraString);
		u.setPassword(dataHoraString);
		return u;
	}
}
