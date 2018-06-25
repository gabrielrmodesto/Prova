/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import model.dao.FornecedorDAO;
import model.database.DatabaseFactory;
import model.database.DatabaseMysql;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author gabrielrmodesto
 */
public class JanelaPrincipalController implements Initializable {

    @FXML
    private Menu menuCadastro;

    @FXML
    private MenuItem menuItemCadastro;

    @FXML
    private Menu menuRelatorios;

    @FXML
    private MenuItem menuItemRelatorios;

    @FXML
    private Menu menuSair;

    @FXML
    private AnchorPane anchorPrincipal;
    private final DatabaseMysql database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    @FXML
    void sairPrograma(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void telaCadastroFornecedores(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/JanelaCadastroFornecedores.fxml"));
            anchorPrincipal.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    void telaRelatoriosPecas(ActionEvent event) throws JRException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Relatorio.fxml"));
            anchorPrincipal.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDAO.setConnection(connection);
    }    
    
}
