package fxPassreg;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import passreg.Kategoria;
import passreg.Paasy;
import passreg.Passreg;

/**
 * @author Yahya
 * @version 19.1.2021
 * Pääikkunan kontrolleeri
 */
public class PassregViewController implements Initializable {

    @FXML private PasswordField passField;
    @FXML private TextField passText;
    @FXML private CheckBox naytaCheckBox;
    @FXML private Hyperlink hyperLink;
    @FXML private TreeView<String> treeView;
    @FXML private ListChooser<Paasy> paasyLista;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       // Tähän tarvittavat alustukset
        TreeItem<String> root = new TreeItem<String>("Kategoriat");
        treeView.setRoot(root);       
    }
    
    /**
     * Käsitellään hyperlinkin toimintaa
     */
    @FXML public void handleHyperLink() {
        avaaHyperLink();
    }
    
    /**
     * Käsitellään tallenna-painikkeen toimintaa
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Käsitellään avaa-painikkeen toimintaa
     * @throws IOException jos lukeminen epäonnistuu
     */
    @FXML private void handleAvaa() throws IOException {
        avaaTiedosto();
    }
    
    
    @FXML private void handleNaytaCheckBox() {
        if (this.naytaCheckBox.isSelected()) naytaSalasana();
        else piilotaSalasana();
    }
    
    
    /**
     * Käsitellään lopeta-menuvalinnan toimintaa. Tallennetaan muutokset sitä ennen.
     */
    @FXML private void handleLopeta() { lopeta();}
    
    
    /**
     * Käsitellään muokkaa paasy -menuvalinnan toimintaa
     */
    @FXML private void handleLisaaUusiPaasy() {
        avaaPaasyDialogTyhjana();
    }
    
    
    /**
     * Käsitellään muokkaa paasy -menuvalinnan toiminta.
     */
    @FXML private void handleMuokkaaPaasy() {
        avaaPaasyDialogTaytettyna();
    }
    
    
    /**
     * Käsitellään poista pääsy -menuvalinnan toiminta.
     */
    @FXML private void handlePoistaPaasy() {
        poistaPaasy();
    }
   
    
    /**
     * Käsitellään lisää uusi kategoria -menuvalinnan toimintaa
     */
    @FXML private void handleLisaaKategoria() {
        lisaaUusiKategoria();
    }
    
    /**
     * Käsitellään muokkaa kategorian nimi -menuvalinnan toimintaa
     */
    @FXML private void handleMuokkaaKategoria() {
        muokkaaKategoria();
    }
    
    /**
     * Käsitellään poista kategoria -menuvalinnan toimintaa
     */
    @FXML private void handlePoistaKategoria() {
        poistaKategoria();
    }
    
    
    /**
     * Käsitellään tulosta-menuvalinnan toiminta.
     */
    @FXML private void handleTulosta() {
        tulosta();
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
    // 
    // 
    // #######################################################
    
    private String tiedostonNimi = "";
    private Passreg passreg;
    
    
    /**
     * Avataan hyperlinkki. Kerran kun se on avattu, muuttuu sen väri punaiseksi
     */
    private void avaaHyperLink() {
        avaaLinkki(this.hyperLink.getText());
        this.hyperLink.setStyle("-fx-text-fill:#ff0000;");
    }
    
    
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
     * @throws IOException jos lukeminen epäonnistuu
     */
    public boolean avaaTiedosto() throws IOException {
        String uusTiedostonNimi = AloitusIkkunaController.kysyTiedosto(null, tiedostonNimi);
        if(uusTiedostonNimi == null) return false;
        lueTiedosto(uusTiedostonNimi);
        return true;
    }
    
    
    /**
     * Näytetään salasana luettavaksi
     */
    public void naytaSalasana() {
        passText.setText(passField.getText());
        passText.setVisible(true); passField.setVisible(false);
    }
    
    
    /**
     * Piilottaa salasanan
     */
    private void piilotaSalasana() {
        passField.setText(passText.getText());
        passText.setVisible(false);
        passField.setVisible(true);
    }
    
    
    /**
     * Luetaan tiedoston sisältöä
     * @param tiedosto luettava tiedosto
     * @throws IOException jos lukeminen epäonnistuu
     */
    private void lueTiedosto(String tiedosto) throws IOException {
        //TODO: Tähän järkevämpi tiedoston lukemista.
        //this.passreg.lueTiedostosta(tiedosto);
        Dialogs.showMessageDialog("Ei osata vielä lukea", dlg -> {dlg.titleProperty().setValue(tiedosto);});
        //Dialogs.showMessageDialog("Tiedosto " + tiedosto + " ei aukea!!");
    }
    
    
    /**
     * Avataan pääsyn muokkausikkuna tyhjänä
     */
    private void avaaPaasyDialogTyhjana() {
        //TODO: Tähän oikeaa lisäystoimintaa
        PaasyDialogController.naytaPaasyDialog();
    }
    
    
    /**
     * Asettaa kategoriat puurakenteeseen.
     */
    private void naytaPuuElementit() {
        /*List<Kategoria> kategoriat = getKategoriat();
        for (Kategoria ktg : kategoriat) {
            treeView.getRoot().getChildren().add(new TreeItem<String>(ktg.getNimi()));
        }*/
        treeView.getRoot().getChildren().add(new TreeItem<String>("some"));
        treeView.getRoot().getChildren().add(new TreeItem<String>("muu"));
        treeView.getRoot().getChildren().add(new TreeItem<String>("työ"));
    }
    
    
    /**
     * Palauttaa listan rekisterin kategorioista.
     * @return rekisterin kategoriat
     */
    @SuppressWarnings("unused")
    private List<Kategoria> getKategoriat() {
        // TODO Auto-generated method stub
       List<Kategoria> kgt = new ArrayList<>();
       for (int i = 0; i < passreg.getKategoriatLkm(); i++) {
           kgt.add(passreg.annaKategoria(i));
       }
       return kgt;
    }

    /**
     * Avataan pääsyn muokkausikkuna täytettynä valitun pääsyn tiedoilla
     */
    private void avaaPaasyDialogTaytettyna() {
        PaasyDialogController.naytaPaasyDialog();
    }
    
    
    /**
     * Poistetaan valittu pääsy. Ennen varsinaista poistoa, kysytään josko käyttäjä on varma toiminnasta.
     */
    private void poistaPaasy() {
        //TODO: tähän oikeaa poistotoimintaa
        Dialogs.showMessageDialog("Poistetaan, mutta vielä ei toimi! ");
    }
    
    
    /**
     * Avataan kategorian muokkaikkuna tyhjänä
     */
    private void lisaaUusiKategoria() {
        KategoriaDialogController.naytaKategoriaDialog();
    }
    
    
    /**
     * Avataan kategorian muokkausikkuna täytettynä valitun kategorian nimellä
     */
    private void muokkaaKategoria() {
        KategoriaDialogController.naytaKategoriaDialog();
    }
    
    
    /**
     * Poistetaan valittu kategoria
     */
    private void poistaKategoria() {
        Dialogs.showMessageDialog("Poistetaan, mutta vielä ei toimi");
    }
    
    
    /**
     * Ohjataan käyttäjä tarkempaa dokumentaatiota näyttävään web-sivuun.
     * Sivu on "https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"
     */
    private void haeApua() {
        avaaLinkki("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg");   
    }
    
    
    /**
     * Avataan linkki
     * @param url linkki jota avataan
     */
    private void avaaLinkki(String url) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(url));
        }
        catch (URISyntaxException e) {return;}
        catch (IOException e) {return;}      
    }
    
    
    /**
     * Poistutaan ohjelmasta. Mikäli tallentamattomia muutoksia on, kysytään käyttäjältä onko hän varma.
     */
    private void lopeta() {
        tallenna();
        Platform.exit();
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
    private void tulosta() {
        TulostusDialogController.naytaTulostusIkkuna();
    }

    /**
     * Asetetaan kontrollerille salasanarekisteri
     * @param passrekisteri asettava rekisteri-olio
     */
    public void setPassreg(Passreg passrekisteri) {
        // TODO Auto-generated method stub
        this.passreg = passrekisteri;
        naytaPuuElementit();
        naytaPaasyt();
    }

    private void naytaPaasyt() {
        // TODO naytetaan pääsyt
        
    }

}
