package graph.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import models.ModelResponse;
import services.DeploySolution;

public class SecondaryController implements Initializable {
  ModelResponse modelResponse = ModelResponse.getInstance();
  private final DeploySolution deploySolution =
      new DeploySolution(modelResponse);
  @FXML private Text commandText;
  @FXML private Label frameworkNameLabel;
  @FXML private Label nodesLabel;
  @FXML private Label edgesLabel;
  @FXML private Label algorithmLabel;
  @FXML private Label iterationLabel;
  @FXML private Label graphNameLabel;
  @FXML private Button deploySolutionButton;
  @FXML private Label frameworkNamePreLabel;
  @FXML private Label expected_timePreLabel;
  @FXML private Label expected_timeLabel;
  @FXML private Label nodesPreLabel;
  @FXML private Label edgesPreLabel;
  @FXML private Label algorithmPreLabel;
  @FXML private Label iterationPreLabel;
  @FXML private Label graphNamePreLabel;
  // @FXML
  // private TextArea commandTextArea;

  @FXML
  private void handleDeploySolution(ActionEvent event) {
    try {
      Process process = deploySolution.getProcessBuilder().start();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(process.getInputStream()));
      String res = "";
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.startsWith("Bad input")) {
          commandText.setText(
              "You did not provide me with a valid input file would you like me to convert it for you ?");
          break;
        }
        if (line.startsWith(
                "docker: Cannot connect to the Docker daemon at unix")) {
          commandText.setText(
              "The docker daemon is not running. Please start it and retry");
          break;
        }
        if (line.startsWith("triangle count")) {
          commandText.setText(line);
        }
        if (line.contains("Number of triangles")) {
          commandText.setText(line);
        }
        if (line.contains("Input file is not in right format")) {
          commandText.setText(
              "Input file is not in right format, you should provide me with an Edge List");
          break;
        }
        if (line.contains("number of different labels")) {
          commandText.setText(line);
        }
        if (line.startsWith("1.")) {
          StringBuilder res1 =
              new StringBuilder("Print top 10 vertecies :\n" + line + "\n");
          for (int i = 0; i < 10; i++) {
            if (((line = reader.readLine()) != null)) {
              res1.append(line).append("\n");
            }
          }
          commandText.setText(res1.toString());
        }
        res = res + "\n" + line;
      }
      System.out.println(res);
      System.out.println(commandText.getText());

      // TODO : this returns the success for the last executed command
      int exitVal = process.waitFor();
      if (exitVal == 0) {
        System.out.println("Success!");
        // System.out.println(output);
        // System.exit(0);
      } else {
        // abnormal...
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    try {

      deploySolutionButton.getStyleClass().addAll("btn", "btn-success");
      frameworkNamePreLabel.setText("Recommanded Framework:\t");
      String preLabelCssClass = "preLabel";
      frameworkNamePreLabel.getStyleClass().add(preLabelCssClass);
      frameworkNameLabel.setText(modelResponse.getFramework());

      expected_timePreLabel.setText("expected time:\t");
      expected_timePreLabel.getStyleClass().add(preLabelCssClass);
      expected_timeLabel.setText(modelResponse.getexpected_time());

      graphNamePreLabel.setText("Graph Path:\t");
      graphNamePreLabel.getStyleClass().add(preLabelCssClass);
      graphNameLabel.setText(modelResponse.getFormModel().getFileName());

      nodesPreLabel.setText("Number of Nodes:\t");
      nodesPreLabel.getStyleClass().add(preLabelCssClass);
      nodesLabel.setText(
          String.valueOf(modelResponse.getFormModel().getNodes()));

      edgesPreLabel.setText("Number of Edges:\t");
      edgesPreLabel.getStyleClass().add(preLabelCssClass);
      edgesLabel.setText(
          String.valueOf(modelResponse.getFormModel().getEdges()));

      algorithmPreLabel.setText("Algorithm to Apply:\t");
      algorithmPreLabel.getStyleClass().add(preLabelCssClass);
      algorithmLabel.setText(
          modelResponse.getFormModel().getAlgorithm().name());

      int iteration = modelResponse.getFormModel().getIterations();
      if (iteration != -1) {
        iterationLabel.setText(String.valueOf(iteration));
        iterationPreLabel.getStyleClass().add(preLabelCssClass);
        iterationPreLabel.setText("number of iteration:\t");
      } else {
        iterationLabel.setVisible(false);
        iterationPreLabel.setVisible(false);
      }
      commandText.setText("");
      // System.setOut(new PrintStream( new
      // CustomOutputStream(commandTextArea)));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //  public void setParameters(String params) {
  //     // Process the parameters here

  //      JsonElement jsonElement = JsonParser.parseString(params);
  //      JsonObject jsonObject = jsonElement.getAsJsonObject();
  //      String framework = jsonObject.get("framework").getAsString();
  //      System.out.println(framework);
  // }
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
