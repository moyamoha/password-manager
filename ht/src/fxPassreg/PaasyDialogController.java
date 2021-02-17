package fxPassreg;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * @author Yahya
 * @version 5.2.2021
 * Kontrolleri p‰‰sydialogin toimintaa varten
 */
public class PaasyDialogController implements ModalControllerInterface<String>, Initializable {
    
    @FXML private Button generoiButton; 
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO: T‰h‰n lis‰‰ alustuksia
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Avaa uuden ikkunan, jossa generoidaan antamasien kenttien arvojen mukainen salasana");
        generoiButton.setTooltip(tooltip);
    }
    
    /**
     * N‰ytet‰‰n p‰‰syn muokkausikkuna modaalisena
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
     * K‰sitell‰‰n generoi-painikkeen toiminta
     */
    @FXML private void handleGeneroiButton() {
        avaaGenerointiIkkuna();
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
        ModalController.closeStage(generoiButton);
    }
    
    /**
     * Kun k‰ytt‰j‰ painaa ok-buttonin, tallennetaan muutokset ja poistutaan. 
     * Mik‰li tallennettavia muutoksia ei ole, poistutaan ikkunasta.
     */
    private void handleOk() {
        // TODO tallentaa muutokset 
        Dialogs.showMessageDialog("Tallennetaan, mutta viel‰ ei toimi!");
    }
    
    /**
     * N‰ytet‰‰n generoidialogi uudessa ikkunassa
     */
    private void avaaGenerointiIkkuna() {
        GeneroiDialogController.naytaGeneroiIkkuna();
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
