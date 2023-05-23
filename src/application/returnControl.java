package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class returnControl implements Initializable{
	
	private Stage stage;
    private Scene scene;
    private Parent root;
    private Connection con1, con2;
    private PreparedStatement pst1, pst2, pst3;
    private ResultSet rs1, rs2, rst3;

    @FXML
    private Button rback;

    @FXML
    private TextField rbid;

    @FXML
    private Button rreturn;

    @FXML
    private TextField ruid;
    
    @FXML
    private TextField rsearch;
    
    @FXML
    private TableView<modelSearch> sftable;
    
    @FXML
    private TableColumn<modelSearch, String> sfauthor;

    @FXML
    private TableColumn<modelSearch, String> sfbid;

    @FXML
    private TableColumn<modelSearch, String> sfname;

    @FXML
    private TableColumn<modelSearch, String> sfuid;
    
    ObservableList<modelSearch> data = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		Connection con;
		try {
			con = vcon.getConnection();
//			ResultSet rs = con.createStatement().executeQuery("select * from bookDet");
			PreparedStatement pst = con.prepareStatement("select * from issueDet");
            ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
//	            System.out.print(rs.getString("bid") + rs.getString("bname") + rs.getString("bauthor") + rs.getString("byear"));
			    data.add(new modelSearch(rs.getString("userid"), rs.getString("bookid"), rs.getString("bookname"), rs.getString("author")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sfuid.setCellValueFactory(new PropertyValueFactory<modelSearch, String>("sfuid"));
		sfbid.setCellValueFactory(new PropertyValueFactory<modelSearch, String>("sfbid"));
		sfname.setCellValueFactory(new PropertyValueFactory<modelSearch, String>("sfname"));
		sfauthor.setCellValueFactory(new PropertyValueFactory<modelSearch, String>("sfauthor"));
		
		sftable.setItems(data);
		
		FilteredList<modelSearch> filteredData = new FilteredList<>(data, b -> true);
		
		rsearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(sbook -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
//					JOptionPane.showMessageDialog(null, "No Matches found!");
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (sbook.getSfname().toLowerCase().indexOf(lowerCaseFilter) != -1){
//				    return true;
//				}
//				else 
//					return false;
//			});
//				
				if (sbook.getSfuid().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (sbook.getSfbid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else if (sbook.getSfname().toLowerCase().indexOf(lowerCaseFilter) != -1){
					return true;
				}
				else if (String.valueOf(sbook.getSfauthor()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		SortedList<modelSearch> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(sftable.comparatorProperty());
		
		sftable.setItems(sortedData);
		
    }

    @FXML
    void rback(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void rreturn(ActionEvent event) {
    	
    	String userid = ruid.getText();
    	String bookid = rbid.getText();
    	

    	if(userid.equals("") && bookid.equals("")) {
    		JOptionPane.showMessageDialog(null, "Please enter all the details!");
    	}
    	else {
    		try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    			pst1 = con1.prepareStatement("select * from issueDet where userid=? and bookid=?");
    			pst1.setString(1, userid);
    			pst1.setString(2, bookid);
    			rs1 = pst1.executeQuery();
    			
    			if(rs1.next()) {
    				try {
    					Class.forName("com.mysql.cj.jdbc.Driver");
    					con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
    					pst2 = con1.prepareStatement("delete from issueDet where userid=? and bookid=?");
    					pst2.setString(1, userid);
    					pst2.setString(2, bookid);
    					int rstatus = pst2.executeUpdate();
    					
    					if (rstatus == 1) {
    						JOptionPane.showMessageDialog(null, "Book Returned Successfully!");
    						ruid.setText("");
    						rbid.setText("");
    						ruid.requestFocus();
    					}
    					else {
    						JOptionPane.showMessageDialog(null, "Book couldn't be returned!");
    					}
    				} catch (ClassNotFoundException | SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				

    				
    				
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "Details entered are not correct!");
    				ruid.setText("");
    				rbid.setText("");
    				ruid.requestFocus();
    			}
    			
    			
    		} catch (ClassNotFoundException e) {
    			Logger.getLogger(rreturn.getClass().getName()).log(Level.SEVERE, null, e);
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	

    }

}

