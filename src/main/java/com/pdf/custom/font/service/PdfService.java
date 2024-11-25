package com.pdf.custom.font.service;

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class PdfService {
    public byte[] generatePdf(String text) {

        try {
            File file = ResourceUtils.getFile("classpath:static/pdfTemplates/customFont.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Create parameters for the main report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("text", text);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
