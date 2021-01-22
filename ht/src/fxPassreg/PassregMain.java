package fxPassreg;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * @author Yahya
 * @version 19.1.2021
 *
 */
public class PassregMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PassregGUIView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("passreg.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Passreg");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
