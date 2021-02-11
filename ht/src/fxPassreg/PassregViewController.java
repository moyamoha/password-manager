package fxPassreg;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * @author Yahya
 * @version 19.1.2021
 *
 */
public class PassregViewController implements Initializable {
	// Ei viel‰ mit‰‰n
    
    private String tiedostonNimi = "Salasanat";
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    @FXML
    void handleGeneroi() {
        generoi();
    }

    
    @FXML
    void handleKopioLeikePoydalle() {
        kopioLeikePoydalle();
    }

    
    @FXML
    void handleTallenna() {
        tallenna();
    }
    
    @FXML
    private void handleAvaa() {
        avaaTiedosto();
    }

    @FXML
    private void handleUusiPaasy() {
        lisaaUusiPaasy();
    }
    
    @FXML
    private void handlePoisto() {
        poista();
    }
    
    @FXML 
    private void handleLopeta() {lopeta();}
    
    @FXML private void handleMuokkaaPaasy() {
        avaaPaasyDialog();
    }
    
    @FXML private void handleGeneroiDialog() {
        avaaGeneroiDialog();
    }
    
    @FXML private void handleTulostusDialog() {
        avaaTulostusDialog();
    }
    
    
    @FXML private void handleAboutDialog() {
        avaaAboutDialog();
    }
    
    @FXML private void handleApua() {
        haeApua();
    }
    
    

    // #######################################################
    
    
    /**
     * Generoidaan k‰ytt‰j‰lle salasana
     */
    private void generoi() {
        //TODO: T‰h‰n oikeaa generoimistoimintaa
        Dialogs.showMessageDialog("Generoidaan, mutta viel‰ ei toimi! ");
    }
    
    
    /**
     * Tallennetaan muutoksia tiedostoon.
     */
    private void tallenna() {
        //TODO: t‰h‰n oikeaa tallennustoimintaa
        Dialogs.showMessageDialog("Tallennetaan, mutta viel‰ ei toimi! ");
    }
    
    
    /**
     * Avataan luettava tiedosto
     * @return true jos avaaminen onnistui
     */
    public boolean avaaTiedosto() {
        String uusTiedostonNimi = AloitusIkkunaController.kysyTiedosto(null, tiedostonNimi);
        if(uusTiedostonNimi == null) return false;
        lueTiedosto(uusTiedostonNimi);
        return true;
    }
    
    
    /**
     * Luetaan tiedoston sis‰ltˆ‰
     * @param tiedosto luettava tiedosto
     */
    private void lueTiedosto(String tiedosto) {
        Dialogs.showMessageDialog("Ei osata viel‰ lukea", dlg -> {dlg.titleProperty().setValue(tiedosto);});
    }
    
    
    /**
     * Lis‰t‰‰n uusi p‰‰sy rekisteriin
     */
    private void lisaaUusiPaasy() {
        //TODO: T‰h‰n oikeaa lis‰ystoimintaa
        Dialogs.showMessageDialog("Lis‰t‰‰n, mutta viel‰ ei toimi! ");
    }
    
    
    /**
     * Otetaan generoidusta salasanasta kopio leikepˆyd‰lle
     */
    private void kopioLeikePoydalle() {
        Dialogs.showMessageDialog("Kopioidaan, mutta viel‰ ei toimi! ");
    }
    
    
    /**
     * Poistetaan valittu p‰‰sy. Ennen varsinaista poistoa, kysyt‰‰n josko k‰ytt‰j‰ on varma toiminnasta.
     */
    private void poista() {
        //TODO: t‰h‰n oikeaa poistotoimintaa
        Dialogs.showMessageDialog("Poistetaan, mutta viel‰ ei toimi! ");
    }
    
    
    /**
     * Ohjataan k‰ytt‰j‰ tarkempaa dokumentaatiota n‰ytt‰v‰‰n web-sivuun.
     * Sivu on "https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"
     */
    private void haeApua() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"));
        }
        catch (URISyntaxException e) {return;}
        catch (IOException e) {return;}      
    }
    
    
    /**
     * Poistutaan ohjelmasta. Mik‰li tallentamattomia muutoksia on, kysyt‰‰n k‰ytt‰j‰lt‰ onko h‰n varma.
     */
    private void lopeta() {
        Platform.exit();
    }
    
    private void avaaGeneroiDialog() {
        GeneroiDialogController.naytaGeneroiIkkuna();
    }

    private void avaaAboutDialog() {
        TiedotDialogController.naytaAboutDialog();
    }
    
    private void avaaTulostusDialog() {
        TulostusDialogController.naytaTulostusIkkuna();
    }
    
    private void avaaPaasyDialog() {
        // TODO Auto-generated method stub
        PaasyDialogController.naytaPaasyDialog();
    }


}
