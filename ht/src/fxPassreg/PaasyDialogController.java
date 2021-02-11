package fxPassreg;


import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Yahya
 * @version 5.2.2021
 * Kontrolleri p‰‰sydialogin toimintaa varten
 */
public class PaasyDialogController implements ModalControllerInterface<String> {
    
    @FXML private Button cancelButton; 
    
    /**
     * N‰ytet‰‰n p‰‰syn muokkausikkuna
     */
    @FXML
    public static void naytaPaasyDialog() {
         ModalController.showModal(
                PaasyDialogController.class.getResource("PaasyDialogView.fxml"),
                "muokkaus",
                null, "");
    }
    
    /**
     * K‰sitell‰‰n cancel-buttonin toiminta. 
     */
    @FXML private void handleCancelButton() {
        handleCancel();
    }
    
    /**
     * K‰sitell‰‰n ok-buttonin toiminta.
     */
    @FXML private void handleOkButton() {
        handleOk();
    }
    

    // ###########################################################
    
    /**
     * Cancel-buttonin painettua poistutaan ikkunasta, mik‰li tallennattomia tietoja ei ole.
     * Jos tallentamatonta tietoa lˆytyy, kysyt‰‰n k‰ytt‰j‰lt‰ haluaako h‰n varmasti poistua
     */
    private void handleCancel() {
        // TODO lis‰‰ kysymysdialogi, jossa varmistetaan, ett‰ k‰ytt‰j‰ varmasti haluaa poistua.
        ModalController.closeStage(cancelButton);
    }
    
    /**
     * Kun k‰ytt‰j‰ painaa ok-buttonin, tallennetaan muutokset ja poistutaan. 
     * Mik‰li tallennettavia muutoksia ei ole, poistutaan ikkunasta.
     */
    private void handleOk() {
        // TODO tallentaa muutokset 
        ModalController.closeStage(cancelButton);
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
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }
    
    
}
