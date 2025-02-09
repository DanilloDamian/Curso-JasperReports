package com.mballem.jasper.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws SQLException {
        //abrirJrxml("09");
        //String diretorio = "C:\\Users\\Danillo\\Documents\\Projeto Curso Jasper\\Curso JasperReports\\relatoriosPDF\\";
        //exportarParaPDF("02",diretorio+"jasper-"+ UUID.randomUUID()+".pdf");
        abrirPontoJasper("02");
    }

    private static void abrirPontoJasper(String numero) throws SQLException {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.acrirPontoJasper("relatorios/jasper/funcionarios-"+numero+".jasper",connection);
        connection.close();

    }

    private static void exportarParaPDF(String numero, String saida) {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.exportaParaPDF("relatorios/jrxml/funcionarios-"+numero+".jrxml",connection,saida);
    }

    private static void abrirJrxml(String numero) throws SQLException {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.addParams("NIVEL_DESC","Junior");
        service.abrirJasperViewer("relatorios/jrxml/funcionarios-"+numero+".jrxml",connection);
        connection.close();
    }
}
