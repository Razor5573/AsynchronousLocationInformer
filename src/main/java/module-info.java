module ru.nsu.kalugin.fxasynclocationinformer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    opens ru.nsu.kalugin.fxasynclocationinformer to com.fasterxml.jackson.annotation, javafx.fxml, com.fasterxml.jackson.core, com.fasterxml.jackson.databind;

    exports ru.nsu.kalugin.fxasynclocationinformer;
}