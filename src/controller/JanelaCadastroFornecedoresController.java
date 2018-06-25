/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.bean.Fornecedor;
import model.dao.FornecedorDAO;
import model.database.DatabaseFactory;
import model.database.DatabaseMysql;

/**
 * FXML Controller class
 *
 * @author gabrielrmodesto
 */
public class JanelaCadastroFornecedoresController implements Initializable {
    @FXML
    private TableView<Fornecedor> tableViewFornecedor;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnNome;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnCidade;
    @FXML
    private Label lblDetalheCodigo;

    @FXML
    private Label lblDetalheNome;

    @FXML
    private Label lblDetalheSituacao;

    @FXML
    private Label lblDetalheCidade;

    @FXML
    private TextField cmpBuscaFornecedor;

    @FXML
    private Button btnBusca;

    @FXML
    private Button btnInserirFornecedor;

    @FXML
    private Button btnAlterarFornecedor;

    @FXML
    private Button btnRemoveFornecedor;
    
    private List<Fornecedor> listFornecedor;
    private ObservableList<Fornecedor> observableListFornecedor;
    private final DatabaseMysql database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    private void carregaTableViewFornecedores() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("f_nome"));
        
        listFornecedor = fornecedorDAO.listar();
        observableListFornecedor = FXCollections.observableArrayList(listFornecedor);
        
        tableViewFornecedor.setItems(observableListFornecedor);
    }
    
    @FXML
    void alteraFornecedor(ActionEvent event) throws IOException {
        Fornecedor fornecedor = tableViewFornecedor.getSelectionModel().getSelectedItem();
        if(fornecedor != null){
            boolean buttonConfirmarClicked = showCadastroFornecedorDialog(fornecedor);
            if(buttonConfirmarClicked){
                fornecedorDAO.alterar(fornecedor);  
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor escolha um fornecedor na tabela");
            alert.show();
        }
    }

    @FXML
    void buscaFornecedor(ActionEvent event) {
        for(Fornecedor fornecedor : fornecedorDAO.listar()){
            if(cmpBuscaFornecedor.getText().equals(String.valueOf(fornecedor.getCodigo()))){
                lblDetalheCodigo.setText(String.valueOf(fornecedor.getCodigo()));
                lblDetalheNome.setText(fornecedor.getNome());
                lblDetalheSituacao.setText(fornecedor.getSituacao());
                lblDetalheCidade.setText(fornecedor.getCidade());
            } 
        }
    }

    @FXML
    void insereFornecedor(ActionEvent event) throws IOException {
        Fornecedor fornecedor = new Fornecedor();
        boolean buttonConfirmarClicked = showCadastroFornecedorDialog(fornecedor);
        if(buttonConfirmarClicked){
            fornecedorDAO.inserir(fornecedor);
        }
    }

    @FXML
    void removeFornecedor(ActionEvent event) {
        Fornecedor fornecedor = tableViewFornecedor.getSelectionModel().getSelectedItem();
        if(fornecedor != null){
            fornecedorDAO.remover(fornecedor);
            carregaTableViewFornecedores();   
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor escolha um fornecedor na tabela");
            alert.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDAO.setConnection(connection);
        
        carregaTableViewFornecedores();
        
        tableViewFornecedor.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFornecedor(newValue));
    }    
     private void selecionarItemTableViewFornecedor(Fornecedor fornecedor) {
        if(fornecedor != null){
            lblDetalheCodigo.setText(String.valueOf(fornecedor.getCodigo()));
            lblDetalheNome.setText(fornecedor.getNome());
            lblDetalheSituacao.setText(fornecedor.getSituacao());
            lblDetalheCidade.setText(fornecedor.getCidade());
        }else{
            lblDetalheCodigo.setText("");
            lblDetalheNome.setText("");
            lblDetalheSituacao.setText("");
            lblDetalheCidade.setText("");
        }
    }

    private boolean showCadastroFornecedorDialog(Fornecedor fornecedor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(JanelaCadastroFornecedoresDialogController.class.getResource("../view/JanelaCadastroFornecedoresDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Fornecedores");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        JanelaCadastroFornecedoresDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFornecedor(fornecedor);
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

    

   
    
}
