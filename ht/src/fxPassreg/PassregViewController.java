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
 * P‰‰ohjelman kontrolleeri
 */
public class PassregViewController implements Initializable {

    
    private String tiedostonNimi = "Salasanat";
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
    
    
    /**
     * K‰sitell‰‰n generoi-painikkeen toiminta
     */
    @FXML private void handleGeneroi() {
        generoi();
    }

    
    /**
     * K‰sitell‰‰n kopioileikepoydalle-painikkeen toiminta
     */
    @FXML private void handleKopioLeikePoydalle() {
        kopioLeikePoydalle();
    }

    
    /**
     * K‰sitell‰‰n tallenna-painikkeen toiminta
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * K‰sitell‰‰n avaa-painikkeen toiminta
     */
    @FXML private void handleAvaa() {
        avaaTiedosto();
    }

    
    /**
     * K‰sitell‰‰n uusipaasy-painikkeen toiminta
     */
    @FXML private void handleUusiPaasy() {
        lisaaUusiPaasy();
    }
    
    
    /**
     * K‰sitell‰‰n poista-menuvalinnan toiminta.
     */
    @FXML private void handlePoisto() {
        poista();
    }
    
    
    /**
     * K‰sitell‰‰n lopeta-menuvalinnan toiminta.
     */
    @FXML private void handleLopeta() {lopeta();}
    
    
    /**
     * K‰sitell‰‰n muokkaa paasy -menuvalinnan toiminta.
     */
    @FXML private void handleMuokkaaPaasy() {
        avaaPaasyDialog();
    }
    
    
    /**
     * K‰sitell‰‰n generoi salasana -menuvalinnan toiminta.
     */
    @FXML private void handleGeneroiDialog() {
        avaaGeneroiDialog();
    }
    
    
    /**
     * K‰sitell‰‰n tulosta-menuvalinnan toiminta.
     */
    @FXML private void handleTulostusDialog() {
        avaaTulostusDialog();
    }
    
    
    /**
     * K‰sitell‰‰n tietoja-menuvalinnan toiminta.
     */
    @FXML private void handleAboutDialog() {
        avaaAboutDialog();
    }
    
    
    /**
     * K‰sitell‰‰n Apua-menuvalinnan toiminta.
     */
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
     * Avataan luettava tiedosto ja palautetaan true jos avaaminen onnistuu
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
        //TODO: T‰h‰n j‰rkev‰mpi tiedoston lukemista.
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
        //TODO: T‰h‰n j‰rkev‰mpi kopioi-toiminta
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
    
    
    /**
     * Avataan ikkuna, jossa generoidaan salasana.
     */
    private void avaaGeneroiDialog() {
        GeneroiDialogController.naytaGeneroiIkkuna();
    }

    
    /**
     * Avataan ikkuna, jossa lukee ohjelman tiedot
     */
    private void avaaAboutDialog() {
        TiedotDialogController.naytaAboutDialog();
    }
    
    
    /**
     * Avataan tulostusikkuna.
     */
    private void avaaTulostusDialog() {
        TulostusDialogController.naytaTulostusIkkuna();
    }
    
    
    /**
     * Avataan p‰‰syn muokkausikkuna.
     */
    private void avaaPaasyDialog() {
        PaasyDialogController.naytaPaasyDialog();
    }


}
