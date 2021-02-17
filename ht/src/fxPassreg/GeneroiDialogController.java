package fxPassreg;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;

/**
 * @author Yahya
 * @version 10.2.2021
 * Kontrolleri salasanan generoimisikkunalle
 */
public class GeneroiDialogController implements ModalControllerInterface<String> {
    
    
    @FXML private Button okButton;

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
    
    /** 
     * K‰sitell‰‰n generoi-painikkeen toiminta
     */
    @FXML private void handleCancelButton() {
        cancel();
        
    }
    
    @FXML private void handleGeneroi() {
        generoi();
    }
    
    /**
     * N‰ytet‰‰n generointi-ikkuna.
     */
    public static void naytaGeneroiIkkuna() {
        ModalController.showModal(
                GeneroiDialogController.class.getResource("GeneroiDialogView.fxml"),
                "Salasanan generointi",
                null, "");
    }

    // ########################################################################
    
    
    /**
     * Generoidaan k‰ytt‰j‰lle sopiva salasana
     */
    private void generoi() {
        // TODO Salasanan generointi
        Dialogs.showMessageDialog("Generoidaan, mutta ei osata viel‰");
    }
    
    private void cancel() {
        ModalController.closeStage(okButton);
    }

}
