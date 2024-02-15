module org.example.practica1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens org.example.practica1 to javafx.fxml;
    exports org.example.practica1;
}