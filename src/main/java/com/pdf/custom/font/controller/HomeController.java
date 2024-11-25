package com.pdf.custom.font.controller;

import com.pdf.custom.font.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController{

    @Autowired
    private PdfService pdfService;
    @GetMapping("/")
    public String index(){
        return "HOME CONTROLLER";
    }

    @GetMapping("/generate")
    public ResponseEntity<?> getPurchaseReport(@RequestParam(required = false, name = "text", defaultValue = "") String text) throws Exception {
        System.out.println("Value is "+ text);
        byte[] report = pdfService.generatePdf(text);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customFont.pdf")
                .contentType(MediaType.APPLICATION_PDF).body(report);
    }

}