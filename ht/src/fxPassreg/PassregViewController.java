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
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * @author Yahya
 * @version 19.1.2021
 * Pääohjelman kontrolleeri
 */
public class PassregViewController implements Initializable {

    
    private String tiedostonNimi = "Salasanat";
    @FXML private Button generoiButton;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Avaa uuden ikkunan, jossa generoidaan automaattinen salasana valitsemasi kenttien mukaan");
        generoiButton.setTooltip(tooltip);
    }
    
    
    /**
     * Käsitellään generoi-painikkeen toiminta
     */
    @FXML private void handleGeneroi() {
        avaaGeneroiDialog();
    }

    
    /**
     * Käsitellään tallenna-painikkeen toiminta
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Käsitellään avaa-painikkeen toiminta
     */
    @FXML private void handleAvaa() {
        avaaTiedosto();
    }

    
    /**
     * Käsitellään uusipaasy-painikkeen toiminta
     */
    @FXML private void handleUusiPaasy() {
        lisaaUusiPaasy();
    }
    
    
    /**
     * Käsitellään poista-menuvalinnan toiminta.
     */
    @FXML private void handlePoisto() {
        poista();
    }
    
    
    /**
     * Käsitellään lopeta-menuvalinnan toiminta.
     */
    @FXML private void handleLopeta() {lopeta();}
    
    
    /**
     * Käsitellään muokkaa paasy -menuvalinnan toiminta.
     */
    @FXML private void handleMuokkaaPaasy() {
        avaaPaasyDialog();
    }
    
    /**
     * Käsitellään tulosta-menuvalinnan toiminta.
     */
    @FXML private void handleTulostusDialog() {
        avaaTulostusDialog();
    }
    
    
    /**
     * Käsitellään tietoja-menuvalinnan toiminta.
     */
    @FXML private void handleAboutDialog() {
        avaaAboutDialog();
    }
    
    
    /**
     * Käsitellään Apua-menuvalinnan toiminta.
     */
    @FXML private void handleApua() {
        haeApua();
    }

    // #######################################################
    
    
    /**
     * Tallennetaan muutoksia tiedostoon.
     */
    private void tallenna() {
        //TODO: tähän oikeaa tallennustoimintaa
        Dialogs.showMessageDialog("Tallennetaan, mutta vielä ei toimi! ");
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
     * Luetaan tiedoston sisältöä
     * @param tiedosto luettava tiedosto
     */
    private void lueTiedosto(String tiedosto) {
        //TODO: Tähän järkevämpi tiedoston lukemista.
        Dialogs.showMessageDialog("Ei osata vielä lukea", dlg -> {dlg.titleProperty().setValue(tiedosto);});
    }
    
    
    /**
     * Lisätään uusi pääsy rekisteriin
     */
    private void lisaaUusiPaasy() {
        //TODO: Tähän oikeaa lisäystoimintaa
        Dialogs.showMessageDialog("Lisätään, mutta vielä ei toimi! ");
    }
    
    /**
     * Poistetaan valittu pääsy. Ennen varsinaista poistoa, kysytään josko käyttäjä on varma toiminnasta.
     */
    private void poista() {
        //TODO: tähän oikeaa poistotoimintaa
        Dialogs.showMessageDialog("Poistetaan, mutta vielä ei toimi! ");
    }
    
    
    /**
     * Ohjataan käyttäjä tarkempaa dokumentaatiota näyttävään web-sivuun.
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
     * Poistutaan ohjelmasta. Mikäli tallentamattomia muutoksia on, kysytään käyttäjältä onko hän varma.
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
     * Avataan pääsyn muokkausikkuna.
     */
    private void avaaPaasyDialog() {
        PaasyDialogController.naytaPaasyDialog();
    }


}
