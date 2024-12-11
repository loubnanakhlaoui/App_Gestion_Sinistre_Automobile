package sgsa;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static sgsa.getData.username;
public class dashboardController implements Initializable {
   @FXML
    private Label RuleId;

    @FXML
    private TableColumn<RuleData,String> RuleId_col;

    @FXML
    private TextField SearchPart;

    @FXML
    private Button addCarPart_btn;

    @FXML
    private Button addClient_btn;

    @FXML
    private ImageView addImagePart;

    @FXML
    private TextField addInsured_InsuredID;

    @FXML
    private Button addInsured_addBtn;

    @FXML
    private Button addInsured_clearBtn;

    @FXML
    private TableColumn<InsuredData,String> addInsured_col_InsuredID;

    @FXML
    private TableColumn<InsuredData,String> addInsured_col_dateMember;

    @FXML
    private TableColumn<InsuredData,String> addInsured_col_firstName;

    @FXML
    private TableColumn<InsuredData,String> addInsured_col_gender;

    @FXML
    private TableColumn<InsuredData,String> addInsured_col_lastName;

    @FXML
    private TableColumn<InsuredData,String> addInsured_col_phone;

    @FXML
    private TableView<InsuredData> addInsured_col_tableView;

    @FXML
    private Button addInsured_deleteBtn;

    @FXML
    private TextField addInsured_firstName;

    @FXML
    private AnchorPane addInsured_form;

    @FXML
    private ComboBox<?> addInsured_gender;

    @FXML
    private ImageView addInsured_image;

    @FXML
    private Button addInsured_importBtn;
@FXML
    private Button addPart_importBtn;

    @FXML
    private TextField addInsured_lastName;

    @FXML
    private TextField addInsured_phone;

    @FXML
    private TextField addInsured_search;

    @FXML
    private Button addInsured_updateBtn;

    @FXML
    private TableColumn<PartCarData,String> addPartId_col;

    @FXML
    private TableColumn<PartCarData,String> addPartName_col;

    @FXML
    private TextField addPart_PartID;

    @FXML
    private TextField addPart_PartName;

    @FXML
    private TextField addPart_SupplierName;

    @FXML
    private TextField addPart_SupplierPhone;

    @FXML
    private Button addPart_btn;

    @FXML
    private AnchorPane addPart_form;

    @FXML
    private TableView<PartCarData> addPart_tableView;

    @FXML
    private Button addRule_btn;

    @FXML
    private AnchorPane addRule_form;

    @FXML
    private TableColumn<PartCarData,String> addSupplierName_col;

    @FXML
    private TableColumn<PartCarData,String> addSupplierPhone_col;

    @FXML
    private Button clearPart_btn;

    @FXML
    private Button close;

    @FXML
    private Label contenantRule;

    @FXML
    private TableColumn<RuleData,String> contenantRule_col;

    @FXML
    private Button deletePart_btn;

    @FXML
    private Label homeTotalInsured_form;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Label nameRule;

    @FXML
    private TableColumn<RuleData,String> nameRule_col;

    @FXML
    private Button updatePart_btn;
    
    @FXML
    private TextField SearchRule;

    

   
    @FXML
    private TextField addRule_RuleID;

   
    @FXML
    private TextField addRule_content;

   

    @FXML
    private TextField addRule_name;

    @FXML
    private TableView<RuleData> addRule_tableView;

    
    
    @FXML
    private Button clearRule_btn;

    @FXML
    private Button deleteRule_btn;

    @FXML
    private Button updateRule_btn;
    private double x = 0;
    private double y = 0;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Image image;
    
    public void homeTotalInsureds() {

        String sql = "SELECT COUNT(id) FROM insureddata";

        connect = database.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            homeTotalInsured_form.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void addInsuredAdd() {
    Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "INSERT INTO insureddata "
                + "(insured_id,firstName,lastName,gender,phoneNum,image,date) "
                + "VALUES(?,?,?,?,?,?,?)";

       


        connect = database.connectDb();

        try {
            Alert alert;
            if (addInsured_InsuredID.getText().isEmpty()
                    || addInsured_firstName.getText().isEmpty()
                    || addInsured_lastName.getText().isEmpty()
                    || addInsured_gender.getSelectionModel().getSelectedItem() == null
                    || addInsured_phone.getText().isEmpty()             
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT insured_id FROM insureddata WHERE insured_id = '"
                        + addInsured_InsuredID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Insured ID: " + addInsured_InsuredID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addInsured_InsuredID.getText());
                    prepare.setString(2, addInsured_firstName.getText());
                    prepare.setString(3, addInsured_lastName.getText());
                    prepare.setString(4, (String) addInsured_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addInsured_phone.getText());
                    

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(6, uri);
                    prepare.setString(7, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                   
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                   addInsuredShowListData();
                    addInsuredReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   
    public void addInsuredUpdate() {

        String uri = getData.path;
        if (uri != null) {
    uri = uri.replace("\\", "\\\\");
}

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE insureddata SET firstName = '"
                + addInsured_firstName.getText() + "', lastName = '"
                + addInsured_lastName.getText() + "', gender = '"
                + addInsured_gender.getSelectionModel().getSelectedItem() + "', phoneNum = '"
                + addInsured_phone.getText() + "', image = '"
                + uri + "', date = '" + sqlDate + "' WHERE insured_id ='"
                + addInsured_InsuredID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addInsured_InsuredID.getText().isEmpty()
                    || addInsured_firstName.getText().isEmpty()
                    || addInsured_lastName.getText().isEmpty()
                    || addInsured_gender.getSelectionModel().getSelectedItem() == null
                    || addInsured_phone.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Insured ID: " + addInsured_InsuredID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addInsuredShowListData();
                    addInsuredReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addInsuredDelete() {

    String sql = "DELETE FROM insureddata WHERE insured_id = '"
            + addInsured_InsuredID.getText() + "'";

    connect = database.connectDb();

    try {

        Alert alert;
        if (addInsured_InsuredID.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the Insured ID field");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Insured ID: " + addInsured_InsuredID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                addInsuredShowListData();
                addInsuredReset();
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void addInsuredReset() {
        addInsured_InsuredID.setText("");
        addInsured_firstName.setText("");
        addInsured_lastName.setText("");
        addInsured_gender.getSelectionModel().clearSelection();   
       addInsured_phone.setText("");
        addInsured_image.setImage(null);
        getData.path = "";
    }
    
    private String[] listGender = {"Male", "Female"};
    public void addInsuredGendernList() {
        List<String> listG = new ArrayList<>();

        for (String data : listGender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        addInsured_gender.setItems(listData);
    }
    
     public void addInsuredInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 108, 119, false, true);
            addInsured_image.setImage(image);
        }
    }
   public void addInsuredSearch() {
    FilteredList<InsuredData> filter = new FilteredList<>(addInsuredList, e -> true);

    addInsured_search.textProperty().addListener((Observable, oldValue, newValue) -> {
        filter.setPredicate(predicateInsuredData -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String searchKey = newValue.toLowerCase();

            return predicateInsuredData.getInsured_id().toLowerCase().contains(searchKey) ||
                   predicateInsuredData.getFirstName().toLowerCase().contains(searchKey) ||
                   predicateInsuredData.getLastName().toLowerCase().contains(searchKey) ||
                   predicateInsuredData.getGender().toLowerCase().contains(searchKey) ||
                   predicateInsuredData.getPhoneNum().toLowerCase().contains(searchKey) ||
                   predicateInsuredData.getDate().toString().toLowerCase().contains(searchKey);
        });

        
        SortedList<InsuredData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addInsured_col_tableView.comparatorProperty());
        addInsured_col_tableView.setItems(sortList);
    });
}



     
     public ObservableList<InsuredData> addInsuredListData() {

        ObservableList<InsuredData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM insureddata";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            InsuredData InsuredD;

            while (result.next()) {
                InsuredD = new InsuredData(
                        result.getString("insured_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("phoneNum"),
                        result.getString("image"),
                        result.getDate("date"));
                listData.add(InsuredD);

            }

        } catch (Exception e) {e.printStackTrace();}
        return listData;
    }
     
     private ObservableList<InsuredData> addInsuredList;  
     public void addInsuredShowListData() {
     addInsuredList=addInsuredListData();
     addInsured_col_InsuredID.setCellValueFactory(new PropertyValueFactory<>("insured_id"));
     addInsured_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
     addInsured_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
     addInsured_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
     addInsured_col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
     addInsured_col_dateMember.setCellValueFactory(new PropertyValueFactory<>("date"));
     addInsured_col_tableView.setItems(addInsuredList);
     
     addInsured_col_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Mettez à jour votre ImageView avec la nouvelle image sélectionnée
            updateImageView(newSelection.getImage());
        }
    });

             }
      public void addInsuredSelect() {
    InsuredData InsuredD = addInsured_col_tableView.getSelectionModel().getSelectedItem();
    int num = addInsured_col_tableView.getSelectionModel().getSelectedIndex();
    if ((num - 1) < -1) {
        return;
    }

    addInsured_InsuredID.setText(String.valueOf(InsuredD.getInsured_id()));
    addInsured_firstName.setText(InsuredD.getFirstName());
    addInsured_lastName.setText(InsuredD.getLastName());
    addInsured_phone.setText(InsuredD.getPhoneNum());

    getData.path = InsuredD.getImage();

    String uri = "file:" + InsuredD.getImage();

    image = new Image(uri, 108, 119, false, true);
    addInsured_image.setImage(image);

    // Mettez à jour l'ImageView lors de la sélection
    updateImageView(InsuredD.getImage());
}


     public void displayUsername() {
        username.setText(getData.username);
    }
 public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addInsured_form.setVisible(false);
            addPart_form.setVisible(false);
            addRule_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            addCarPart_btn.setStyle("-fx-background-color:transparent");
            addRule_btn.setStyle("-fx-background-color:transparent");
             
            homeTotalInsureds();
            

        } else if (event.getSource() == addClient_btn) {
            home_form.setVisible(false);
            addInsured_form.setVisible(true);
            addPart_form.setVisible(false);
            addRule_form.setVisible(false);
            addClient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            home_btn.setStyle("-fx-background-color:transparent");
            addCarPart_btn.setStyle("-fx-background-color:transparent");
            addRule_btn.setStyle("-fx-background-color:transparent");
          addInsuredGendernList();
          addInsuredSearch();
          
        } else if (event.getSource() == addCarPart_btn) {
            home_form.setVisible(false);
            addInsured_form.setVisible(false);
            addPart_form.setVisible(true);
            addRule_form.setVisible(false);
            addCarPart_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            addRule_btn.setStyle("-fx-background-color:transparent");

            

        }
    else if (event.getSource() == addRule_btn) {
            home_form.setVisible(false);
            addInsured_form.setVisible(false);
           addRule_form.setVisible(true);
           addPart_form.setVisible(false);
            addRule_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addClient_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
           addCarPart_btn.setStyle("-fx-background-color:transparent");

          

        }
    }  
    public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void addPartdAdd() {
        String sql = "INSERT INTO partcardata"
                + "(part_id,part_name,supplier_name,supplier_phone,imagePart) "
                + "VALUES(?,?,?,?,?)";

       


        connect = database.connectDb();

        try {
            Alert alert;
            if (addPart_PartID.getText().isEmpty()
                    || addPart_PartName.getText().isEmpty()
                    || addPart_SupplierName.getText().isEmpty()
                    || addPart_SupplierPhone.getText().isEmpty()             
                    || getData.path == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT part_id FROM partcardata WHERE part_id = '"
                        + addPart_PartID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Part ID: " + addPart_PartID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addPart_PartID.getText());
                    prepare.setString(2, addPart_PartName.getText());
                    prepare.setString(3, addPart_SupplierName.getText());
                    prepare.setString(4, addPart_SupplierPhone.getText());
                    
                   String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(5, uri);
                    prepare.executeUpdate();

                   
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                   addPartShowListData();
                    addPartReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   
    public void addPartUpdate() {

        String uri = getData.path;
        if (uri != null) {
    uri = uri.replace("\\", "\\\\");
}

       
        String sql = "UPDATE partcardata SET part_name= '"
                + addPart_PartName.getText() + "', supplier_name = '"
                + addPart_SupplierName.getText() + "', supplier_phone = '"
                + addPart_SupplierPhone.getText() + "', imagePart = '"
                + uri +  "' WHERE part_id ='"
                + addPart_PartID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addPart_PartID.getText().isEmpty()
                    || addPart_PartName.getText().isEmpty()
                    || addPart_SupplierName.getText().isEmpty()
                    || addPart_SupplierPhone.getText().isEmpty()
                    || getData.path == null ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Part ID: " + addPart_PartID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addPartShowListData();
                    addPartReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addPartDelete() {

    String sql = "DELETE FROM partcardata WHERE part_id = '"
            + addPart_PartID.getText() + "'";

    connect = database.connectDb();

    try {

        Alert alert;
        if (addPart_PartID.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the Part ID field");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Part ID: " + addPart_PartID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                addPartShowListData();
                addPartReset();
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void addPartReset() {
        addPart_PartID.setText("");
        addPart_PartName.setText("");
        addPart_SupplierName.setText("");
        addPart_SupplierPhone.setText("");
        addImagePart.setImage(null);
        
    }
    
   
    
     public void addPartInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 108, 119, false, true);
            addImagePart.setImage(image);
        }
    }
   public void addPartSearch() {
    FilteredList<PartCarData> filter = new FilteredList<>(addPartList, e -> true);

    SearchPart.textProperty().addListener((Observable, oldValue, newValue) -> {
        filter.setPredicate(predicatePartCarData -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String searchKey = newValue.toLowerCase();

            return predicatePartCarData.getPart_id().toLowerCase().contains(searchKey) ||
                   predicatePartCarData.getPart_name().toLowerCase().contains(searchKey) ||
                   predicatePartCarData.getSupplier_name().toLowerCase().contains(searchKey) ||
                   predicatePartCarData.getSupplier_phone().toLowerCase().contains(searchKey);
                   });

        // Mettez à jour manuellement le prédicat pour déclencher la mise à jour de la TableView
        SortedList<PartCarData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addPart_tableView.comparatorProperty());
        addPart_tableView.setItems(sortList);
    });
}



     
     public ObservableList<PartCarData> addPartListData() {

        ObservableList<PartCarData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM partcardata";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            PartCarData partD;

            while (result.next()) {
                partD = new PartCarData(
                        result.getString("part_id"),
                        result.getString("part_name"),
                        result.getString("supplier_name"),
                        result.getString("supplier_phone"),
                        result.getString("imagePart"));
                     
                listData.add(partD);

            }

        } catch (Exception e) {e.printStackTrace();}
        return listData;
    }
     
     private ObservableList<PartCarData> addPartList;  
     public void addPartShowListData() {
     addPartList=addPartListData();
     addPartId_col.setCellValueFactory(new PropertyValueFactory<>("part_id"));
     addPartName_col.setCellValueFactory(new PropertyValueFactory<>("part_name"));
     addSupplierName_col.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
     addSupplierPhone_col.setCellValueFactory(new PropertyValueFactory<>("supplier_phone"));
     addPart_tableView.setItems(addPartList);
     
     addPart_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            updateImageViewPart(newSelection.getImageP());
        }
    });

             }
      public void addPartSelect() {
    PartCarData partD = addPart_tableView.getSelectionModel().getSelectedItem();
    int num = addPart_tableView.getSelectionModel().getSelectedIndex();
    if ((num - 1) < -1) {
        return;
    }

    addPart_PartID.setText(String.valueOf(partD.getPart_id()));
    addPart_PartName.setText(partD.getPart_name());
    addPart_SupplierName.setText(partD.getSupplier_name());
    addPart_SupplierPhone.setText(partD.getSupplier_phone());

    getData.path = partD.getImageP();

    String uri = "file:" + partD.getImageP();

    image = new Image(uri, 108, 119, false, true);
    addImagePart.setImage(image);

    // Mettez à jour l'ImageView lors de la sélection
    updateImageViewPart(partD.getImageP());
}


    
   

   private void updateImageViewPart(String imagePath) {
    if (imagePath != null && !imagePath.isEmpty()) {
        // Charger l'image à partir du chemin et définir dans votre ImageView
        Image imagepart = new Image("file:" + imagePath);
        addImagePart.setImage(imagepart);
    } else {
        // Effacer l'image si le chemin est vide
        addImagePart.setImage(null);
    }
}
   
   public void addRuledAdd() {
        String sql = "INSERT INTO ruledata"
                + "(rule_id,name_rule,content_rule) "
                + "VALUES(?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addRule_RuleID.getText().isEmpty()
                    || addRule_name.getText().isEmpty()
                    || addRule_content.getText().isEmpty()
                      ){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT rule_id FROM ruledata WHERE rule_id = '"
                        +addRule_RuleID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Rule ID: " + addRule_RuleID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addRule_RuleID.getText());
                    prepare.setString(2, addRule_name.getText());
                    prepare.setString(3, addRule_content.getText());
                   
                    prepare.executeUpdate();

                   
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                   addRuleShowListData();
                    addRuleReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   
    public void addRuleUpdate() {

        String sql = "UPDATE ruledata SET name_rule= '"
                + addRule_name.getText() + "', content_rule = '"
                + addRule_content.getText() + "' WHERE rule_id ='"
                + addRule_RuleID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addRule_RuleID.getText().isEmpty()
                    || addRule_name.getText().isEmpty()
                    || addRule_content.getText().isEmpty()
                    ){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Rule ID: " + addRule_RuleID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addRuleShowListData();
                    addRuleReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addRuleDelete() {

    String sql = "DELETE FROM ruledata WHERE rule_id = '"
            + addRule_RuleID.getText() + "'";

    connect = database.connectDb();

    try {

        Alert alert;
        if (addRule_RuleID.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the Rule ID field");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Rule ID: " + addRule_RuleID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                addRuleShowListData();
                addRuleReset();
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void addRuleReset() {
        addRule_RuleID.setText("");
        addRule_name.setText("");
        addRule_content.setText("");
        
    }
    
   
    
     
   public void addRuleSearch() {
    FilteredList<RuleData> filter = new FilteredList<>(addRuleList, e -> true);

    SearchRule.textProperty().addListener((Observable, oldValue, newValue) -> {
        filter.setPredicate(predicateRuleData -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String searchKey = newValue.toLowerCase();

            return predicateRuleData.getRule_id().toLowerCase().contains(searchKey) ||
                   predicateRuleData.getName_rule().toLowerCase().contains(searchKey) ||
                   predicateRuleData.getContent_rule().toLowerCase().contains(searchKey) 
                  ;
                   });

        // Mettez à jour manuellement le prédicat pour déclencher la mise à jour de la TableView
        SortedList<RuleData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addRule_tableView.comparatorProperty());
        addRule_tableView.setItems(sortList);
    });
}



     
     public ObservableList<RuleData> addRuleListData() {

    ObservableList<RuleData> listData = FXCollections.observableArrayList();
    String sql = "SELECT * FROM ruledata";

    connect = database.connectDb();

    try {
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        RuleData ruleD;

        while (result.next()) {
            ruleD = new RuleData(
                    result.getString("rule_id"),
                    result.getString("name_rule"),
                    result.getString("content_rule")
            );
            listData.add(ruleD);
        }
    }  catch (Exception e) {
        e.printStackTrace();
    }
    return listData;
}

     
     private ObservableList<RuleData> addRuleList;  
     public void addRuleShowListData() {
     addRuleList=addRuleListData();
     RuleId_col.setCellValueFactory(new PropertyValueFactory<>("rule_id"));
    nameRule_col.setCellValueFactory(new PropertyValueFactory<>("name_rule"));
     contenantRule_col.setCellValueFactory(new PropertyValueFactory<>("content_rule"));
     addRule_tableView.setItems(addRuleList);
     }
      public void addRuleSelect() {
    RuleData ruleD = addRule_tableView.getSelectionModel().getSelectedItem();
    int num = addRule_tableView.getSelectionModel().getSelectedIndex();
    if ((num - 1) < -1) {
        return;
    }

    addRule_RuleID.setText(String.valueOf(ruleD.getRule_id()));
    addRule_name.setText(ruleD.getName_rule());
    addRule_content.setText(ruleD.getContent_rule());
}

   
   
   
   
     public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
   

   private void updateImageView(String imagePath) {
    if (imagePath != null && !imagePath.isEmpty()) {
        // Charger l'image à partir du chemin et définir dans votre ImageView
        Image imagee = new Image("file:" + imagePath);
        addInsured_image.setImage(imagee);
    } else {
        // Effacer l'image si le chemin est vide
        addInsured_image.setImage(null);
    }
}
   
    
   
   
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeTotalInsureds();
         addInsuredShowListData();
         addInsuredGendernList();
         addPartShowListData();
         addRuleShowListData(); 
         addInsured_col_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
     if (newSelection != null) {
         addInsuredSelect(); // Appelez la méthode pour mettre à jour les champs de texte et l'image
     }
 });
          
addPart_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
     if (newSelection != null) {
         addPartSelect(); // Appelez la méthode pour mettre à jour les champs de texte et l'image
     }
 });

  addRule_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
     if (newSelection != null) {
         addRuleSelect(); // Appelez la méthode pour mettre à jour les champs de texte et l'image
     }
 });
    }
    
}