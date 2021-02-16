/**
 * 
 */
package fxPassreg;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Yahya
 * @version 10.2.2021
 * Kontrolleri tiedot-ikkunalle. Ikkunassa n‰ytet‰‰n ohjelman tietoja kuten versio ja tekij‰.
 */
public class TiedotDialogController implements ModalControllerInterface<String> {
        
    @FXML private Button okButton; 
    
    
    /**
     * N‰ytet‰‰n ohjelman tietoja sis‰lt‰v‰ ikkuna.
     */
    @FXML public static void naytaAboutDialog() {
        ModalController.showModal(TiedotDialogController.class.getResource("PassregTiedotView.fxml"), "About us" , null, "");    
    }
      
    /**
     * K‰sitell‰‰n ok-painikkeen toiminta.
     */
    @FXML private void handleDefaultOk() {
        handleOk();
    }    
    
    /**
     * K‰sitell‰‰n lis‰‰-painikkeen toiminta.
     */
    @FXML private void handleLisaaButton() {
        handleLisaa();
    }
   
    /**
     * Ok-painiketta painaessa, poistutaan tiedot-ikkunasta ja palataan p‰‰ikkunaan. 
     */
    private void handleOk() {
        ModalController.closeStage(okButton);
    }
       
    /**
     * Lis‰‰-painiketta painaessa, vied‰‰n k‰ytt‰j‰ ohjelman dokumentaatiosivulle.
     * sivu on: "https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"
     */
    private void handleLisaa() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"));
        }
        catch (URISyntaxException e) { return; }
        catch (IOException e) { return; }
        
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }   
    

}
