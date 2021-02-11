/**
 * 
 */
package fxPassreg;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Yahya
 * @version 10.2.2021
 *
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
    
    @FXML private void handleLisaaButton() {
        handleLisaa();
    }

    private void handleOk() {
        // TODO Auto-generated method stub
        ModalController.closeStage(okButton);
    }
    
    private void handleLisaa() {
        // TODO: n‰ytet‰‰n lis‰‰ tietoja
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
