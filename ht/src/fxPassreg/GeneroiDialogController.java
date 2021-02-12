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
    
    
    @FXML private Button generoiButton;

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
     * Käsitellään kopioileikepoydalle-painikkeen toiminta
     */
    @FXML private void handleKopio() {
        kopioiLeikePoydalle();
        ModalController.closeStage(generoiButton);
    }
    
    
    /** 
     * Käsitellään generoi-painikkeen toiminta
     */
    @FXML private void handleGeneroi() {
        generoi();
    }
    
    /**
     * Näytetään generointi-ikkuna.
     */
    public static void naytaGeneroiIkkuna() {
        ModalController.showModal(
                GeneroiDialogController.class.getResource("GeneroiDialogView.fxml"),
                "Salasanan generointi",
                null, "");
    }

    // ########################################################################
    
    
    /**
     * Otetaan kopio generoidusta salasanasta leikepöydälle
     */
    private void kopioiLeikePoydalle() {
        // TODO tekstin kopiointi leikepöydälle
        Dialogs.showMessageDialog("Kopioidaan, mutta ei osata vielä");
    }
    
    
    /**
     * Generoidaan käyttäjälle sopiva salasana
     */
    private void generoi() {
        // TODO Salasanan generointi
        Dialogs.showMessageDialog("Generoidaan, mutta ei osata vielä");
    }

}
