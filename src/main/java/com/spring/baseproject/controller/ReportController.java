package com.spring.baseproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class ReportController {

	@Autowired
	private DataSource dataSource;

	@RequestMapping(value = "/relatorio/usuarios", method = RequestMethod.GET)
	@ResponseBody
	public void getReport(HttpServletResponse response) throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream("/reports/report_usuarios.jasper");
		Map<String, Object> params = new HashMap<>();
		params.put("report_name", "USUÁRIOS SISTEMA");
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=report.pdf");

		final OutputStream outStream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}

}
