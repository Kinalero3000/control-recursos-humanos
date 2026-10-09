package main.java.com.jgunzalesindustries.control.recursos.humanos.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request.ApplicantRequest;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response.ApplicantResponse;
import main.java.com.jgunzalesindustries.control.recursos.humanos.service.ApplicantService;

public class ClientController {


    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    @FXML private TableView<ApplicantResponse> applicantTable;
    @FXML private TableColumn<ApplicantResponse, String> applicant;
    @FXML private TableColumn<ApplicantResponse, String> firstNameColumn;
    @FXML private TableColumn<ApplicantResponse, String> lastNameColumn;
    @FXML private TableColumn<ApplicantResponse, String> phoneColumn;
    @FXML private TableColumn<ApplicantResponse, String> emailColumn;

   
    @FXML private Label statusLabel;

    private final ApplicantService clientService = new ApplicantService();
    private final SceneManager sceneManager;

    private ApplicantResponse selectedApplicant;

    public ClientController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {

        applicant.setCellValueFactory(new PropertyValueFactory<>("id_applicant"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("names_applicant"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        applicantTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            selectedApplicant = newValue;      
            if (newValue != null) {
                fillForm(newValue);         
            }
        });

        refreshTable();
    }

    
    @FXML
    private void onSave() {
        try {
            ApplicantRequest request = buildRequestFromForm();

            if (selectedApplicant == null) {
                clientService.create(request);
                statusLabel.setText("Cliente registrado correctamente.");
            } else {
                clientService.update(selectedApplicant.getIdApplicant(), request);
                statusLabel.setText("Cliente actualizado correctamente.");
            }

            clearForm();     
            refreshTable(); 

        } catch (IllegalArgumentException e) {
            statusLabel.setText(e.getMessage());
        } catch (RuntimeException e) {
            statusLabel.setText("Ocurrió un error al guardar. Revisa la conexión a la base de datos.");
        }
    }

    @FXML
    private void onDelete() {
        if (selectedApplicant == null) {
            statusLabel.setText("Selecciona un cliente de la tabla para eliminar.");
            return;
        }
        try {
            clientService.delete(selectedApplicant.getIdApplicant());
            clearForm();
            refreshTable();
            statusLabel.setText("Cliente eliminado.");
        } catch (IllegalArgumentException e) {
            statusLabel.setText(e.getMessage());
        } catch (RuntimeException e) {
            statusLabel.setText("No se pudo eliminar el cliente.");
        }
    }

    @FXML
    private void onClear() {
        clearForm();
    }

    @FXML
    private void onLogout() {
        try {
            sceneManager.showLoginView();
        } catch (Exception e) {
            statusLabel.setText("No se pudo volver al login: " + e.getMessage());
        }
    }


    private void refreshTable() {
        try {
            ObservableList<ApplicantResponse> data =
                    FXCollections.observableArrayList(clientService.list());
            applicantTable.setItems(data);
        } catch (RuntimeException e) {
            statusLabel.setText("No se pudo cargar la lista. Revisa la conexión a la base de datos.");
        }
    }

    private void fillForm(ApplicantResponse a) {
        applicant.setText(a.getIdApplicant());
        firstNameField.setText(a.getNamesApplicant());
        lastNameField.setText(a.getLastname());
        phoneField.setText(a.getPhone());
        emailField.setText(a.getEmail());
        addressField.setText(a.getAdressId());
    }

    private void clearForm() {
        selectedApplicant = null;                              
        applicantTable.getSelectionModel().clearSelection();   
        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
    }

    private ApplicantRequest buildRequestFromForm() {
        ApplicantRequest request = new ApplicantRequest();
        request.setNamesApplicant(textOf(firstNameField));
        request.setLastName(textOf(lastNameField));
        request.setPhone(textOf(phoneField));
        request.setEmail(textOf(emailField));
        request.setAdressId(textOf(addressField));
        return request;
    }

    /** getText() puede ser null; esto lo convierte en "" y quita espacios sobrantes. */
    private String textOf(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }
}


