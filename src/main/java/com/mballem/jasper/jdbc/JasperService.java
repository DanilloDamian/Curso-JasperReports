package com.mballem.jasper.jdbc;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class JasperService {

    private Map<String, Object> params = new LinkedHashMap<>();

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public void abrirJasperViewer(String jrxml, Connection connection){
        try {
            JasperReport report = compilarJrxml(jrxml);
            JasperPrint print = JasperFillManager.fillReport(report,this.params,connection);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportaParaPDF(String jrxml, Connection connection, String saida){
        try {
            OutputStream out = new FileOutputStream(saida);
            JasperReport report = compilarJrxml(jrxml);
            JasperPrint print = JasperFillManager.fillReport(report,this.params,connection);
            JasperExportManager.exportReportToPdfStream(print,out);

        } catch (JRException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void acrirPontoJasper(String jasperFile, Connection connection){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(jasperFile);
            JasperPrint print = JasperFillManager.fillReport(is,this.params,connection);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private JasperReport compilarJrxml(String arquivo) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(arquivo);
            return JasperCompileManager.compileReport(is);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
