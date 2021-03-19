package fxPassreg;

import java.awt.Desktop;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import kanta.CTreeView;
import passreg.Kategoria;
import passreg.Paasy;
import passreg.Passreg;
import passreg.Tietue;

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
    @FXML private ListChooser<Paasy> paasyChooser;
    @FXML private ScrollPane panelPaasy;
    @FXML private VBox puuVbox;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alustaPuu();
        hallitseSalasanaKentta();
        alustaPaasyLista();
        hyperLink.setOnMouseClicked(e -> { avaaLinkki(this.hyperLink.getText());
        this.hyperLink.setStyle("-fx-text-fill:#ff0000;"); } );
    }

    
    @FXML private void handleTallenna()         { tallenna(); }
    
    @FXML private void handleAvaa()             { avaaTiedosto(); }
    
    @FXML private void handleLopeta()           { lopeta();}

    @FXML private void handleLisaaUusiPaasy()   { uusiPaasy(); }
    
    @FXML private void handleMuokkaaPaasy()     { avaaPaasyDialogTaytettyna(); }
    
    @FXML private void handlePoistaPaasy()      { poistaPaasy(); }
    
    @FXML private void handleLisaaKategoria()   { lisaaUusiKategoria(); }
    
    @FXML private void handleMuokkaaKategoria() { muokkaaKategoria(); }

    @FXML private void handlePoistaKategoria()  { poistaKategoria(); }
    
    @FXML private void handleTulosta()          { tulosta(); }
    
    @FXML private void handleAboutDialog()      { avaaAboutDialog(); }
    
    @FXML private void handleApua()             { haeApua();}
    
   
    // #######################################################
    // omaa koodia tähän
    // #######################################################
    
    private Passreg passreg;
    private TextArea areaPaasy = new TextArea();   // TODO: poistettava
    private CTreeView<Tietue> kPuu;
    private Paasy valittuPaasy;
    private Kategoria valittuKategoria;
    
    /**
     * Alustetaan paasychooser
     */
    private void alustaPaasyLista() {
        panelPaasy.setContent(areaPaasy);
        panelPaasy.setFitToHeight(true);
        paasyChooser.addSelectionListener(e -> { 
            setValittuPaasy(paasyChooser.getSelectedObject());
            kPuu.getSelectionModel().select(null);
            setValittuKategoria(null);
            try (PrintStream ps = naytaPaasy()) {ps.close();} ;
        });
    }
    
    /**
     * Alustaa puunäkymä
     */
    private void alustaPuu() {
        kPuu = new CTreeView<Tietue>(new Kategoria(), "Kategoriat");
        puuVbox.getChildren().add(kPuu);
        kPuu.setMinHeight(427);
        kPuu.setOnMouseClicked(e -> {
            Tietue t = kPuu.getSelectedObject();
            if (t instanceof Paasy) {
                paasyChooser.getSelectionModel().select(null);
                setValittuPaasy((Paasy) t);
                setValittuKategoria(null);
                try(PrintStream ps = naytaPaasy()) { /*...*/ }
            }
            if (t instanceof Kategoria) {
                if (kPuu.isRoot(kPuu.getSelectionModel().getSelectedItem())) setValittuKategoria(null);
                else setValittuKategoria((Kategoria) t);
            }
        });
    }
    
    private PrintStream naytaPaasy() {
        if (getValittuPaasy() == null) return null;
        areaPaasy.setText("");
        PrintStream ps = TextAreaOutputStream.getTextPrintStream(areaPaasy);
        getValittuPaasy().tulosta(ps);
        return ps;
    }
    
    /**
     * Tallennetaan muutoksia tiedostoon.
     */
    private void tallenna() {
        if (! passreg.onMuutettu()) Dialogs.showMessageDialog("Ei tallentamattomia muutoksia");
        else {
            passreg.tallenna();
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Tallennettiin");
        }
    }
    
    /**
     * Näytetään ilmoitus ruudulle. 
     * @param aika kauanko ilmoitus jää
     * @param tyyppi minkätyyppinen ilmoitus
     * @param teksti mikä on ilmoituksen sisältö
     */
    private void naytaIlmoitus(double aika, AlertType tyyppi, String teksti) {
        Alert alert = new Alert(tyyppi);
        alert.setHeaderText(null); alert.setContentText(teksti);
        PauseTransition delay = new PauseTransition(Duration.seconds(aika));
        delay.setOnFinished(e -> alert.close());
        alert.show(); delay.play();
    }
    
    /**
     * Kysytään käyttäjältä onko hän varma ohjelman sulkemisesta
     * @return true jos tallentamattomia muutoksia löytyi tai 
     */
    public boolean voiSulkea() {
        if (passreg.onMuutettu()) {
            return Dialogs.showQuestionDialog("Suljetaanko?"
                    , "Tallentamattomia muutoksia löytyi! Haluatko silti sulkea ohjelman?",
                    "kyllä", "ei");
        }
        return true;
    }
    
    
    /**
     * Avataan luettava tiedosto ja palautetaan true jos avaaminen onnistuu
     * @return true jos avaaminen onnistui
     */
    public boolean avaaTiedosto() {
        String uusTiedostonNimi = AloitusIkkunaController.kysyTiedosto(null, "passreg");
        if (uusTiedostonNimi == null) return false;
        if (uusTiedostonNimi.equals("")) uusTiedostonNimi = "passreg";
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
                return ( ! naytaCheckBox.isSelected());
            }
        };
        passField.visibleProperty().bind(bb);
    }
    
    
    /**
     * Luetaan tiedoston sisältöä
     * @param tiedosto luettava tiedosto
     */
    private void lueTiedosto(String nimi) {
        ModalController.getStage(panelPaasy).setTitle("Passreg - " + nimi);
        passreg.lueTiedostosta(nimi);
        naytaPaasytListaan();
        naytaPuuElementit();
    }
    
    private void uusiPaasy() {
        Kategoria k = getValittuKategoria();
        Paasy entry;
        if (k == null) {
            naytaIlmoitus(1, AlertType.INFORMATION, "Valitse jokin kategoria");
            return;
        }
        entry = new Paasy(k.getTunnusNro());
        entry.rekisteroi();
        entry.taytaGmailTiedoilla();
        passreg.lisaa(entry);
        hae(entry.getTunnusNro());
        setValittuPaasy(entry);
        lisaaPaasyPuuhun(entry, entry.getKategoriaId());
    }
    
    /**
     * Lisätään yksittäinen pääsy puuhun
     * @param p lisättävä pääsy
     * @param kategoriaId lisättävän pääsyn kategorian tunnusnumero
     */
    private void lisaaPaasyPuuhun(Paasy p, int kategoriaId) {
        for (TreeItem<Tietue> t : kPuu.getRoot().getChildren()) {
            if (t.getValue().getTunnusNro() == kategoriaId) {
                kPuu.getSelectionModel().select(t);
                kPuu.addToSelected(p, p.getView());
                setValittuKategoria((Kategoria)t.getValue());
                return;
            }
        }
    }

    private void hae(int pnro) {
        paasyChooser.clear();
        int index = 0;
        for (int i = 0; i < passreg.getPaasytLkm(); i++) {
            Paasy p = passreg.annaPaasy(i);
            if (p == null) continue;
            if (p.getTunnusNro() == pnro) index = i;
            paasyChooser.add(p.getOtsikko(), p);
        }
        paasyChooser.setSelectedIndex(index);
    }

    /**
     * Asettaa kategoriat puurakenteeseen.
     */
    private void naytaPuuElementit() {
        kPuu.getRoot().getChildren().clear();
        for (int i = 0; i < passreg.getKategoriatLkm(); i++) {
            Kategoria lisattava = passreg.annaKategoria(i);
            if (lisattava != null) {
                kPuu.add(lisattava, lisattava.getView());
                List<Paasy> paasyt = passreg.getPaasyt(lisattava.getTunnusNro());
                for (Tietue p : paasyt) {
                    kPuu.addToSelected(p, p.getView());
                }
            }
        }
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
        Paasy poistettava = getValittuPaasy();
        if (poistettava == null) return;
        if (Dialogs.showQuestionDialog("Poisto?", "Haluatko varmasti poistaa " + poistettava.getView() + " ?", "kyllä", "ei")) {
            this.passreg.poistaPaasy(poistettava.getTunnusNro());
            naytaPaasytListaan();
            naytaPuuElementit();
            setValittuPaasy(paasyChooser.getSelectedObject());
        }
        else return;
    }

    /** Näytetään pääsyt paasyChooser:iin */
    private void naytaPaasytListaan() {
        paasyChooser.clear();
        for (int i = 0; i < passreg.getPaasytLkm(); i++) {
            Paasy p = passreg.annaPaasy(i);
            if (p == null) continue;
            paasyChooser.add(p.getOtsikko(), p);
        }
        if (paasyChooser.getObjects().size() == 0) areaPaasy.setText("");
    }

    /** Avataan kategorian muokkaikkuna tyhjänä */
    private void lisaaUusiKategoria() {
        Kategoria kg1 = new Kategoria();
        kg1.rekisteroi();
        passreg.lisaa(kg1);
        kPuu.add(kg1, kg1.getNimi());
        setValittuKategoria(kg1);
    }
    
    /** Avataan kategorian muokkausikkuna täytettynä valitun kategorian nimellä */
    private void muokkaaKategoria() {
        KategoriaDialogController kdc = new KategoriaDialogController();
        kdc.naytaKategoriaDialog();
    }
    
    /** Poistetaan valittu kategoria */
    private void poistaKategoria() {
        Kategoria valittu =  getValittuKategoria();
        if (valittu == null) return;
        String viesti = "Haluatko varmasti poistaa " + valittu.getView() + "? Tämän poistaminen poistaa kaikki tähän kategoriaan kuuluvat pääsyt!!!"; 
        if (Dialogs.showQuestionDialog("Poisto?", viesti, "kyllä", "ei")) {
            passreg.poistaKategoria(valittu.getTunnusNro());
            kPuu.remove(valittu);
            kPuu.getSelectionModel().select(null);
            setValittuKategoria(null);
            naytaPaasytListaan();
        }
    }
    
    /** Ohjataan käyttäjä tarkempaa dokumentaatiota näyttävään web-sivuun. */
    private void haeApua() { avaaLinkki("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"); }
    
    /** Avataan ikkuna, jossa lukee ohjelman tiedot */
    private void avaaAboutDialog() { TiedotDialogController.naytaAboutDialog(); }
    
    /**
     * Avataan linkki
     * @param url linkki jota avataan
     */
    private void avaaLinkki(String url) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(url));
        }
        catch (Exception e) {return;} 
    }
    
    /** Poistutaan ohjelmasta jos ei ole tallentamattomia muutoksia. */
    public void lopeta() {
        if (voiSulkea()) Platform.exit();
    }
    
    /** Avataan tulostusikkuna ja tulostetaan siihen valitun kategorian kaikki pääsyt.*/
    private void tulosta() {
        Kategoria k = getValittuKategoria();
        if ( k == null) {
            naytaIlmoitus(2, AlertType.WARNING, "Valitse jokin kategoria, jonka pääsyt haluat tulostaa");
            return;
        }
        TulostusDialogController tulostusCtrl = TulostusDialogController.tulosta();
        TextArea alue = tulostusCtrl.getTextArea();
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(alue)) {
            os.println("Tulostetaan kaikki tämän kategorian tiedot");
            os.println();
            k.tulosta(os);
            os.print("\n\n");
            for (Paasy p: passreg.getPaasyt(k.getTunnusNro())) { 
                p.tulosta(os);
                os.println("\n======================================");
            }
        }
    }

    /**
     * Asetetaan kontrollerille salasanarekisteri
     * @param passrekisteri asettava rekisteri-olio
     */
    public void setPassreg(Passreg passrekisteri) {
        this.passreg = passrekisteri;
    }

    /** @return valittu Paasy*/
    private Paasy getValittuPaasy()        { return valittuPaasy; }

    /** @return the valittuKategoria */
    private Kategoria getValittuKategoria() { return valittuKategoria; }
    
    /** @param valittuPaasy the valittuPaasy to set */
    private void setValittuPaasy(Paasy valittuPaasy)             { this.valittuPaasy = valittuPaasy; }

    /** @param valittuKategoria the valittuKategoria to set*/
    private void setValittuKategoria(Kategoria valittuKategoria) { this.valittuKategoria = valittuKategoria; }

}
