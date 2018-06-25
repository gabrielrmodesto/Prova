/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Fornecedor;

/**
 * FXML Controller class
 *
 * @author gabrielrmodesto
 */
public class JanelaCadastroFornecedoresDialogController implements Initializable {
    
    
    @FXML
    private TextField situacao;

    @FXML
    private TextField cidade;

    @FXML
    private TextField nome;

    @FXML
    private Button insereFornecedor;

    @FXML
    private Button cancela;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Fornecedor fornecedor;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        this.nome = nome;
        this.situacao = situacao;
        this.cidade = cidade;
    }
    @FXML
    void cancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void inserirFornecedor(ActionEvent event) {
        if(validarCampos()){
            fornecedor.setNome(nome.getText());
            fornecedor.setSituacao(situacao.getText());
            fornecedor.setCidade(cidade.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }
    private boolean validarCampos(){
        String errorMessage = "";
        if(nome.getText() == null || nome.getText().length() == 0)
            errorMessage += "Nome invalido!\n";
        if(situacao.getText() == null || situacao.getText().length() == 0)
            errorMessage += "situacao invalido!\n";
        if(cidade.getText() == null || cidade.getText().length() == 0)
            errorMessage += "cidade invalido!\n";
        if(errorMessage.length() == 0)
            return true;
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos invalidos, por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
                  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
