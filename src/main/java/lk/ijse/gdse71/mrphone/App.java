package lk.ijse.gdse71.mrphone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboard-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Mr.Phone");
        Image image = new Image(getClass().getResourceAsStream("/assets/image/360_F_779074248_wOi17drmXgV4pY7iy5phmgwbMynbadjw (1).jpg"));
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
