module exercicemvc {
    requires transitive java.desktop;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens exercicemvc to javafx.fxml;
    exports exercicemvc.View;    
    exports exercicemvc.Controller; 
    exports exercicemvc.Model;
}