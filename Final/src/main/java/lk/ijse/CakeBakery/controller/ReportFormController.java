package lk.ijse.CakeBakery.controller;

import lk.ijse.CakeBakery.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;

public class ReportFormController {
    public AnchorPane ReportFormContext;
    public Button btnItemDiffernt;
    public Button btnSQLReport;
    public Button btnEmployeeReport;


    public void EmployeeReportOnAction(ActionEvent actionEvent) {
    }

    public void SqlReportOnAction(ActionEvent actionEvent) {
    }
    public void ItemDifferntOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("src/main/java/lk.ijse.CakeBakery.report/Blank_A4_101.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);

            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*public void ItemDifferntOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/lk.ijse.CakeBakery.report/QtyChart.jrxml"));
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, connection);
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/


    public void txtFeild_Key_Released(KeyEvent keyEvent) {
    }
}
