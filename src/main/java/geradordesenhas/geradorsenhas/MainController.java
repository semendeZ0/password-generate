package geradordesenhas.geradorsenhas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.security.SecureRandom;

public class MainController {
    ObservableList<Integer> opcoesTamanho = FXCollections.observableArrayList(8, 16, 32, 64);

    @FXML
    private Label copiaLabel;

    @FXML
    private ComboBox<Integer> tamanhosSenha;

    @FXML
    private CheckBox letrasCheck;

    @FXML
    private CheckBox numerosCheck;

    @FXML
    private CheckBox simbolosCheck;

    @FXML
    private Label senhaGeradaLabel;

    @FXML
    private Label segurancaLabel;

    @FXML
    private ProgressBar segurancaProgressBar;


    @FXML
    public void initialize(){
        tamanhosSenha.setItems(opcoesTamanho);

    }

    public void verificarSeguranca(){
        // caixasSelecionadas transforma tudo em String e possibilita condições com switch de forma limpa
         String caixasSelecionadas = String.valueOf(tamanhosSenha.getValue()) +
                "-" + letrasCheck.isSelected() +
                 "-" + numerosCheck.isSelected() +
                 "-" + simbolosCheck.isSelected();

        segurancaProgressBar.getStyleClass().removeAll("fraca", "media", "forte");

        // ███████░░░░

        switch (caixasSelecionadas){
            case "8-true-false-false", "8-false-true-false", "8-false-false-true":
                segurancaLabel.setText("FRACA");
                segurancaLabel.setStyle("-fx-text-fill: #FF003C");
                segurancaProgressBar.setProgress(0.3);
                segurancaProgressBar.getStyleClass().add("fraca");
                break;

            case "16-true-false-false", "16-false-true-false", "16-false-false-true":
                segurancaLabel.setText("FRACA");
                segurancaLabel.setStyle("-fx-text-fill: #FF003C");
                segurancaProgressBar.setProgress(0.3);
                segurancaProgressBar.getStyleClass().add("fraca");
                break;

            case "16-true-true-false", "16-true-false-true", "16-false-true-true":
                segurancaLabel.setText("MÉDIA");
                segurancaLabel.setStyle("-fx-text-fill: #FFD600");
                segurancaProgressBar.setProgress(0.70);
                segurancaProgressBar.getStyleClass().add("media");
                break;

            case "16-true-true-true":
                segurancaLabel.setText("FORTE");
                segurancaLabel.setStyle("-fx-text-fill: #00FF9C");
                segurancaProgressBar.setProgress(1);
                segurancaProgressBar.getStyleClass().add("forte");
                break;

            case "32-true-false-false", "32-false-true-false", "32-false-false-true":
                segurancaLabel.setText("FRACA");
                segurancaLabel.setStyle("-fx-text-fill: #FF003C");
                segurancaProgressBar.setProgress(0.3);
                segurancaProgressBar.getStyleClass().add("fraca");
                break;


            case "32-true-true-true", "32-true-true-false", "32-true-false-true", "32-false-true-true":
                segurancaLabel.setText("FORTE");
                segurancaLabel.setStyle("-fx-text-fill: #00FF9C");
                segurancaProgressBar.setProgress(1);
                segurancaProgressBar.getStyleClass().add("forte");
                break;

            case "64-true-false-false", "64-false-true-false", "64-false-false-true":
                segurancaLabel.setText("FRACA");
                segurancaLabel.setStyle("-fx-text-fill: #FF003C");
                segurancaProgressBar.setProgress(0.3);
                segurancaProgressBar.getStyleClass().add("fraca");
                break;

            case "64-true-true-true", "64-true-true-false", "64-true-false-true", "64-false-true-true":
                segurancaLabel.setText("FORTE");
                segurancaLabel.setStyle("-fx-text-fill: #00FF9C");
                segurancaProgressBar.setProgress(1);
                segurancaProgressBar.getStyleClass().add("forte");
                break;


        }



    }

    public void gerarSenha(){
        String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String minusculas = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String simbolos = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        StringBuilder combinacao = new StringBuilder();


        if (!letrasCheck.isSelected() && !numerosCheck.isSelected()
                && !simbolosCheck.isSelected()){
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

        for (int i = 0; i < tamanhosSenha.getValue(); i++) {
            int indice = random.nextInt(combinacao.length());
            senhaGerada.append(combinacao.charAt(indice));
        }

        senhaGeradaLabel.setText(String.valueOf(senhaGerada));

        verificarSeguranca();
    }

    public void copiarSenha(){
        String senhaParaCopiar = senhaGeradaLabel.getText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();

        if (senhaParaCopiar.isEmpty()){
            copiaLabel.setText("Não há nada para copiar!");
        } else {
            content.putString(senhaParaCopiar);
            clipboard.setContent(content);
            copiaLabel.setText("Senha copiada com sucesso!");

        }
    }

}