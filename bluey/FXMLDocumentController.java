/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluey;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import models.EmailValidation;
import models.Genius;
import models.Shazam;
import models.User;

/**
 *
 * @author HBadr
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML private WebView bannerartist;
    @FXML private Label name;
    @FXML private BorderPane profilePicture;
    @FXML private ImageView imageView;
    @FXML private TextField email;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML public static Scene scene;
    @FXML private static Stage registerStage;
    @FXML private static Stage currentStage;
    @FXML private static Stage mainPageStage;
    @FXML private final String REGISTER_VIEW_LOCATION = "/views/RegisterPage.fxml";
    @FXML private final String MAINPAGE_VIEW_LOCATION = "/views/MainPage.fxml";
    public static String uname;
    String upassword;
    String uemail;
    
    @FXML
    private void handleLogin(ActionEvent event){
        uname = this.username.getText();
        upassword = this.password.getText();
        if(User.loginInformationAcceptable(uname, upassword)){
       Parent root;
        try {
            getCurrentStage().close();
            this.mainPageStage = new Stage();
            //FXMLDocumentController.currentStage = FXMLDocumentController.registerStage;
            root = FXMLLoader.load(getClass().getResource(this.MAINPAGE_VIEW_LOCATION));
            this.scene = new Scene(root);
            this.mainPageStage.setScene(scene);
            this.mainPageStage.show();
            //displayBanner();

            
            //EmailValidation validate = new EmailValidation("hsa.2244@gmail.com");
            //System.out.println(validate);
            this.currentStage = this.registerStage;
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        else
            System.out.println("ops!");
    }
    /*
     private void displayBanner(){
         User bannery = new User();
         String favy = bannery.getFavoriteArtist();
         Genius banner = new Genius(favy);
         WebEngine web2 = bannerView.getEngine();
         String image = banner.artistBanner();
         web2.load(image);
    }*/
    
    /*Handles the action when a user clicks Register and takes them to
    another window to fill up the information.
    */
    @FXML
    private void handleRegister(ActionEvent event) {
        System.out.println("Register button clicked");
        Parent root;
        try {
            //getCurrentStage().close();
            this.registerStage = new Stage();
            //FXMLDocumentController.currentStage = FXMLDocumentController.registerStage;
            root = FXMLLoader.load(getClass().getResource(this.REGISTER_VIEW_LOCATION));
            this.scene = new Scene(root);
            this.registerStage.setScene(scene);
            this.registerStage.show();
            //this.currentStage = this.registerStage;
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Handles the action when a user clicks Submit to finish the registering 
    process and then use the User class to connect to the database and
    fill the information in the table in the database.
    */
    @FXML
    private void handleSubmit(ActionEvent event){
        System.out.println("Submit button clicked");
        this.uname = this.username.getText();
        this.upassword = this.password.getText();
        this.uemail = this.email.getText();
        String favArtist = "";
        String profilePic = "";
        User.usernameAcceptable(uname, upassword, uemail, favArtist, profilePic);
    }
    @FXML
    private void handleCheck(ActionEvent event){
       User bannery = new User();
         String favy = bannery.getFavoriteArtist();
         Genius banner = new Genius(favy);
         WebEngine web2 = bannerartist.getEngine();
         String imagee = banner.artistBanner();
         web2.load(imagee); 
    }
   
   
    public static Stage getCurrentStage() {
        return FXMLDocumentController.currentStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLDocumentController.currentStage = bluey.Bluey.newStage;
        if (this.name != null)
            this.name.setText("Hello " + uname);
        
}
}
