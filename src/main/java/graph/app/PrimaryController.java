package graph.app;

import java.io.File;

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
                formModel.setFileSize(bytes/1000000);

                fileSelectionLabel.setText(file.getAbsolutePath() + " choosen");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        try {
            formModel.setNodes(Long.parseLong(nodesTextField.getText()));
            formModel.setEdges(Long.parseLong(edgesTextField.getText()));
            formModel.setAlgorithm(algorithComboBox.getValue());
            if (iterationTextField.isVisible()) {
                formModel.setIterations(Integer.parseInt(iterationTextField.getText()));
            } else {
                formModel.setIterations(-1);
            }
            System.out.println(formModel);
            ModelResponse modelResponse = ModelResponse.getInstance();
            FormModel newFormModel = new FormModel(formModel.getFileName(), formModel.getVolumePath(),
                    formModel.getNodes(), formModel.getEdges(), formModel.getAlgorithm(), formModel.getIterations() ,formModel.getFileSize());
            modelResponse.setFramework("MMAP");
            modelResponse.setFormModel(newFormModel);
            App.setRoot("secondary");
        } catch (Exception e) {
            e.printStackTrace();
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


