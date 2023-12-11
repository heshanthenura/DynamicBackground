module com.heshanthenura.dynamicbackground {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.heshanthenura.dynamicbackground to javafx.fxml;
    exports com.heshanthenura.dynamicbackground;
}