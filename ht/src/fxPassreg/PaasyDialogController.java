package fxPassreg;

import static fi.jyu.mit.fxgui.Functions.getNodes;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import passreg.Paasy;

/**
 * @author Yahya
 * @version 5.2.2021
 * Kontrolleri p��sydialogin toimintaa varten
 */
public class PaasyDialogController implements ModalControllerInterface<Paasy>, Initializable {
    
    @FXML private Button generoiButton; 
    @FXML private CheckBox naytaCheckBox;
    @FXML private TextField passText1;
    @FXML private PasswordField passField1;
    @FXML private TextField passText2;
    @FXML private PasswordField passField2;
    @FXML private Label virheText;
 
    
    @FXML private void handleCancelButton() {
        handleCancel();
    }
    
    @FXML private void handleGeneroiButton() {
        avaaGenerointiIkkuna();
    }
    
    @FXML private void handleOkButton() {
        handleOk();
    }
    

    // ########################################################
    //                    methods                             //
    // ########################################################
    
    private Paasy current;
    private ArrayList<TextInputControl> edits;
    private static Paasy apuPaasy = new Paasy();
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Tooltip tooltip = new Tooltip("Avaa uuden ikkunan, jossa generoidaan antamasi kenttien arvojen mukainen salasana");
        generoiButton.setTooltip(tooltip);
        hallitseGeneroimista();
        bindSalasanaKentat();
        Node parent = generoiButton.getParent();
        Collection<Label> labelit =  getNodes(parent, Label.class, 
                n -> n.getStyleClass().contains("label"), true);
        edits =  (ArrayList<TextInputControl>) getNodes(parent, TextInputControl.class, 
                        n -> n.getStyleClass().contains("kentta"), true);
        for (Label label : labelit) {
            if (label.getId() != null) {
                try {
                    String id = label.getId();
                    label.setLayoutX(0); label.setAlignment(Pos.CENTER_RIGHT);
                    label.setPrefWidth(120);
                    label.setText(apuPaasy.getKysymys(Integer.valueOf(id))); // koska id:t tulisi alkaa nollasta
                } catch (NumberFormatException e) {/*..*/}
            }
        }
        for (TextInputControl solmu : edits) {
            if (solmu.getId() != null) {
                String id = solmu.getId();
                solmu.textProperty().addListener(new ChangeListener<String>() {
                    @Override 
                    public void changed(ObservableValue<? extends String> ov, String vanha, String uusi) {                
                        if (current == null) return;
                        try {
                            String virhe = current.aseta(Integer.valueOf(id), uusi);
                            if (virhe == null) {
                                naytaVirhe(virhe);
                                solmu.setStyle("-fx-background-color: #ffffff");
                                return;
                            }
                            solmu.getStyleClass().add("virhe");
                            solmu.setStyle("-fx-background-color: #ff0000");
                            naytaVirhe(virhe);
                            return;  
                        }
                        catch (NumberFormatException e) { return; }
                }});
            }
        }
    }

    /**
     * Naytetaan virhe punaisena tekstina
     * @param virhe
     */
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            virheText.setText("");
            return;
        }
        virheText.setText(virhe);
    }

    /**
     * @param edits tekstikent�t joihin n�ytet��n tietueen tiedot
     * @param paasy n�ytett�v� p��sy
     */
    public static void naytaPaasy(ArrayList<TextInputControl> edits, Paasy paasy) {
        if (paasy == null) return;
        for (int i = apuPaasy.ekaKentta(); i <= apuPaasy.kenttaLkm(); i++) {
            String s = paasy.anna(i);
            for (TextInputControl edit : edits) {
                if (edit.getId().equals(String.valueOf(i))) {
                    edit.setText(s); 
                }
            }
        }
    }

    /**
     * N�ytet��n p��syn muokkausikkuna modaalisena
     * @param modality mille ollaan modaalisia
     * @param oletus oletus p��sy
     * @return t�m�n kontrollerin muokkaama p��sy
     */
    public static Paasy kysyPaasy(Stage modality, Paasy oletus) {
         URL url = PaasyDialogController.class.getResource("PaasyDialogView.fxml");
         Paasy ctrl =  ModalController.showModal(
                  url, "muokkaus", modality, oletus);
         return ctrl;
    }
    
    
    /**
     * Cancel-buttonin painettua poistutaan ikkunasta, mik�li tallennattomia tietoja ei ole.
     * Jos tallentamatonta tietoa l�ytyy, kysyt��n k�ytt�j�lt� haluaako h�n varmasti poistua
     */
    private void handleCancel() {
        current = null;
        ModalController.closeStage(generoiButton);
    }
    
    
    /**
     * Kytket��n salasanatkentt� ja sen vastaava tekstikentt� toisiinsa.
     * Muutos toiseen aiheuttaa muutosta toiseen. Kytket��n samalla salasanan n�kyvyys n�yt�-checkboxiin.
     */
    private void bindSalasanaKentat() {
        passText1.textProperty().bindBidirectional(passField1.textProperty());
        passText2.textProperty().bindBidirectional(passField2.textProperty());
        BooleanBinding bb = new BooleanBinding() {
            { super.bind(naytaCheckBox.selectedProperty()); }
            @Override
            protected boolean computeValue() {
                return ( ! naytaCheckBox.isSelected());
            }
        };
        passField1.visibleProperty().bind(bb);
        passField2.visibleProperty().bind(bb);
    }
    
    /**
     * Kontrolloidaan generoi-buttonin saavutettavuus. Jos salasanakentt��n on kirjoitettu jotakin, 
     * estet��n generoimista. Sallitaan generoimista ainoastaan silloin, kun salasanakentt� on tyhj�
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
     * Kun k�ytt�j� painaa ok-painikkeen, tallennetaan muutokset ja poistutaan. 
     * Mik�li tallennettavia muutoksia ei ole, poistutaan ikkunasta.
     */
    private void handleOk() {
        for (TextInputControl edit : edits) {
            try {
                int id = Integer.valueOf(edit.getId());
                if (! current.onValidi(id, edit.getText())) {
                    PassregGUIController.naytaIlmoitus(1.5, AlertType.ERROR, "T�yt� pakolliset kent�t oikeilla arvoilla!");
                    return;
                }
            } catch (NumberFormatException e2) {
                //
            }
        }
        String[] pakolliset = {
                edits.get(1).getText(), edits.get(2).getText(), edits.get(3).getText()  
        };
        List<String> pakollisetList = Arrays.asList(pakolliset);
        if (pakollisetList.stream().allMatch(e -> e.equals(""))) { // Varmistetaan, ett� v�hint��n yksi k�ytt�j�tunnusta esitt�v� kentt� t�ytet��n
            virheText.setText("Jokin n�ist� pit�� olla t�ytettyn�: tunnus, s�hk�posti tai puhelinnumero");
            return;
        }
        if ( ! salasanatTasmaa()) {  virheText.setText("Salasanat ei t�sm��"); return; }
        ModalController.closeStage(generoiButton);
    }
    
    /**
     * Tutkitaan t�sm��k� salasanakenttien arvot.
     * @return
     */
    private boolean salasanatTasmaa() {
        return passText1.getText().equals(passText2.getText());
    }

    /**
     * N�ytet��n generoidialogi uudessa ikkunassa, ja haetaan generoitu salasana k�ytett�v�ksi
     */
    private void avaaGenerointiIkkuna() {
        String salasana = GeneroiDialogController.haeGeneroituSalasana();
        passText1.setText(salasana); passText2.setText(salasana);
    }


    @Override
    public Paasy getResult() {
        return current;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(Paasy oletus) {
        ModalController.getStage(generoiButton).setOnCloseRequest(e -> handleCancel());
        current = oletus;
        naytaPaasy(edits, oletus);
    }
    
    
}
