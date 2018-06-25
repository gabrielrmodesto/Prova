/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bean.Fornecedor;
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
public class RelatorioController implements Initializable {
    
    @FXML
    private TableView<Fornecedor> tableVIewRelatorio;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnNome;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnSituacao;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnCidade;

    @FXML
    private Button btnImprimeRelatorio;
    
    private List<Fornecedor> listFornecedor;
    private ObservableList<Fornecedor> observableListFornecedor;
    
    private final DatabaseMysql database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();

    @FXML
    void imprimeRelatorio(ActionEvent event) throws JRException {
        URL url = getClass().getResource("/view/RelatorioProva.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDAO.setConnection(connection);
        carregaTableViewFornecedor();
    }    

    private void carregaTableViewFornecedor() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("f_nome"));
        tableColumnSituacao.setCellValueFactory(new PropertyValueFactory<>("f_situacao"));
        tableColumnCidade.setCellValueFactory(new PropertyValueFactory<>("f_cidade"));
        
        listFornecedor = fornecedorDAO.listar();
        
        observableListFornecedor = FXCollections.observableArrayList(listFornecedor);
        tableVIewRelatorio.setItems(observableListFornecedor);
    }
    
}
