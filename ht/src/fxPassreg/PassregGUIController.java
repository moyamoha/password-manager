package fxPassreg;

import java.awt.Desktop;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
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
 * P��ikkunan kontrolleeri
 */
public class PassregGUIController implements Initializable {

    @FXML private PasswordField passField;
    @FXML private TextField hakuKentta;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private TextField passText;
    @FXML private CheckBox naytaCheckBox;
    @FXML private Hyperlink hyperLink;
    @FXML private ListChooser<Paasy> paasyChooser;
    @FXML private ScrollPane panelPaasy;
    @FXML private VBox puuVbox;

    
    @FXML private void handleTallenna()         { tallenna(); }
    
    @FXML private void handleAvaa()             { avaaTiedosto(false); }
    
    @FXML private void handleLopeta()           { lopeta();}

    @FXML private void handleLisaaUusiPaasy()   { uusiPaasy(); }
    
    @FXML private void handleMuokkaaPaasy()     { muokkaa(); }
    
    @FXML private void handlePoistaPaasy()      { poistaPaasy(); }
    
    @FXML private void handleLisaaKategoria()   { lisaaUusiKategoria(); }
    
    @FXML private void handleMuokkaaKategoria() { muokkaaKategoria(); }

    @FXML private void handlePoistaKategoria()  { poistaKategoria(); }
    
    @FXML private void handleTulosta()          { tulosta(); }
    
    @FXML private void handleAboutDialog()      { avaaAboutDialog(); }
    
    @FXML private void handleApua()             { haeApua();}
    
    @FXML private void handleHae()              { etsiPaasyt(); }
    
   
    // #######################################################
    // omaa koodia t�st� eteenp�in
    
    private Passreg passreg;
    private TextArea areaPaasy = new TextArea();   // TODO: poistettava
    private CTreeView<Tietue> puu;
    private Paasy valittuPaasy;
    private Kategoria valittuKategoria;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alustaPuu();
        hallitseSalasanaKentta();
        alustaPaasyLista();
        hyperLink.setOnMouseClicked(e -> { avaaLinkki(hyperLink.getText());
        hyperLink.setStyle("-fx-text-fill:#ff0000;"); } );
    }

    /** Tallennetaan muutoksia tiedostoon, mik�li tallentamattomia muutoksia l�ytyy*/
    private void tallenna() {
        if (! passreg.onMuutettu()) naytaIlmoitus(1.5, AlertType.INFORMATION, "Ei tallentamattomia muutoksia");
        else {
            passreg.tallenna();
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Tallennettiin");
        }
    }
    
    /**
     * Avataan luettava tiedosto ja palautetaan true jos avaaminen onnistuu
     * @param onEkaKerta kertoo missa vaiheessa avaaminen tapahtuu. </br>Pit�isi olla true jos avataan tiedosto ohjelman k�ynnistyess�
     * @return true jos avaaminen onnistui
     */
    public boolean avaaTiedosto(boolean onEkaKerta) {
        String uusTiedostonNimi = AloitusIkkunaController.kysyTiedosto(null, "passreg");
        if (uusTiedostonNimi == null) return false;
        if (uusTiedostonNimi.equals("")) uusTiedostonNimi = "passreg";
        if (!onEkaKerta) {
            tallenna();  // Tallennetaan ettei menetett�isi nykyisen rekisterin tietoja
        }
        lueTiedosto(uusTiedostonNimi);
        return true;
    }
    
    /**
     * Luetaan tiedoston sis�lt��
     * @param tiedosto luettava tiedosto
     */
    private void lueTiedosto(String nimi) {
        ModalController.getStage(panelPaasy).setTitle("Passreg - " + nimi);
        passreg.lueTiedostosta(nimi);
        paivitaPuu();
    }
  
    /** Luodaan uusi p��sy ja lis�t��n rekisteriin sek� puun�kym��n n�kyv�ksi*/
    private void uusiPaasy() {
        Kategoria k = valittuKategoria;
        Paasy entry;
        if (k == null) {
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Valitse jokin kategoria");
            return;
        }
        entry = new Paasy(k.getTunnusNro());
        entry.rekisteroi();
        entry.taytaGmailTiedoilla();
        passreg.lisaa(entry);
        valittuPaasy = entry;
        lisaaPaasyPuuhun(entry, entry.getKategoriaId());
        etsiPaasyt();
    }
    
    /**
     * Avataan p��syn muokkausikkuna t�ytettyn� valitun p��syn tiedoilla
     */
    private void muokkaa() {
        PaasyDialogController.kysyPaasy();
        /*if (valittuPaasy == null) return;
        try {
            Paasy p;
            p = valittuPaasy.clone();
            p = PaasyDialogController.kysyPaasy(null, p);
            if (p == null) return;
            passreg.korvaaTaiLisaa(p);
            paivitaPuu();
            
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }*/
        
    }

    /**
     * Poistetaan valittu p��sy. Ennen varsinaista poistoa, kysyt��n josko k�ytt�j� on varma toiminnasta.
     */
    private void poistaPaasy() {
        Paasy poistettava = valittuPaasy;
        if (poistettava == null) return;
        if (Dialogs.showQuestionDialog("Poisto?", "Haluatko varmasti poistaa " + poistettava.getView() + "?", "kyll�", "ei")) {
            passreg.poistaPaasy(poistettava.getTunnusNro());
            paivitaPuu();
            valittuPaasy = null;
            if (paasyChooser.getObjects().contains(poistettava)) etsiPaasyt();
        }
        else return;
    }

    /** Avataan kategorian muokkaikkuna tyhj�n� */
    private void lisaaUusiKategoria() {
        Kategoria k = new Kategoria();
        k.rekisteroi();
        passreg.lisaa(k);
        puu.add(k, k.getView());
        valittuKategoria = k;
    }
    
    /** Avataan kategorian muokkausikkuna t�ytettyn� valitun kategorian nimell� */
    private void muokkaaKategoria() {
        KategoriaDialogController kdc = new KategoriaDialogController();
        kdc.naytaKategoriaDialog();
    }
    
    /** Poistetaan valittu kategoria */
    private void poistaKategoria() {
        Kategoria v = valittuKategoria;
        if (v == null) {
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Valitse jokin kategoria!!"); return;
        }
        String viesti = "Haluatko varmasti poistaa " + v.getView() + "? T�m�n poistaminen poistaa kaikki t�h�n kategoriaan kuuluvat p��syt!!!"; 
        if (Dialogs.showQuestionDialog("Poisto?", viesti, "kyll�", "ei")) {
            passreg.poistaKategoria(v.getTunnusNro());
            puu.remove(v);
            puu.getSelectionModel().select(null);
            valittuKategoria = null;
            valittuPaasy = null;
            etsiPaasyt();
        }
    }
    
    /**
     *  Ohjataan k�ytt�j� tarkempaa dokumentaatiota n�ytt�v��n web-sivuun.
     */
    private void haeApua() { avaaLinkki("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"); }
    
    /**
     * Etsit��n p��syt ja lajitellaan annetun hakukent�n ja hakuehdon mukaan.
     */
    private void etsiPaasyt() {
        String kentta = hakuKentta.getText();
        if (kentta == null || kentta.equals("")) {
            paasyChooser.clear();
            return;
        }
        Collection<Paasy> paasyt =  passreg.getPaasyt(cbKentat.getSelectedText(), kentta);
        naytaPaasytListaan(paasyt);
    }
    
    /** 
     * Avataan ikkuna, jossa lukee ohjelman tiedot 
     */
    private void avaaAboutDialog() { TiedotDialogController.naytaAboutDialog(); }
    
    /** Poistutaan ohjelmasta jos ei ole tallentamattomia muutoksia. */
    public void lopeta() {
        if (voiSulkea()) Platform.exit();
    }
    
    /** Avataan tulostusikkuna ja listataan kategoriat p��syineen valmiiksi tulostettavaksi*/
    private void tulosta() {
        TulostusDialogController tulostusCtrl = TulostusDialogController.tulosta();
        TextArea alue = tulostusCtrl.getTextArea();
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(alue)) {
            os.println("Tulostetaan kaikki p��syt"); os.println();
            for (int i = 0; i < passreg.getKategoriatLkm(); i++) {
                Kategoria k = passreg.annaKategoria(i);
                Collection<Paasy> pst = passreg.getPaasyt(k.getTunnusNro());
                os.print("");
                k.tulosta(os); os.println();
                for (Paasy p : pst) {
                    p.tulosta(os); os.println("===============================");
                }
                os.println();
            }
            
        }
    }
    
    //############# 
    // T�st� eteenp�in tulee l�hinn� apumetodit
    // TODO: siirr� muut apuMetodit t�h�n

    /**
     * Asetetaan kontrollerille salasanarekisteri
     * @param passrekisteri asettava rekisteri-olio
     */
    public void setPassreg(Passreg passrekisteri) {
        passreg = passrekisteri;
    }
    
    /**
     * N�ytet��n ilmoitus ruudulle. 
     * @param aika kauanko ilmoitus j��
     * @param tyyppi mink�tyyppinen ilmoitus
     * @param teksti mik� on ilmoituksen sis�lt�
     */
    private void naytaIlmoitus(double aika, AlertType tyyppi, String teksti) {
        Alert alert = new Alert(tyyppi);
        alert.setHeaderText(null); alert.setContentText(teksti);
        PauseTransition delay = new PauseTransition(Duration.seconds(aika));
        delay.setOnFinished(e -> alert.close());
        alert.show(); delay.play();
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
        catch (Exception e) {return;} 
    }
    
    /** N�ytet��n p��syt paasyChooser:iin */
    private void naytaPaasytListaan(Collection<Paasy> pst) {
        paasyChooser.clear();
        for (Paasy p : pst) {
            paasyChooser.add(p.getView(), p);
        }
    }
    
    /** Alustetaan paasychooser */
    private void alustaPaasyLista() {
        panelPaasy.setContent(areaPaasy);
        panelPaasy.setFitToHeight(true);
        paasyChooser.setOnMouseClicked(e -> { 
            valittuPaasy = paasyChooser.getSelectedObject();
            valittuKategoria = null;
            puu.getSelectionModel().select(null);
            naytaPaasy();
        });
    }
    
    /** Alustaa puun�kym� */
    private void alustaPuu() {
        puu = new CTreeView<Tietue>(new Kategoria(), "Kategoriat");
        puuVbox.getChildren().add(puu);
        puu.setMinHeight(427);
        puu.setOnMouseClicked(e -> {
            Tietue t = puu.getSelectedObject();
            if (t instanceof Paasy) {
                valittuPaasy = (Paasy) t;
                valittuKategoria = null;
                paasyChooser.getSelectionModel().select(null);
                naytaPaasy();
            }
            if (t instanceof Kategoria) {
                if (puu.isRoot(puu.getSelectionModel().getSelectedItem())) valittuKategoria = null;
                else {
                    valittuKategoria = (Kategoria) t;
                    paasyChooser.getSelectionModel().select(null);
                    valittuPaasy = null;
                }
            }
        });
    }
    
    /** N�ytet��n p��sy suureen tekstikentt��n. Tilapaisesti*/
   //TODO: Poistettava kun p��syn tiedot osataan n�ytt�� kukin kentt� omaan tekstikentt��n
    private void naytaPaasy() {
        if (valittuPaasy == null) return;
        areaPaasy.setText("");
        try (PrintStream ps = TextAreaOutputStream.getTextPrintStream(areaPaasy)) {
            valittuPaasy.tulosta(ps);
        };
    }
    
    /**
     * Kysyt��n k�ytt�j�lt� onko h�n varma ohjelman sulkemisesta
     * @return true jos tallentamattomia muutoksia l�ytyi tai 
     */
    public boolean voiSulkea() {
        if (passreg.onMuutettu()) {
            return Dialogs.showQuestionDialog("Suljetaanko?", 
                    "Tallentamattomia muutoksia l�ytyi! Haluatko silti sulkea ohjelman?", "kyll�", "ei");
        } return true;
    }
    
    /** Kytket��n passText ja passFied -kent�t toisiins�. Eli molemmilla tulee olemaan sama teksti. */
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
     * Lis�t��n yksitt�inen p��sy puuhun
     * @param p lis�tt�v� p��sy
     * @param kategoriaId lis�tt�v�n p��syn kategorian tunnusnumero
     */
    private void lisaaPaasyPuuhun(Paasy p, int kategoriaId) {
        for (TreeItem<Tietue> item : puu.getRoot().getChildren()) {
            if (item.getValue().getTunnusNro() == kategoriaId) {
                puu.getSelectionModel().select(item);
                puu.addToSelected(p, p.getView());
                valittuKategoria = (Kategoria)item.getValue();
                return;
            }
        }
    }
    
    /**
     * Asettaa kategoriat puurakenteeseen.
     */
    private void paivitaPuu() {
        puu.getRoot().getChildren().clear();
        for (int i = 0; i < passreg.getKategoriatLkm(); i++) {
            Kategoria lisattava = passreg.annaKategoria(i);
            if (lisattava != null) {
                puu.add(lisattava, lisattava.getView());
                List<Paasy> paasyt = passreg.getPaasyt(lisattava.getTunnusNro());
                for (Tietue p : paasyt) {
                    puu.addToSelected(p, p.getView());
                }
            }
        }
    }
}
