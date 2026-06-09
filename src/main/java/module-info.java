module geradordesenhas.geradorsenhas {
    requires javafx.controls;
    requires javafx.fxml;


    opens geradordesenhas.geradorsenhas to javafx.fxml;
    exports geradordesenhas.geradorsenhas;
}