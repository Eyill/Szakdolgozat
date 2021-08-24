module adventure {
    requires javafx.controls;
    requires javafx.fxml;

    opens adventure to javafx.fxml;
    exports adventure;
    exports adventure.controller;
    opens adventure.controller to javafx.fxml;
}