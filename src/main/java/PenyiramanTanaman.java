
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PenyiramanTanaman extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Pastikan path sesuai nama dan lokasi file FXML kamu
        URL url = new File("src/main/java/view/Homebase.fxml").toURI().toURL();

        Scene scene = new Scene(FXMLLoader.load(url));

        stage.setTitle("Aplikasi Penyiraman Tanaman");
        stage.setScene(scene);
        stage.show();
    }
}