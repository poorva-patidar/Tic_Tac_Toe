module com.poorva.tic_tac_toe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.poorva.tic_tac_toe to javafx.fxml;
    exports com.poorva.tic_tac_toe;
}