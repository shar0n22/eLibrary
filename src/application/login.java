package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class login {
	
    @FXML
    private Button cancel;

    @FXML
    private Button login;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField uname;
    
    @FXML
    private AnchorPane scenePane;
    
    @FXML
    private Hyperlink sid;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void cancel(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginid(ActionEvent event) throws IOException {
//    	System.out.println("login successfuly");
//    	JOptionPane.showMessageDialog(null, "login successfull");4
    	String username = uname.getText();
    	String password = pass.getText();
    	
    	if(username.equals("") && password.equals("")) {
    		JOptionPane.showMessageDialog(null, "Please Enter Username and Password!");
    	}
    	else {
    		try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    			pst = con.prepareStatement("select * from userDet where username=? and userpassword=?");
    			pst.setString(1, username);
    			pst.setString(2, password);
    			rs = pst.executeQuery();
    			
    			if(rs.next()) {
//    				JOptionPane.showMessageDialog(null, "Login Success");
    				root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
    				stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    				scene = new Scene(root);
    				stage.setScene(scene);
    				stage.show();
    				
    				
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "Username or Password entered is not correct");
    				uname.setText("");
    				pass.setText("");
    				uname.requestFocus();
    			}
    			
    			
    		} catch (ClassNotFoundException e) {
    			Logger.getLogger(login.getClass().getName()).log(Level.SEVERE, null, e);
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }
    
    @FXML
    void signin(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("newMember.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

   

}