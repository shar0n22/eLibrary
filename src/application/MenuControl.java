package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuControl {
	
	private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button abook;

    @FXML
    private Button ibook;

    @FXML
    private Button logout;

    @FXML
    private Button rbook;

    @FXML
    private Button vbook;
    
    @FXML
    private AnchorPane menuScenePane;

    @FXML
    void addBook(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("addBooks.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void issueBook(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("issueBooks.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void logout(ActionEvent event) {
    	stage = (Stage) menuScenePane.getScene().getWindow();
        stage.close();

    }

    @FXML
    void returnBook(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("returnBooks.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void viewBook(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

}
