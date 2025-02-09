package com.mballem.jasper.jdbc;

import java.sql.Connection;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        //abrirJrxml("09");
        String diretorio = "C:\\Users\\Danillo\\Documents\\Projeto Curso Jasper\\Curso JasperReports\\relatoriosPDF\\";
        exportarParaPDF("02",diretorio+"jasper-"+ UUID.randomUUID()+".pdf");
    }

    private static void exportarParaPDF(String numero, String saida) {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.exportaParaPDF("relatorios/jrxml/funcionarios-"+numero+".jrxml",connection,saida);
    }

    private static void abrirJrxml(String numero) {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.addParams("NIVEL_DESC","Junior");
        service.acrirJasperViewer("relatorios/jrxml/funcionarios-"+numero+".jrxml",connection);
    }
}
