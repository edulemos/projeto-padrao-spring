package com.spring.baseproject.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:/email.properties")
public class EmailUtil {

	@Autowired
	private Environment env;

	@Autowired
	private ServletContext servletContext;

	private String conteudoHtml;
	private String nomeTemplate;
	private String destinatario;
	private String assunto;
	private HashMap<String, String> parametros;

	public void enviarEmailHtml() {
		Thread thead = new Thread(new ThreadMail());
		thead.start();
	}

	public String getTemplateHtml() {

		String caminhoTemplate = servletContext.getRealPath("/email/" + nomeTemplate);

		StringBuffer sbf = new StringBuffer();
		String templateStr = "";

		try {
			// carrega o template
			BufferedReader br = new BufferedReader(new FileReader(caminhoTemplate));
			while (br.ready()) {
				sbf.append(br.readLine());
			}

			templateStr = sbf.toString();

			// carrega os parametros do template
			for (Entry<String, String> entry : parametros.entrySet()) {
				String key = "$P{" + entry.getKey() + "}";
				String value = entry.getValue();
				templateStr = templateStr.replace(key, value);
			}

			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		// retorna o template sem espacos
		return templateStr.replaceAll("\\s+", " ");
	}

	public class ThreadMail extends Thread {

		String hostName;
		String fromEmail;
		String usuario;
		String senha;
		String conteudoHtml;
		String destinatario;
		String assunto;
		boolean authenticator;
		boolean tls;
		boolean ssl;
		int smtpPort;

		public ThreadMail() {

			hostName = env.getProperty("email.hostName", String.class);
			fromEmail = env.getProperty("email.fromEmail", String.class);
			usuario = env.getProperty("email.usuario", String.class);
			senha = env.getProperty("email.senha", String.class);
			smtpPort = null != env.getProperty("email.smtpPort", String.class) ? new Integer(env.getProperty("email.smtpPort", String.class)) : 25;
			authenticator = "true".equals(env.getProperty("email.authenticator", String.class)) ? true : false;
			tls = "true".equals(env.getProperty("email.tls", String.class)) ? true : false;
			ssl = "true".equals(env.getProperty("email.ssl", String.class)) ? true : false;

		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {

			try {

				HtmlEmail htmlEmail = new HtmlEmail();
				String templateHtml = getTemplateHtml();
				htmlEmail.setHtmlMsg(templateHtml);
				htmlEmail.addTo(getDestinatario());
				htmlEmail.setSubject(getAssunto());

				htmlEmail.setFrom(fromEmail);
				htmlEmail.setHostName(hostName);
				htmlEmail.setSmtpPort(smtpPort);
				htmlEmail.setTLS(tls);
				htmlEmail.setSSL(ssl);

				if (authenticator) {
					htmlEmail.setAuthenticator(new DefaultAuthenticator(usuario, senha));
				}

				htmlEmail.send();

			} catch (EmailException e) {
				e.printStackTrace();
			}

		}

	}

	public String getConteudoHtml() {
		return conteudoHtml;
	}

	public void setConteudoHtml(String conteudoHtml) {
		this.conteudoHtml = conteudoHtml;
	}

	public String getNomeTemplate() {
		return nomeTemplate;
	}

	public void setNomeTemplate(String nomeTemplate) {
		this.nomeTemplate = nomeTemplate;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public HashMap<String, String> getParametros() {
		return parametros;
	}

	public void setParametros(HashMap<String, String> parametros) {
		this.parametros = parametros;
	}

}
