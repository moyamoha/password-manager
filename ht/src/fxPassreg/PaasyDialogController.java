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
import passreg.Entry;

/**
 * @author Yahya
 * @version 5.2.2021
 * Kontrolleri pääsydialogin toimintaa varten
 */
public class PaasyDialogController implements ModalControllerInterface<Entry>, Initializable {
    
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
    
    private Entry current;
    private ArrayList<TextInputControl> edits;
    private static Entry apuPaasy = new Entry();
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Tooltip tooltip = new Tooltip("Avaa uuden ikkunan, jossa generoidaan antamasi kenttien arvojen mukainen salasana");
        generoiButton.setTooltip(tooltip);
        bindSalasanaKentat();
        Node parent = generoiButton.getParent();
        Collection<Label> labelit =  getNodes(parent, Label.class, 
                n -> n.getStyleClass().contains("l"), true);
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
                                solmu.setStyle("-fx-control-inner-background: white");
                                return;
                            }
                            solmu.setStyle("-fx-control-inner-background: red");
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
     * @param edits tekstikentät joihin näytetään tietueen tiedot
     * @param entry näytettävä pääsy
     */
    public static void naytaPaasy(ArrayList<TextInputControl> edits, Entry entry) {
        if (entry == null) return;
        for (int i = apuPaasy.ekaKentta(); i <= apuPaasy.kenttaLkm(); i++) {
            String s = entry.anna(i);
            for (TextInputControl edit : edits) {
                if (edit.getId().equals(String.valueOf(i))) {
                    edit.setText(s); 
                }
            }
        }
    }

    /**
     * Näytetään pääsyn muokkausikkuna modaalisena
     * @param modality mille ollaan modaalisia
     * @param oletus oletus pääsy
     * @return tämän kontrollerin muokkaama pääsy
     */
    public static Entry kysyPaasy(Stage modality, Entry oletus) {
         URL url = PaasyDialogController.class.getResource("PaasyDialogView.fxml");
         Entry ctrl =  ModalController.showModal(
                  url, "muokkaus", modality, oletus);
         return ctrl;
    }
    
    /**
     * Cancel-buttonin painettua poistutaan ikkunasta, mikäli tallennattomia tietoja ei ole.
     * Jos tallentamatonta tietoa löytyy, kysytään käyttäjältä haluaako hän varmasti poistua
     */
    private void handleCancel() {
        current = null;
        ModalController.closeStage(generoiButton);
    }
    
    
    /**
     * Kytketään salasanatkenttä ja sen vastaava tekstikenttä toisiinsa.
     * Muutos toiseen aiheuttaa muutosta toiseen. Kytketään samalla salasanan näkyvyys näytä-checkboxiin.
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
     * Kun käyttäjä painaa ok-painikkeen, tallennetaan muutokset ja poistutaan. 
     * Mikäli tallennettavia muutoksia ei ole, poistutaan ikkunasta.
     */
    private void handleOk() {
        for (TextInputControl edit : edits) {
            try {
                int id = Integer.valueOf(edit.getId());
                if (! current.onValidi(id, edit.getText())) {
                    PassregGUIController.naytaIlmoitus(1.5, AlertType.ERROR, "Täytä pakolliset kentät oikeilla arvoilla!");
                    return;
                }
            } catch (NumberFormatException e2) {/*..*/}
        }
        String[] pakolliset = {
                edits.get(1).getText(), edits.get(2).getText(), edits.get(3).getText()  
        };
        List<String> pakollisetList = Arrays.asList(pakolliset);
        if (pakollisetList.stream().allMatch(e -> e.equals(""))) { // Varmistetaan, että vähintään yksi käyttäjätunnusta esittävä kenttä täytetään
            virheText.setText("Jokin näistä pitää olla täytettynä: tunnus, sähköposti tai puhelinnumero");
            return;
        }
        if ( ! salasanatTasmaa()) {  virheText.setText("Salasanat ei täsmää"); return; }
        ModalController.closeStage(generoiButton);
    }
    
    /**
     * Tutkitaan täsmääkö salasanakenttien arvot.
     * @return
     */
    private boolean salasanatTasmaa() {
        return passText1.getText().equals(passText2.getText());
    }

    /**
     * Näytetään generoidialogi uudessa ikkunassa, ja haetaan generoitu salasana käytettäväksi
     */
    private void avaaGenerointiIkkuna() {
        String salasana = GeneroiDialogController.haeGeneroituSalasana();
        if (salasana == null || salasana.equals("")) return;
        passText1.setText(salasana); passText2.setText(salasana);
    }

    @Override
    public Entry getResult() {
        return current;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(Entry oletus) {
        ModalController.getStage(generoiButton).setOnCloseRequest(e -> handleCancel());
        current = oletus;
        naytaPaasy(edits, oletus);
        passText2.setText(passField1.getText());
    }
    
}
