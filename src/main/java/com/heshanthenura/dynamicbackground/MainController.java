package com.heshanthenura.dynamicbackground;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {

    Logger logger = Logger.getLogger("info");

    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    double screenWidth = bounds.getWidth();
    double screenHeight = bounds.getHeight();

    Stage stage;

    @FXML
    private ImageView bg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            stage = (Stage) bg.getScene().getWindow();
            logger.info("Screen Width: " + screenWidth + " Screen Height: " + screenHeight);

            bg.setFitWidth(screenWidth);
            bg.setFitHeight(screenHeight);
            bg.setTranslateX(screenWidth - bg.getFitWidth());
            bg.setTranslateY(0);

            stage.setX(0);
            stage.setY(0);

            logger.info("Screen X: " + stage.getX() + " Screen Y: " + stage.getY());

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(16), e -> {
                        double x = stage.getX();
                        double y = stage.getY();

                        bg.setTranslateX(screenWidth - bg.getFitWidth() - x);
                        bg.setTranslateY(-y);
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });
    }

    @FXML
    private void openNewWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));

            newStage.setTitle("Dynamic Background");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
