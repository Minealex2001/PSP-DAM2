module org.example.ejercicio12 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.ejercicio12 to javafx.fxml;
    exports org.example.ejercicio12;
}