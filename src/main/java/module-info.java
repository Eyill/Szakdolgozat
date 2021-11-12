module adventure {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;

  opens adventure to javafx.fxml;
  exports adventure;
  exports adventure.controller;
  exports adventure.modals;
  exports adventure.modals.controllers;
  opens adventure.controller to javafx.fxml;
  opens adventure.modals.controllers to javafx.fxml;
}