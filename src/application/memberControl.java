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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class memberControl {
	
	private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection con1, con2;
    private PreparedStatement pst1, pst2, pst3;
    private ResultSet rs1, rs2, rst3;


    @FXML
    private Button nback;

    @FXML
    private TextField nid;

    @FXML
    private TextField nname;

    @FXML
    private TextField npass;

    @FXML
    private Button nsignin;

    @FXML
    void nback(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void nsignin(ActionEvent event) {
        String userid = nid.getText();
        String username = nname.getText();
        String password = npass.getText();
        if(userid.equals("") && username.equals("") && password.equals("")) {
    		JOptionPane.showMessageDialog(null, "Please Enter all the Details!");
    	}
        else {
        	try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    			pst1 = con1.prepareStatement("select * from userDet where userid=?");
    			pst1.setString(1, userid);
    			rs1 = pst1.executeQuery();
    			
    			if(rs1.next()) {
    				JOptionPane.showMessageDialog(null, "User already exists! Please change your User ID");
    				
    				
    				
    			}
    			else {
    				try {
    					Class.forName("com.mysql.cj.jdbc.Driver");
    					con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    					pst2 = con1.prepareStatement("insert into userDet(userid, username, userpassword)values(?, ?, ?)");
    					pst2.setString(1, userid);
    					pst2.setString(2, username);
    					pst2.setString(3, password);
    					
    					int nstatus = pst2.executeUpdate();
    					
    					if (nstatus == 1) {
    						JOptionPane.showMessageDialog(null, "SignIn Successfull!");
    						nid.setText("");
    						nname.setText("");
    						npass.setText("");
    						
    						nid.requestFocus();
    					}
    					else {
    						JOptionPane.showMessageDialog(null, "Sorry, couldn't SignIn!");
    					}
    				} catch (ClassNotFoundException | SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			
    			
    		} catch (ClassNotFoundException e) {
    			Logger.getLogger(nsignin.getClass().getName()).log(Level.SEVERE, null, e);
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

}

