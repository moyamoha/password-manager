package fxPassreg;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import passreg.Paasy;
import passreg.Passreg;

/**
 * @author Yahya
 * @version 19.1.2021
 * Pääikkunan kontrolleeri
 */
public class PassregGUIController implements Initializable {

    @FXML private PasswordField passField;
    @FXML private TextField passText;
    @FXML private CheckBox naytaCheckBox;
    @FXML private Hyperlink hyperLink;
    @FXML private TreeView<String> treeView;
    @FXML private ListChooser<Paasy> paasyLista;
    @FXML private ScrollPane panelPaasy;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       // Tähän tarvittavat alustukset
        TreeItem<String> root = new TreeItem<String>("Kategoriat");
        treeView.setRoot(root); 
        hallitseSalasanaKentta();
        alusta();
    }
    
    @FXML private void handleHyperLink()        { avaaHyperLink(); }
    
    @FXML private void handleTallenna()         { tallenna(); }
    
    @FXML private void handleAvaa() throws IOException {avaaTiedosto(); }
    
    @FXML private void handleLopeta()           { lopeta();}
    
    @FXML private void handleLisaaUusiPaasy()   { uusiPaasy(); }
    
    @FXML private void handleMuokkaaPaasy()     { avaaPaasyDialogTaytettyna(); }
    
    @FXML private void handlePoistaPaasy()      { poistaPaasy(); }
    
    @FXML private void handleLisaaKategoria()   { lisaaUusiKategoria(); }
    
    @FXML private void handleMuokkaaKategoria() { muokkaaKategoria(); }

    @FXML private void handlePoistaKategoria()  { poistaKategoria(); }
    
    @FXML private void handleTulosta()          { tulosta(); }
    
    @FXML private void handleAboutDialog()      { avaaAboutDialog(); }
    
    @FXML private void handleApua()             { haeApua(); }
   
    // #######################################################
    // 
    // 
    // #######################################################
    
    private String tiedostonNimi = "";
    @SuppressWarnings("unused")
    private Passreg passreg;
    private TextArea areaPaasy = new TextArea();   // TODO: poista kun ei enää tarvita. Tilapäinen
    
    
    private void alusta() {
        // TODO Auto-generated method stub
        panelPaasy.setContent(areaPaasy);
        panelPaasy.setFitToHeight(true);
        paasyLista.clear();
        paasyLista.addSelectionListener(e -> naytaPaasy());
    }
    
    
    private void naytaPaasy() {
        Paasy valittuPaasy = paasyLista.getSelectedObject();
        if (valittuPaasy == null) return;
        areaPaasy.setText("");
        @SuppressWarnings("resource")
        PrintStream ps = TextAreaOutputStream.getTextPrintStream(areaPaasy);
        valittuPaasy.tulosta(ps);
    }

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
     * Kytketään passText ja passFied -kentät toisiinsä. Eli molemmilla tulee olemaan sama teksti.
     */
    private void hallitseSalasanaKentta() {
        passText.textProperty().bindBidirectional(passField.textProperty());
        BooleanBinding bb = new BooleanBinding() {
            { super.bind(naytaCheckBox.selectedProperty());}
            @Override
            protected boolean computeValue() {
                // TODO Auto-generated method stub
                return ( ! naytaCheckBox.isSelected());
            }
        };
        passField.visibleProperty().bind(bb);
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
    
    
    private void uusiPaasy() {
        Paasy entry = new Paasy();
        entry.rekisteroi();
        entry.taytaGmailTiedoilla();
        passreg.lisaa(entry);
        hae(entry.getTunnusNro());
    }
    
    
    private void hae(int pnro) {
        paasyLista.clear();
        int index = 0;
        for (int i = 0; i < passreg.getPaasytLkm(); i++) {
            Paasy p = passreg.annaPaasy(i);
            if (p.getTunnusNro() == pnro) index = i;
            paasyLista.add(p.getOtsikko(), p);
        }
        paasyLista.setSelectedIndex(index);
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
    }

}
