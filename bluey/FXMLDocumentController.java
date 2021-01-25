/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluey;

import java.io.File;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import models.User;

/**
 *
 * @author HBadr
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private WebView bannerartist;
    @FXML
    private Label name;
    @FXML
    private BorderPane profilePicture;
    @FXML
    private ImageView imageView;
    @FXML
    private Image image;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private static Scene scene;
    @FXML
    private static Stage registerStage;
    @FXML
    private static Stage currentStage;
    @FXML
    private static Stage mainPageStage;
    @FXML
    private static Stage browseStage;
    @FXML
    private final String registerViewLocation = "/views/RegisterPage.fxml";
    @FXML
    private final String mainpageViewLocation = "/views/MainPage.fxml";
    public static String uname;
    private String upassword;
    private String uemail;
    private FileChooser fileChooser;
    private MenuItem profilePictureItem;
    private String path;
    private File file;

    @FXML
    private void handleLogin(ActionEvent _event) {
        uname = this.username.getText();
        upassword = this.password.getText();
        if (User.loginInformationAcceptable(uname, upassword)) {
            Parent root;
            try {
                getCurrentStage().close();
                this.mainPageStage = new Stage();
                root = FXMLLoader.load(getClass().getResource(this.mainpageViewLocation));
                this.scene = new Scene(root);
                this.mainPageStage.setScene(scene);
                this.mainPageStage.show();
                //EmailValidation validate = new EmailValidation("hsa.2244@gmail.com");
                //System.out.println(validate);
                this.currentStage = this.registerStage;
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("ops!");
        }
    }

    @FXML
    private void handleRegister(ActionEvent _event) {
        System.out.println("Register button clicked");
        Parent root;
        try {
            //getCurrentStage().close();
            this.registerStage = new Stage();
            //FXMLDocumentController.currentStage = FXMLDocumentController.registerStage;
            root = FXMLLoader.load(getClass().getResource(this.registerViewLocation));
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
    private void handleSubmit(ActionEvent _event) {
        System.out.println("Submit button clicked");
        this.uname = this.username.getText();
        this.upassword = this.password.getText();
        this.uemail = this.email.getText();
        String favArtist = "";
        String profilePic = "";
        User.usernameAcceptable(uname, upassword, uemail, favArtist, profilePic);
    }

    @FXML
    private void handleChangePicture(ActionEvent _event) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        file = fileChooser.showOpenDialog(browseStage);
        if (file != null) {
            path = file.getAbsolutePath();
            User tempUser = new User();
            tempUser.changeProfilePicture(uname, path);
            tempUser.getProfilePicture();
            profilePicture();
        } else {
            System.out.println("kys1");
        }
    }

    private void profilePicture() {
        if (this.profilePicture != null) {
            User tempUser = new User();
            String pathFromUser = tempUser.getProfilePicture();
            File temp = new File(pathFromUser);
            boolean exists = temp.exists();
            if ("".equals(pathFromUser) || !exists) {
                Image image1 = new Image("/styles/lana.jpg");
                this.imageView = new ImageView(image1);
            } else {
                this.image = new Image("file:///" + pathFromUser);
                this.imageView = new ImageView(this.image);
            }

            this.imageView.setFitWidth(150);
            this.imageView.setFitHeight(80);
            this.imageView.setPreserveRatio(true);
            this.profilePicture.setCenter(this.imageView);
        }
    }

    private void favoriteArtistPicture() {
        if (this.bannerartist != null) {
            User tempUser = new User();
            String pathFromUser = tempUser.getFavoriteArtist();
            String nameWithoutSpaces = pathFromUser.replaceAll(" ", "%20");
            //Genius banner = new Genius(nameWithoutSpaces);
            //String imagee = banner.artistBanner();
            //WebEngine web2 = bannerartist.getEngine();
            //web2.load(imagee);
        }
    }

    public static Stage getCurrentStage() {
        return FXMLDocumentController.currentStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLDocumentController.currentStage = bluey.Bluey.newStage;
        favoriteArtistPicture();
        profilePicture();
        if (this.name != null) {
            this.name.setText("Hello " + uname);
        }

    }
}
