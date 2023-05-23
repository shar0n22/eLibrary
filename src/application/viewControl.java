package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class viewControl implements Initializable{
	
//	private Connection con;
//    private PreparedStatement pst;
//    private ResultSet rs;
	private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button back;
    
    @FXML
    private TableView<modelView> btable;
    
    @FXML
    private TableColumn<modelView, String> bid;
    
    @FXML
    private TableColumn<modelView, String> bname;

    @FXML
    private TableColumn<modelView, String> bauthor;

    @FXML
    private TableColumn<modelView, String> byear;

    @FXML
    void back(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }
    
    ObservableList<modelView> oblist = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		Connection con;
		try {
			con = vcon.getConnection();
//			ResultSet rs = con.createStatement().executeQuery("select * from bookDet");
			PreparedStatement pst = con.prepareStatement("select * from bookDet");
            ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
//	            System.out.print(rs.getString("bid") + rs.getString("bname") + rs.getString("bauthor") + rs.getString("byear"));
			    oblist.add(new modelView(rs.getString("bookid"), rs.getString("bookname"), rs.getString("author"), rs.getString("yearPublished")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sharon22052002");
//			pst = con.prepareStatement("select * from bookDet");
//			rs = pst.executeQuery();
//			
//			while (rs.next()) {
//				oblist.add(new modelView(rs.getString("bookid"), rs.getString("bookname"), rs.getString("author"), rs.getString("yearPublished")));
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		bid.setCellValueFactory(new PropertyValueFactory<modelView, String>("bid"));
		bname.setCellValueFactory(new PropertyValueFactory<modelView, String>("bname"));
		bauthor.setCellValueFactory(new PropertyValueFactory<modelView, String>("bauthor"));
		byear.setCellValueFactory(new PropertyValueFactory<modelView, String>("byear"));
		
		btable.setItems(oblist);
		
		
	}

}
