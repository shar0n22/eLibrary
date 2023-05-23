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

public class issueControl {
	
	private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection con1, con2;
    private PreparedStatement pst1, pst2, pst3;
    private ResultSet rs1, rs2, rst3;

    @FXML
    private TextField iauthor;

    @FXML
    private Button iback;

    @FXML
    private TextField ibid;

    @FXML
    private Button iissue;

    @FXML
    private TextField iname;

    @FXML
    private TextField iuid;

    @FXML
    void iback(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void issue(ActionEvent event) throws IOException{
        String userid = iuid.getText();
        String bookid = ibid.getText();
        String bookname = iname.getText();
        String author = iauthor.getText();
        
        if(userid.equals("") && bookid.equals("") && bookname.equals("") && author.equals("")) {
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
    				
//    				JOptionPane.showMessageDialog(null, "haha");
    				try {
    	    			Class.forName("com.mysql.cj.jdbc.Driver");
    	    			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    	    			pst2 = con1.prepareStatement("select * from bookDet where bookid=?");
    	    			pst2.setString(1, bookid);
    	    			rs2 = pst2.executeQuery();
    	    			
    	    			if(rs2.next()) {
    	    				
//    	    				JOptionPane.showMessageDialog(null, "bruh");
    	    				try {
    	    					Class.forName("com.mysql.cj.jdbc.Driver");
    	    					con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    	    					pst3 = con1.prepareStatement("insert into issueDet(userid, bookid, bookname, author)values(?, ?, ?, ?)");
    	    					pst3.setString(1, userid);
    	    					pst3.setString(2, bookid);
    	    					pst3.setString(3, bookname);
    	    					pst3.setString(4, author);
    	    					int istatus = pst3.executeUpdate();
    	    					
    	    					if (istatus == 1) {
    	    						JOptionPane.showMessageDialog(null, "Book Issued Successfully!");
    	    						iuid.setText("");
    	    						ibid.setText("");
    	    						iname.setText("");
    	    						iauthor.setText("");
    	    						iuid.requestFocus();
    	    					}
    	    					else {
    	    						JOptionPane.showMessageDialog(null, "Book couldn't be issued!");
    	    					}
    	    				} catch (ClassNotFoundException | SQLException e) {
    	    					// TODO Auto-generated catch block
    	    					e.printStackTrace();
    	    				}
    	    				
    	    				
    	    			}
    	    			else {
    	    				JOptionPane.showMessageDialog(null, "Book is not available!");
    	    				iuid.setText("");
    	    				ibid.setText("");
    	    				iname.setText("");
    	    				iauthor.setText("");
    	    				iuid.requestFocus();
    	    			}
    	    			
    	    			
    	    		} catch (ClassNotFoundException e) {
    	    			Logger.getLogger(iissue.getClass().getName()).log(Level.SEVERE, null, e);
    	    		} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
    				
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "User does not exist!");
    				iuid.setText("");
    				ibid.setText("");
    				iname.setText("");
    				iauthor.setText("");
    				iuid.requestFocus();
    			}
    			
    			
    		} catch (ClassNotFoundException e) {
    			Logger.getLogger(iissue.getClass().getName()).log(Level.SEVERE, null, e);
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }

}

