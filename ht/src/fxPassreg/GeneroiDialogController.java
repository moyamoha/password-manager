package fxPassreg;

import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import kanta.Merkkijonot;

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
    
    
    @FXML private Button okButton;
    @FXML private Spinner<Integer> spPituus;
    @FXML private CheckBoxChooser<String> checkBoxit;

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return this.generoi();
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
    
    // ####################################
    
    private final int alkuArvo = 10;
    private final int suurinPituus = 20;
    private final int pieninPituus = 5;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //TODO: lis‰‰ alustuksia t‰h‰n
        SpinnerValueFactory.IntegerSpinnerValueFactory spFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(pieninPituus, suurinPituus, alkuArvo);
        spPituus.setValueFactory(spFactory);
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
    
    
    /**
     * Generoidaan k‰ytt‰j‰lle sopiva salasana
     * @return generoitu salasana
     */
    public String generoi() {
        int pituus = spPituus.getValue();
        String s = Merkkijonot.generoiSalasana(pituus, getBoxinArvot());
        return s;
    }
    
    private boolean[] getBoxinArvot() {
        List<Integer> valitut = checkBoxit.getSelectedIndices();
        boolean[] boxArvot = new boolean[3];
        for (int j = 0; j < valitut.size(); j++) {
            boxArvot[valitut.get(j)] = true;
        }
        return boxArvot;
        
    }
    
    private void cancel() {
        ModalController.closeStage(okButton);
    }


}
