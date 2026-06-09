package geradordesenhas.geradorsenhas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    ObservableList<Integer> opcoesTamanho = FXCollections.observableArrayList(8, 16, 32, 64);

    @FXML
    private Label copiaLabel;

    @FXML
    private ComboBox tamanhosSenha;

    @FXML
    private CheckBox letrasCheck;

    @FXML
    private CheckBox numerosCheck;

    @FXML
    private CheckBox simbolosCheck;

    @FXML
    private Label senhaGeradaLabel;

    public void initialize(){
        tamanhosSenha.setItems(opcoesTamanho);
    }

    public void gerarSenha(){
        String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minusculas = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String simbolos = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        StringBuilder combinacao = new StringBuilder();


        if (letrasCheck.isSelected() == false && numerosCheck.isSelected() == false
                && simbolosCheck.isSelected() == false){
            senhaGeradaLabel.setText("ERRO: Por favor, selecione pelo menos uma opção para prosseguir");
        } else if (tamanhosSenha.getValue() == null){
                senhaGeradaLabel.setText("Selecione o tamanho da senha antes de gerá-la!");
        }

        if (letrasCheck.isSelected()) {
            combinacao.append(maiusculas);
            combinacao.append(minusculas);
        }
        if (numerosCheck.isSelected()){
            combinacao.append(numeros);
        }
        if (simbolosCheck.isSelected()){
            combinacao.append(simbolos);
        }


        SecureRandom random = new SecureRandom();
        StringBuilder senhaGerada = new StringBuilder();

        for (int i = 0; i < (int) tamanhosSenha.getValue(); i++) {
            int indice = random.nextInt(combinacao.length());
            senhaGerada.append(combinacao.charAt(indice));
        }

        senhaGeradaLabel.setText(String.valueOf(senhaGerada));
    }

    public void copiarSenha(){
        String senhaParaCopiar = senhaGeradaLabel.getText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        if (senhaParaCopiar.equals("")){
            copiaLabel.setText("Não há nada para copiar!");
        } else {
            content.putString(senhaParaCopiar);
            clipboard.setContent(content);
            copiaLabel.setText("Senha copiada com sucesso!");

        }
    }

}
