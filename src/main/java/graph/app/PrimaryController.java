package graph.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import enums.AlgorithmEnum;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.FormModel;
import models.ModelResponse;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PrimaryController implements Initializable {
    private FileChooser fileChooser = new FileChooser();
    private FormModel formModel = new FormModel();

    @FXML
    private Label fileSelectionLabel;
    @FXML
    private Button chooseFileButton;
    @FXML
    private TextField nodesTextField;
    @FXML
    private TextField edgesTextField;
    @FXML
    private ComboBox<AlgorithmEnum> algorithComboBox;
    @FXML
    private TextField iterationTextField;
    @FXML
    private Label iterationLabel;
    @FXML
    private Button submitButton;

    @FXML
    private void handleFileChooseAction(ActionEvent event) {
        try {
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                formModel.setFileName(file.getName());
                formModel.setVolumePath(file.getParent());
                Path path = Paths.get(file.getAbsolutePath());
                long bytes = Files.size(path);
                formModel.setFileSize(bytes / 1000000);

                fileSelectionLabel.setText(file.getAbsolutePath());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        try {
            // TODO : computer config
            formModel.setNodes(Long.parseLong(nodesTextField.getText()));
            formModel.setEdges(Long.parseLong(edgesTextField.getText()));
            formModel.setAlgorithm(algorithComboBox.getValue());
            formModel.setCpuNb(Runtime.getRuntime().availableProcessors());
            // long totalRAMSizeBytes = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
            //         .getTotalPhysicalMemorySize();

            // formModel.setRamSize(totalRAMSizeBytes / (1000 * 1000 * 1000));
            if (iterationTextField.isVisible()) {
                formModel.setIterations(Integer.parseInt(iterationTextField.getText()));
            } else {
                formModel.setIterations(-1);
            }
            System.out.println(formModel);
            ModelResponse modelResponse = ModelResponse.getInstance();
            FormModel newFormModel = new FormModel(formModel.getFileName(), formModel.getVolumePath(),
                    formModel.getNodes(), formModel.getEdges(), formModel.getAlgorithm(), formModel.getIterations(),
                    formModel.getFileSize(), formModel.getRamSize(), formModel.getCpuNb());
            // TODO : send HTTP request to our server
            String response=sendPostRequest(newFormModel);
            // TODO : unify this in Lookup
            //convert response to json
                JsonElement jsonElement = JsonParser.parseString(response);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
            
                String framework = jsonObject.get("framework").getAsString();
            System.out.println(framework);
            modelResponse.setFramework(framework);
            modelResponse.setFormModel(newFormModel);
            App.setRoot("secondary");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String sendPostRequest(FormModel formModel) {
        try {
            // Create the URL of your Express.js server
            URL url = new URL("http://localhost:3000/api/submit");
            Gson gson = new Gson();
            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
    
            // Set the request headers if needed
            connection.setRequestProperty("Content-Type", "application/json");
    
            // Create your request body
            // String requestBody = "{\"name\": \"John Doe\", \"age\": 30}";
            String requestBody = gson.toJson(formModel);
    
            // Write the request body
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
            }
    
            // Get the response
            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
    
            // Close the connection
            connection.disconnect();
    
            // Do something with the response
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return(e.toString());
        }
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        algorithComboBox.getItems().setAll(AlgorithmEnum.values());
        iterationLabel.setVisible(false);
        iterationTextField.setVisible(false);
        algorithComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AlgorithmEnum>() {

            @Override
            public void changed(ObservableValue<? extends AlgorithmEnum> arg0, AlgorithmEnum oldAlgorithm,
                    AlgorithmEnum newAlgorithm) {
                if (oldAlgorithm != null) {
                    formModel.setAlgorithm(oldAlgorithm);
                    if (oldAlgorithm == AlgorithmEnum.PAGE_RANK) {
                        iterationTextField.setVisible(true);
                        iterationLabel.setVisible(true);
                    } else {
                        iterationTextField.setVisible(false);
                        iterationLabel.setVisible(false);
                    }
                }
                if (newAlgorithm != null) {
                    formModel.setAlgorithm(newAlgorithm);
                    if (newAlgorithm == AlgorithmEnum.PAGE_RANK) {
                        iterationTextField.setVisible(true);
                        iterationLabel.setVisible(true);
                    } else {
                        iterationTextField.setVisible(false);
                        iterationLabel.setVisible(false);
                    }
                }
            }
        });
    }
}
