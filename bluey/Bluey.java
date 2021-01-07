/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluey;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HBadr
 */
public class Bluey extends Application {
    public static Stage newStage;
    protected Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        newStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
