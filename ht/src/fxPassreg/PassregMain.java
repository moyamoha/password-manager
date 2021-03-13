package fxPassreg;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import passreg.Passreg;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Yahya
 * @version 19.1.2021
 * Salasanarekisteriohjelman pääohjelma
 */
public class PassregMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("PassregGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
			final PassregGUIController passCtrl = (PassregGUIController)ldr.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("passreg.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Passreg");
			
			Passreg passrekisteri = new Passreg();
			passCtrl.setPassreg(passrekisteri);
			
			if (!passCtrl.avaaTiedosto()) Platform.exit();
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> passCtrl.tallenna());
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
