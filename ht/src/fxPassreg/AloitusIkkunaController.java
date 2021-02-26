/**
 * 
 */
package fxPassreg;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Yahya
 * @version 10.2.2021
 * Kontrolleri aloitusikkunaa varten. Aloitusikkunassa kysyt‰‰n k‰ytt‰j‰lt‰ jokin 
 * sopiva tiedoston nimi. Kun k‰ytt‰j‰ antaa tekstina tiedoston nimen, avataan se.
 */
public class AloitusIkkunaController implements ModalControllerInterface<String> {
    
    @FXML private TextField vastausKenttaText;
    private String vastaus = null;
    
    
    /**
     * K‰sitell‰‰n ok-buttonin toiminta
     */
    @FXML private void handleOk() {
        vastaus = this.vastausKenttaText.getText();
        ModalController.closeStage(vastausKenttaText);
    }
    
    
    /**
     * K‰sitell‰‰n cancel-buttonin toiminta
     */
    @FXML private void handleCancel() {
        ModalController.closeStage(vastausKenttaText);
    }
    
    
    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return vastaus;
    }


    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        this.vastausKenttaText.requestFocus();
    }


    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
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

}
