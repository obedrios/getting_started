module com.orios.javafx.helloworldfxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.orios.javafx.helloworldfxml to javafx.fxml;
    exports com.orios.javafx.helloworldfxml;
}
