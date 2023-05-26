package graph.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import models.ModelResponse;
import services.DeploySolution;

public class SecondaryController implements Initializable {
    private DeploySolution deploySolution = new DeploySolution() ;
    private String preLabelCssClass = "preLabel";
    @FXML
    private Text commandText ;
    @FXML
    private Label frameworkNameLabel ;
    @FXML
    private Label nodesLabel;
    @FXML 
    private Label edgesLabel;
    @FXML
    private Label algorithmLabel;
    @FXML 
    private Label iterationLabel;
    @FXML
    private Label graphNameLabel;
    @FXML
    private Button deploySolutionButton;
    @FXML
    private Label frameworkNamePreLabel ;
    @FXML
    private Label nodesPreLabel;
    @FXML 
    private Label edgesPreLabel;
    @FXML
    private Label algorithmPreLabel;
    @FXML 
    private Label iterationPreLabel;
    @FXML
    private Label graphNamePreLabel;
    // @FXML 
    // private TextArea commandTextArea;

    @FXML 
    private void handleDeploySolution(ActionEvent event) {
        try {
            Process process = deploySolution.getProcessBuilder().start();
               BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			String existingText =commandText.getText();
            commandText.setText(existingText +"\n"+line);
            System.out.println(line);
            
		}

		int exitVal = process.waitFor();
		if (exitVal == 0) {
			System.out.println("Success!");
			//System.out.println(output);
			//System.exit(0);
		} else {
			//abnormal...
		}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            ModelResponse modelResponse=  ModelResponse.getInstance();
            frameworkNamePreLabel.setText("Recommanded Framework:\t");
            frameworkNamePreLabel.getStyleClass().add(preLabelCssClass);
            frameworkNameLabel.setText(modelResponse.getFramework());

            graphNamePreLabel.setText("Graph Name:\t");
            graphNamePreLabel.getStyleClass().add(preLabelCssClass);
            graphNameLabel.setText(modelResponse.getFormModel().getFileName());

            nodesPreLabel.setText("Number of Nodes:\t");
            nodesPreLabel.getStyleClass().add(preLabelCssClass);
            nodesLabel.setText( String.valueOf(modelResponse.getFormModel().getNodes()));

            edgesPreLabel.setText("Number of Edges:\t");
            edgesPreLabel.getStyleClass().add(preLabelCssClass);
            edgesLabel.setText(String.valueOf(modelResponse.getFormModel().getEdges()));

            algorithmPreLabel.setText("Algorithm to Apply:\t");
            algorithmPreLabel.getStyleClass().add(preLabelCssClass);
            algorithmLabel.setText(modelResponse.getFormModel().getAlgorithm().name());

            int iteration = modelResponse.getFormModel().getIterations();
            if(iteration!=-1) {
                iterationLabel.setText(String.valueOf(iteration));
                iterationPreLabel.getStyleClass().add(preLabelCssClass);
                iterationPreLabel.setText("number of iteration:\t");
            }else {
                iterationLabel.setVisible(false);
                iterationPreLabel.setVisible(false);
            }
            commandText.setText("");
            //System.setOut(new PrintStream( new CustomOutputStream(commandTextArea)));
           
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
}

// class CustomOutputStream extends OutputStream {
//     private TextArea textArea;
//     public CustomOutputStream(TextArea textArea) {
//         this.textArea = textArea;
//     }

//     @Override
//     public void write(int b){
//         textArea.appendText(String.valueOf((char)b));

//     }
// }