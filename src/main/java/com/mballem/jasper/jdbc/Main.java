package com.mballem.jasper.jdbc;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        abrirJrxml("09");
    }

    private static void abrirJrxml(String numero) {
        Connection connection = JdbcConnection.connection();
        JasperService service = new JasperService();
        service.addParams("NIVEL_DESC","Junior");
        service.acrirJasperViewer("relatorios/jrxml/funcionarios-"+numero+".jrxml",connection);
    }
}
