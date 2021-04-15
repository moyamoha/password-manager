package fxPassreg;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.web.WebEngine;

/**
 * @author Yahya
 * @version 10.2.2021
 * Kontrolleri tulostuksen toiminnan hallitsemiseksi.
 */
public class TulostusDialogController implements ModalControllerInterface<String>, Initializable {
    
    @FXML private TextArea tulostusText;
    @FXML private Button tulostaButton;
    
    /**
     * Näytetään tulostusikkuna ei-modaalisena.
     * @return uusi tulostuskontrolleri
     */
    @FXML public static TulostusDialogController tulosta() {
        TulostusDialogController tulostusCtrl = 
                ModalController.showModeless(TulostusDialogController.class.getResource("PassregTulostusView.fxml"),
                                             "Tulostus", null);
        return tulostusCtrl;
    }
    
    /**
     * Käsitellään ok-buttonin toimintaa
     */
    @FXML private void handleOkButton() {
        handleOk();
    }
    
    // #####################################################################
    
    /**
     * kun käyttäjä painaa ok-painiketta, suljetaan tulostusikkuna.
     */
    private void handleOk() {
        ModalController.closeStage(tulostusText);
    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public void handleShown() {
        //
    }

    @Override
    public void setDefault(String oletus) {
        tulostusText.setText(oletus);
    }
    
    /**
     * @return viite tulostusalueeseen
     */
    public TextArea getTextArea() { return tulostusText;}

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Tooltip tooltip = new Tooltip("Tulostaa...");
        tulostaButton.setTooltip(tooltip);
        tulostaButton.setOnAction(e -> handleTulosta());
    }

    /**
     * Kun painetaan tulosta-painikkeen, tulostetaan tulostusalueen teksti
     */
    private void handleTulosta() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if ( job != null && job.showPrintDialog(null) ) {
            WebEngine webEngine = new WebEngine();
            webEngine.loadContent("<pre>" + tulostusText.getText() + "</pre>");
            webEngine.print(job);
            job.endJob();
        }
    }
    
}
