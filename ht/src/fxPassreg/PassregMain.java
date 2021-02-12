package fxPassreg;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Yahya
 * @version 19.1.2021
 * Salasanarekisteriohjelman p��ohjelma
 */
public class PassregMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("PassregGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
			final PassregViewController passCtrl = (PassregViewController)ldr.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("passreg.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Passreg");
			
			if (!passCtrl.avaaTiedosto()) Platform.exit();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args ei k�yt�ss�
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
