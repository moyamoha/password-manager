package fxPassreg;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import kanta.Merkkijonot;
import static kanta.Merkkijonot.generateHexColor;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.CheckBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
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
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
    }
    
    @FXML private void handleCancelButton() { cancel(); }
    
    @FXML private void handleGeneroi()      { generoi(); }
    
    @FXML private void handleKayta()        { kaytaSalasanaa(); }
    
    // ####################################
    
    private final int alkuArvo = 15;
    private final int suurinPituus = 20;
    private final int pieninPituus = 5;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //TODO: lis�� alustuksia t�h�n
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
     * N�ytet��n generointi-ikkuna.
     */
    public static void naytaGeneroiIkkuna() {
        ModalController.showModal(
                GeneroiDialogController.class.getResource("GeneroiDialogView.fxml"),
                "Salasanan generointi",
                null, "");
    }
    
    
    /**
     * Generoidaan k�ytt�j�lle sopiva salasana ja n�ytet��n ruudulla
     */
    public void generoi() {
        int pituus = spPituus.getValue();
        String s = Merkkijonot.generoiSalasana(pituus, getBoxinArvot());
        String color = generateHexColor();
        showPassLabel.setTextFill(Color.web(color));
        showPassLabel.setText(s);
    }
    
    
    /**
     * Tutkitaan mitk� kaikki generoimiseen liittyv�t vaihtoehdot on valittu.
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
    
    private void cancel() {
        ModalController.closeStage(spPituus);
    }
    
    /**
     * Palauttaa generoidun salasanan
     * @return salasana
     */
    public String kaytaSalasanaa() {
        Dialogs.showMessageDialog("Ei osata viel� toimia halutulla tavalla");
        return showPassLabel.getText();
    }


}
