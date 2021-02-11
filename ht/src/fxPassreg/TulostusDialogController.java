package fxPassreg;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * @author Yahya
 * @version 10.2.2021
 * Kontrolleri tulostuksen toiminnan hallitsemiseksi.
 */
public class TulostusDialogController implements ModalControllerInterface<String> {
    
    @FXML private TextArea tulosteText;
    
    /**
     * N‰ytet‰‰n tulostusikkuna ei-modaalisena.
     */
    @FXML public static void naytaTulostusIkkuna() {
        ModalController.showModeless(TulostusDialogController.class.getResource("PassregTulostusView.fxml"), "Tulostus", null);
    }
    
    /**
     * K‰sitell‰‰n ok-buttonin toimintaa
     */
    @FXML private void handleDefaultOk() {
        handleOk();
    }
    
    // #####################################################################
    
    /**
     * kun k‰ytt‰j‰ painaa ok-painiketta, suljetaan tulostusikkuna.
     */
    private void handleOk() {
        // TODO Auto-generated method stub
        ModalController.closeStage(tulosteText);
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
