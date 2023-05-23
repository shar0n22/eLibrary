package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class vController implements Initializable{

	 @FXML
	 private TableView<bookdet> btable;

    @FXML
    private TableColumn<bookdet, Integer> bid;

    @FXML
    private TableColumn<bookdet, String> bname;

    @FXML
    private TableColumn<bookdet, String> bauthor;

    @FXML
    private TableColumn<bookdet, Integer> byear;
    
    @FXML
    private Button back;

    @FXML
    void back(ActionEvent event) {

    }
    
    ObservableList<bookdet> list = FXCollections.observableArrayList(
    		new bookdet(100, "A Tale of two Cities", "asff", 1234)
    		);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bid.setCellValueFactory(new PropertyValueFactory<bookdet, Integer>("bid"));
		bname.setCellValueFactory(new PropertyValueFactory<bookdet, String>("bname"));
		bauthor.setCellValueFactory(new PropertyValueFactory<bookdet, String>("bauthor"));
		byear.setCellValueFactory(new PropertyValueFactory<bookdet, Integer>("byear"));
		
		btable.setItems(list);
		
	}

}

