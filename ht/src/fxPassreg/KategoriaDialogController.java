/**
 * 
 */
package fxPassreg;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
/**
 * @author Yahya
 * @version 22.2.2021
 *
 */
public class KategoriaDialogController
        implements Initializable, ModalControllerInterface<String> {
    
    @FXML private TextField nimiTextField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * N‰ytet‰‰n kategorian muokkausikkuna modaalisena
     */
    @FXML
    public static void naytaKategoriaDialog() {
         ModalController.showModal(
                KategoriaDialogController.class.getResource("KategoriaDialogView.fxml"),
                "muokkaus",
                null, "");
    }
    

    @FXML private void handleCancelButton() {
        handleCancel();
    }

    @FXML private void handleOkButton() {
        handleOk();
    }


    // #################################################
    //
    //
    //##################################################
    
    /**
     * Poistutaan ikkunasta
     */
    private void handleCancel(){
        ModalController.closeStage(nimiTextField);
    }
    
    /**
     * Tallennetaan muutokset
     */
    private void handleOk() {
        // TODO Auto-generated method stub
        Dialogs.showMessageDialog("Tallennetaan, mutta viel‰ ei toimi!");
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
