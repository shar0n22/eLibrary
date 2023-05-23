package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addControl {
	
	private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button aadd;

    @FXML
    private TextField aauthor;

    @FXML
    private Button aback;

    @FXML
    private TextField aid;

    @FXML
    private TextField aname;

    @FXML
    private TextField ayear;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void aadd(ActionEvent event) {
    	String id = aid.getText();
    	String name = aname.getText();
    	String author = aauthor.getText();
    	String year = ayear.getText();
    	
    	if(id.equals("") && name.equals("") && author.equals("") && year.equals("")) {
    		JOptionPane.showMessageDialog(null, "Please Enter all the Details!");
    	}
    	else {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
			pst = con.prepareStatement("insert into bookDet(bookid, bookname, author, yearPublished)values(?, ?, ?, ?)");
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, author);
			pst.setString(4, year);
			int astatus = pst.executeUpdate();
			
			if (astatus == 1) {
				JOptionPane.showMessageDialog(null, "Book Added Successfully!");
				aid.setText("");
				aname.setText("");
				aauthor.setText("");
				ayear.setText("");
				aid.requestFocus();
			}
			else {
				JOptionPane.showMessageDialog(null, "Book couldn't be added!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
		

    }

    @FXML
    void aback(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
