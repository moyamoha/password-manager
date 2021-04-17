package fxPassreg;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import kanta.Strings;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.CheckBoxChooser;
import fi.jyu.mit.fxgui.ModalController;

/**
 * @author Yahya
 * @version 10.2.2021
 * Kontrolleri salasanan generoimisikkunalle
 */
public class GeneroiDialogController implements ModalControllerInterface<String>, Initializable {
    
    
    @FXML private Spinner<Integer> spPituus;
    @FXML private CheckBoxChooser<String> checkBoxit;
    @FXML private Label showPassLabel;
    @FXML private Button kaytaButton;

    @Override
    public String getResult() {
        return palautettava;
    }

    @Override
    public void handleShown() {
        spPituus.requestFocus();
    }

    @Override
    public void setDefault(String oletus) {
        ModalController.getStage(checkBoxit).setOnCloseRequest(e -> cancel());
    }
    
    @FXML private void handleCancelButton() { cancel(); }
    
    @FXML private void handleGeneroi()      { generoi(); }
    
    @FXML private void handleKayta()        { kaytaSalasanaa(); }
    
    // ####################################
    
    private final int alkuArvo = 15;
    private final int suurinPituus = 20;
    private final int pieninPituus = 5;
    private String palautettava = "";
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        SpinnerValueFactory.IntegerSpinnerValueFactory spFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(pieninPituus, suurinPituus, alkuArvo);
        spPituus.setValueFactory(spFactory);
        BooleanBinding bb = new BooleanBinding() {
            { super.bind(showPassLabel.textProperty()); }
            @Override
            protected boolean computeValue() {
                return showPassLabel.getText().isEmpty();
            }
        }; kaytaButton.disableProperty().bind(bb);
    }
    
    
    /**
     * Generoidaan k‰ytt‰j‰lle sopiva salasana ja n‰ytet‰‰n ruudulla
     */
    private void generoi() {
        int pituus = spPituus.getValue();
        String s = Strings.generoiSalasana(pituus, getBoxinArvot());
        showPassLabel.setText(s);
    }
    
    
    /**
     * Tutkitaan mitk‰ kaikki generoimiseen liittyv‰t vaihtoehdot on valittu.
     * @return boolean taulukko, jossa kummankin vaihtoehdon kohdalla vastaava boolean arvo.
     */
    private boolean[] getBoxinArvot() {
        List<Integer> valitut = checkBoxit.getSelectedIndices();
        boolean[] boxArvot = new boolean[3];
        for (int j = 0; j < valitut.size(); j++) {
            boxArvot[valitut.get(j)] = true;
        }
        return boxArvot;
    }
    
    /**
     * Poistutaan n‰kym‰st‰ palauttamatta mit‰‰n
     */
    private void cancel() {
        ModalController.closeStage(spPituus);
    }
    
    /**
     * K‰ytet‰‰n generoitua salasanaa
     */
    private void kaytaSalasanaa() {
        palautettava = showPassLabel.getText();
        ModalController.closeStage(spPituus);
    }
    
    /**
     * N‰ytet‰‰n generointi-ikkuna.
     * @return generointi salasana
     */
    public static String haeGeneroituSalasana() {
        return ModalController.showModal(
                GeneroiDialogController.class.getResource("GeneroiDialogView.fxml"),
                "Salasanan generointi",
                null, "");
    }


}
