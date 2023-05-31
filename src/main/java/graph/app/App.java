package graph.app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * JavaFX App
 */
public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("primary"), 640, 480);
    scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    stage.setScene(scene);
    stage.show();
  }

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader =
        new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  public static void main(String[] args) { launch(); }
}
// package graph.app;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import java.io.IOException;

// /**
//  * JavaFX App
//  */
// public class App extends Application {

//     private static Scene scene;

//     @Override
//     public void start(Stage stage) throws IOException {
//         scene = new Scene(loadFXML("primary"), 640, 480);
//         stage.setScene(scene);
//         stage.show();
//     }

//     static void setRoot(String fxml, String params) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +
//         ".fxml")); Parent root = fxmlLoader.load();

//         if (params!="") {
//             Object controller = fxmlLoader.getController();
//             if (controller instanceof SecondaryController) {
//                 SecondaryController secondaryController =
//                 (SecondaryController) controller;
//                 secondaryController.setParameters(params);
//             }
//         }

//         scene.setRoot(root);
//     }

//     private static Parent loadFXML(String fxml) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +
//         ".fxml")); return fxmlLoader.load();
//     }

//     public static void main(String[] args) {
//         launch();
//     }

// }