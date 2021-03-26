/**
 * 
 */
package fxPassreg;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Yahya
 * @version 10.2.2021
 * Kontrolleri aloitusikkunaa varten. Aloitusikkunassa kysyt‰‰n k‰ytt‰j‰lt‰ jokin 
 * sopiva tiedoston nimi. Kun k‰ytt‰j‰ antaa tekstina tiedoston nimen, avataan se.
 */
public class AloitusIkkunaController implements ModalControllerInterface<String>, Initializable{
    
    @FXML private TextField vastausKenttaText;
    private String vastaus = "";
    
    
    /** K‰sitell‰‰n ok-buttonin toiminta */
    @FXML private void handleOk() {
        vastaus = vastausKenttaText.getText();
        ModalController.closeStage(vastausKenttaText);
    }
    
    
    /**
     * K‰sitell‰‰n cancel-buttonin toiminta
     */
    @FXML private void handleCancel() {
        vastaus = null;
        ModalController.closeStage(vastausKenttaText);
    }
    
    
    @Override
    public String getResult() { return vastaus; }

    @Override
    public void handleShown() {
        this.vastausKenttaText.requestFocus();
    }


    @Override
    public void setDefault(String oletus) { 
        ModalController.getStage(vastausKenttaText).setOnCloseRequest(e -> Platform.exit());
        vastausKenttaText.setText(oletus);
    }    
    
    /**
     * N‰ytet‰‰n aloitusikkunan dialogin ja kysell‰‰n k‰ytt‰j‰lt‰ jokin aukeava tiedoston nimi
     * @param modalityStage mille ollaan modaalisia
     * @param oletus mik‰ on oletus tiedosta jota avataan
     * @return null jos painetaan cancel muuten tekstikentt‰‰n kirjoitetun tiedoston nimen
     */
    public static String kysyTiedosto(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                 AloitusIkkunaController.class.getResource("PassregAloitusIkkunaView.fxml"),
                 "Passreg", modalityStage, oletus);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //
    }

}
