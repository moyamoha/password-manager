/**
 * 
 */
package fxPassreg;

import static fi.jyu.mit.fxgui.Functions.getNodes;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import javafx.util.Duration;
import passreg.Kategoria;
/**
 * @author Yahya
 * @version 22.2.2021
 *
 */
public class KategoriaDialogController
        implements Initializable, ModalControllerInterface<Kategoria> {
    
    @FXML private Label virheText;

    
    @FXML private void handleCancelButton() {
        handleCancel();
    }

    @FXML private void handleOkButton() {
        tallenna();
    }

    // #################################################
    //
    //
    //##################################################
    
    private static Kategoria apuK = new Kategoria();
    private Kategoria current;
    private TextInputControl edits[];
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Node parent = virheText.getParent();
        edits = new TextInputControl[apuK.kenttaLkm()];
        Collection<TextInputControl> solmut =  getNodes(parent, TextInputControl.class, 
                        n -> n.getStyleClass().contains("kentta"), true);
        for (TextInputControl solmu : solmut) {
            if (solmu.getId() != null) {
                TextInputControl edit = solmu;
                String id = edit.getId();
                edits[Integer.valueOf(id) - 1] = edit; // koska id:t tulisi alkaa nollasta
                edit.setOnKeyReleased(e -> kasitteleMuutosTietueeseen((TextInputControl)(e.getSource())));
            }
        }
    }
    
    
    private void kasitteleMuutosTietueeseen(TextInputControl edit) {
        if (current == null) return;
        String s = edit.getText();
        int k = Integer.valueOf(edit.getId());
        String virhe = current.aseta(k, s); 
        if (virhe == null) {
            naytaVirhe(virhe);
        }
        else naytaVirhe(virhe);
    }


    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            virheText.setText("");
            return;
        }
        virheText.setText(virhe);
        virheText.getStyleClass().add("virhe");
    }
    /**
     * Poistutaan ikkunasta
     */
    private void handleCancel(){
        current = null;
        ModalController.closeStage(virheText);
    }
    
    /**
     * Tallennetaan muutokset
     */
    private void tallenna() {
        String text = virheText.getText();
        if ( text.equals("")) ModalController.closeStage(this.virheText);
        else {
            FadeTransition transition = new FadeTransition(Duration.ONE);
            transition.play();
        }
    }
    
    @Override
    public Kategoria getResult() {
        return current;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(Kategoria oletus) {
        // TODO Auto-generated method stub
        current = oletus;
        naytaKategoria(oletus);
    }

    /**
     * @param modalityStage mille ollaan modaalisia 
     * @param k näytettävä/käsiteltävä kategoria
     * @return muokattu/lisätty kategoria
     */
    public static Kategoria kysyKategoria(Stage modalityStage, Kategoria k) {
        return ModalController.showModal(
                KategoriaDialogController.class.getResource("KategoriaDialogView.fxml"),
                "muokkaus",
                modalityStage, k);
    }
    
    private void naytaKategoria(Kategoria kategoria) {
        if (kategoria == null) return;
        for (int i = apuK.ekaKentta(); i <= apuK.kenttaLkm(); i++) {
            edits[i-1].setText(kategoria.anna(i));
        }
    }

}
