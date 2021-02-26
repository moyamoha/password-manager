package fxPassreg;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * @author Yahya
 * @version 5.2.2021
 * Kontrolleri p‰‰sydialogin toimintaa varten
 */
public class PaasyDialogController implements ModalControllerInterface<String>, Initializable {
    
    @FXML private Button generoiButton; 
    @FXML private CheckBox naytaCheckBox;
    @FXML private TextField passText1;
    @FXML private PasswordField passField1;
    @FXML private TextField passText2;
    @FXML private PasswordField passField2;
    @FXML private Label virheText;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO: T‰h‰n lis‰‰ alustuksia
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Avaa uuden ikkunan, jossa generoidaan antamasi kenttien arvojen mukainen salasana");
        generoiButton.setTooltip(tooltip);
        hallitseGeneroimista();
        hallitseSalasanaKentat();
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
     * K‰sitell‰‰n cancel-painikkeen toiminta. 
     */
    @FXML private void handleCancelButton() {
        handleCancel();
    }
    
    /**
     * K‰sitell‰‰n n‰yt‰-valintaruudun toimintaa
     */
    @FXML private void handleNaytaCheckBox() {
        if (this.naytaCheckBox.isSelected()) naytaSalasana();
        else piilotaSalasana();
    }
    
    /**
     * K‰sitell‰‰n generoi-painikkeen toimintaa
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
    

    // ########################################################
    //                                                        //
    //                    methods                             //
    //                                                        //
    // ########################################################
    
    /**
     * Cancel-buttonin painettua poistutaan ikkunasta, mik‰li tallennattomia tietoja ei ole.
     * Jos tallentamatonta tietoa lˆytyy, kysyt‰‰n k‰ytt‰j‰lt‰ haluaako h‰n varmasti poistua
     */
    private void handleCancel() {
        // TODO lis‰‰ kysymysdialogi, jossa varmistetaan, ett‰ k‰ytt‰j‰ varmasti haluaa poistua.
        ModalController.closeStage(generoiButton);
    }
    
    
    private void hallitseSalasanaKentat() {
        // TODO Auto-generated method stub
        passText1.textProperty().bindBidirectional(passField1.textProperty());
        passText2.textProperty().bindBidirectional(passField2.textProperty());
    }
    
    /**
     * Esitet‰‰n salasana n‰kyv‰ksi merkkijonoksi
     */
    private void naytaSalasana() {
        passField1.setVisible(false);  passText1.setVisible(true);
        passField2.setVisible(false);  passText2.setVisible(true);
    }
    
    /**
     * Piilotetaan salasana n‰kym‰ttˆm‰ksi. N‰ytet‰‰n sen tilalle mustia palloja/luoteja
     */
    private void piilotaSalasana() {
        passText1.setVisible(false); passField1.setVisible(true);
        passText2.setVisible(false); passField2.setVisible(true);
    }
    
    /**
     * Kontrolloidaan generoi-buttonin saavutettavuus. Jos salasanakentt‰‰n on kirjoitettu jotakin, 
     * estet‰‰n generoimista. Sallitaan generoimista ainoastaan silloin kun salasanakentt‰ on tyhj‰
     */
    private void hallitseGeneroimista() {
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(passText1.textProperty(),
                        passField1.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (! passField1.getText().isEmpty()
                        ||  ! passText1.getText().isEmpty());
            }
        };
        generoiButton.disableProperty().bind(bb);
    }
    
    /**
     * Kun k‰ytt‰j‰ painaa ok-buttonin, tallennetaan muutokset ja poistutaan. 
     * Mik‰li tallennettavia muutoksia ei ole, poistutaan ikkunasta.
     */
    private void handleOk() {
        // TODO tallentaa muutokset 
        if (salasanatTasmaa()) {
            virheText.setText("");
            Dialogs.showMessageDialog("Tallennetaan, mutta viel‰ ei toimi!");
            return;
        }
        virheText.setText("Salasanat eiv‰t t‰sm‰‰");
        Dialogs.showMessageDialog("Tallennetaan, mutta viel‰ ei toimi!");
    }
    
    private boolean salasanatTasmaa() {
        // TODO Auto-generated method stub
        if (! naytaCheckBox.isSelected()) return passField1.getText().equals(passField2.getText()); 
        return passText1.getText().equals(passText2.getText());
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
