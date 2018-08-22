package Email;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Create Main application window that take GUI from Email.fxml file, it has title, icon and size
        Parent root = FXMLLoader.load(getClass().getResource("Email.fxml"));
        primaryStage.setTitle("MyEmail");
        primaryStage.setScene(new Scene(root, 600, 650));
        Image applicationIcon = new Image(getClass().getResourceAsStream("letter.png"));
        primaryStage.getIcons().add(applicationIcon);
        
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
