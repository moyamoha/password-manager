package fxPassreg;

import static fi.jyu.mit.fxgui.Functions.getNodes;
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
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import kanta.CustomizedTreeView;
import passreg.Group;
import passreg.Entry;
import passreg.Passreg;
import passreg.ShowableData;

/**
 * @author Yahya
 * @version 19.1.2021
 * Pääikkunan kontrolleri
 */
public class PassregGUIController implements Initializable {

    @FXML private PasswordField passField;
    @FXML private TextField hakuKentta;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private TextField passText;
    @FXML private CheckBox naytaCheckBox;
    @FXML private ListChooser<Entry> paasyChooser;
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
    // omaa koodia tästä eteenpäin
    
    private Passreg passreg;
    private TextInputControl[] edits;
    private static Entry apuPaasy = new Entry();
    private CustomizedTreeView<ShowableData> puu;
    private Entry valittuPaasy;
    private Group valittuKategoria;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alustaPuu();
        alustaPaasyLista();
        hallitseSalasanaKentta();
        for (int i = apuPaasy.ekaKentta(); i <= apuPaasy.kenttaLkm(); i++) {
            cbKentat.add(apuPaasy.getKysymys(i));
        }
        cbKentat.getSelectionModel().select(1);
        cbKentat.setOnAction(e -> etsiPaasyt());
        Node parent = naytaCheckBox.getParent();
        edits = new TextInputControl[apuPaasy.kenttaLkm()];
        Collection<TextInputControl> tekstit =  getNodes(parent, TextInputControl.class, 
                        n -> n.getStyleClass().contains("kentta"), true);
        Collection<Label> labelit =  getNodes(parent, Label.class, 
                n -> n.getStyleClass().contains("label"), true);
        for (TextInputControl kentta : tekstit) {
            if (kentta.getId() != null) {
                String id = kentta.getId();
                edits[Integer.valueOf(id) - 1] = kentta; // koska id:t tulisi alkaa nollasta
            }
        }
        for (Label label : labelit) {
            if (label.getId() != null) {
                try {
                    String id = label.getId();
                    label.setLayoutX(0); label.setAlignment(Pos.CENTER_RIGHT);
                    label.setPrefWidth(118.4);
                    label.setText(apuPaasy.getKysymys(Integer.valueOf(id))); // koska id:t tulisi alkaa nollasta
                } catch (NumberFormatException e) {/*..*/}
            }
        }
    }

    /** Tallennetaan muutoksia tiedostoon, mikäli tallentamattomia muutoksia löytyy*/
    private void tallenna() {
        if (! passreg.onMuutettu()) naytaIlmoitus(1.5, AlertType.INFORMATION, "Ei tallentamattomia muutoksia");
        else {
            passreg.tallenna();
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Tallennettiin");
        }
    }
    
    /**
     * Avataan luettava tiedosto lukemaan ja palautetaan true jos avaaminen onnistuu
     * @param onEkaKerta kertoo missa vaiheessa avaaminen tapahtuu. </br>Pitäisi olla true jos avataan tiedosto ohjelman käynnistyessä
     * @return true jos avaaminen onnistui
     */
    public boolean avaaTiedosto(boolean onEkaKerta) {
        String uusTiedostonNimi = AloitusIkkunaController.kysyTiedosto(null, "passreg");
        if (uusTiedostonNimi == null) return false;
        if (uusTiedostonNimi.equals("")) uusTiedostonNimi = "passreg";
        if (!onEkaKerta) {
            tallenna();  // Tallennetaan ettei menetettäisi nykyisen rekisterin tietoja
        }
        lueTiedosto(uusTiedostonNimi);
        paivitaPuu(null);
        ShowableData t = puu.getSelectedObject();
        valittuPaasy = (t instanceof Entry) ? (Entry) t : null;
        valittuKategoria = (t instanceof Group) ? (Group) t : null;
        naytaPaasy(valittuPaasy);
        return true;
    }
    
    /**
     * Luetaan tiedoston sisältöä
     * @param tiedosto luettava tiedosto
     */
    private void lueTiedosto(String nimi) {
        passreg.lueTiedostosta(nimi);
    }
  
    /** Luodaan uusi pääsy ja lisätään rekisteriin sekä puunäkymään näkyväksi*/
    private void uusiPaasy() {
        Group k = valittuKategoria;
        Entry entry;
        if (k == null) {
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Valitse jokin kategoria");
            return;
        }
        entry = new Entry(k.getTunnusNro());
        entry = PaasyDialogController.kysyPaasy(null, entry);
        if (entry == null) return;
        entry.register();
        passreg.lisaa(entry);
        valittuPaasy = entry;
        paivitaPuu(entry);
        etsiPaasyt();
        naytaPaasy(entry);
    }
    
    /**
     * Avataan pääsyn muokkausikkuna täytettynä valitun pääsyn tiedoilla
     */
    private void muokkaa() {
        if (valittuPaasy == null) {
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Valiste jokin pääsy"); return;
        }
        try {
            Entry p;
            p = valittuPaasy.clone();
            p = PaasyDialogController.kysyPaasy(null, p);
            if (p == null) return;
            if (p.equals(valittuPaasy)) return;
            passreg.korvaaTaiLisaa(p);
            valittuPaasy = p;
            valittuKategoria = null;
            paivitaPuu(p);
            naytaPaasy(p);
            etsiPaasyt();
        } catch (CloneNotSupportedException e) { /*..*/ }
    }

    /**
     * Poistetaan valittu pääsy. Ennen varsinaista poistoa, kysytään josko käyttäjä on varma toiminnasta.
     */
    private void poistaPaasy() {
        Entry poistettava = valittuPaasy;
        if (poistettava == null) {
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Valitse jokin Entry");
            naytaPaasy(null);
            return;
        }
        if (Dialogs.showQuestionDialog("Poisto?", "Haluatko varmasti poistaa " + poistettava.getView() + "?", "kyllä", "ei")) {
            passreg.poistaPaasy(poistettava.getTunnusNro());
            paivitaPuu(null);
            ShowableData t = puu.getSelectedObject();
            if (t instanceof Group) valittuKategoria = (Group) t;
            if (t instanceof Entry) valittuPaasy = (Entry) t;
            if (paasyChooser.getObjects().contains(poistettava)) etsiPaasyt();
            naytaPaasy(null);
        }
        else return;
    }

    /** Avataan kategorian muokkaikkuna tyhjänä */
    private void lisaaUusiKategoria() {
        Group k = new Group();
        k = KategoriaDialogController.kysyKategoria(null, k);
        if (k == null) return;
        k.register();
        passreg.add(k);
        paivitaPuu(k);
        valittuKategoria = k;
    }
    
    /** Avataan kategorian muokkausikkuna täytettynä valitun kategorian nimellä */
    private void muokkaaKategoria() {
        if (valittuKategoria == null) {
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Valitse jokin kategoria"); return;
        }
        try {
            Group k = valittuKategoria.clone();
            k = KategoriaDialogController.kysyKategoria(null, k);
            if (k == null) return;
            passreg.korvaaTaiLisaa(k);
            paivitaPuu(k);
            valittuKategoria = k;
        } catch (CloneNotSupportedException e) {/*..*/}
    }
    
    /** Poistetaan valittu kategoria */
    private void poistaKategoria() {
        Group v = valittuKategoria;
        if (v == null) {
            naytaIlmoitus(1.5, AlertType.INFORMATION, "Valitse jokin kategoria!!"); return;
        }
        String viesti = "Haluatko varmasti poistaa " + v.getView() + "? Tämän poistaminen poistaa kaikki tähän kategoriaan kuuluvat pääsyt!!!"; 
        if (Dialogs.showQuestionDialog("Poisto?", viesti, "kyllä", "ei")) {
            passreg.poistaKategoria(v.getTunnusNro());
            puu.remove(v);
            puu.getSelectionModel().select(null);
            valittuPaasy = null; valittuKategoria = null;
            naytaPaasy(null);
            etsiPaasyt();
        }
    }
    
    /**
     *  Ohjataan käyttäjä tarkempaa dokumentaatiota näyttävään web-sivuun.
     */
    private void haeApua() { avaaLinkki("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/moyamoha#mtypuo4cyMgg"); }
    
    /**
     * Etsitään pääsyt ja lajitellaan annetun hakukentän ja hakuehdon mukaan.
     */
    private void etsiPaasyt() {
        String pattern = hakuKentta.getText();
        if (pattern == null || pattern.isEmpty()) {
            paasyChooser.clear();
            return;
        }
        int paikka = cbKentat.getSelectedIndex();
        if (paikka == 0) {
            naytaIlmoitus(1.5, AlertType.ERROR, "Valitse jokin kenttä, jonka mukaan etsit!"); return;
        }
        Collection<Entry> paasyt =  passreg.etsi(pattern, paikka);
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
    
    /** Avataan tulostusikkuna ja listataan kategoriat pääsyineen valmiiksi tulostettavaksi*/
    private void tulosta() {
        TulostusDialogController tulostusCtrl = TulostusDialogController.tulosta();
        TextArea alue = tulostusCtrl.getTextArea();
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(alue)) {
            os.println("Tulostetaan kaikki pääsyt"); os.println();
            for (int i = 0; i < passreg.getKategoriatLkm(); i++) {
                Group k = passreg.annaKategoria(i);
                Collection<Entry> pst = passreg.getPaasyt(k.getTunnusNro());
                os.print("");
                k.tulosta(os); os.println();
                for (Entry p : pst) {
                    p.tulosta(os); os.println("===============================");
                }
                os.println();
            }
            
        }
    }
    
    //############# 
    // Tästä eteenpäin tulee lähinnä apumetodit
    // TODO: siirrä muut apuMetodit tähän

    /**
     * Asetetaan kontrollerille salasanarekisteri
     * @param passrekisteri asettava rekisteri-olio
     */
    public void setPassreg(Passreg passrekisteri) {
        passreg = passrekisteri;
    }
    
    /**
     * Näytetään ilmoitus ruudulle. 
     * @param aika kauanko ilmoitus jää
     * @param tyyppi minkätyyppinen ilmoitus
     * @param teksti mikä on ilmoituksen sisältö
     */
    public static void naytaIlmoitus(double aika, AlertType tyyppi, String teksti) {
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
    
    /** Näytetään pääsyt paasyChooser:iin */
    private void naytaPaasytListaan(Collection<Entry> pst) {
        paasyChooser.clear();
        for (Entry p : pst) {
            paasyChooser.add(p.getView(), p);
        }
    }
    
    /** Alustetaan paasychooser */
    private void alustaPaasyLista() {
        paasyChooser.setOnMouseClicked(e -> { 
            List<Entry> t = paasyChooser.getObjects();
            if (t.size() != 0 && t.get(0) != null) {
                valittuPaasy = paasyChooser.getSelectedObject();
                ShowableData t1 = puu.getSelectedObject();
                if (t1 == null || (t1 instanceof Group)) { 
                    naytaPaasy(valittuPaasy);
                    return;
                }
                puu.getSelectionModel().select(null);
                naytaPaasy(valittuPaasy);
            }
            else return;
        });
    }
    
    /** Alustaa puunäkymä */
    private void alustaPuu() {
        puu = new CustomizedTreeView<ShowableData>(new Group(), "Database");
        puuVbox.getChildren().add(puu);
        puu.setMinHeight(427);
        puu.setOnMouseClicked(e -> {
            ShowableData t = puu.getSelectedObject();
            if (t instanceof Entry) {
                valittuPaasy = (Entry) t;
                paasyChooser.getSelectionModel().select(null);
                naytaPaasy(valittuPaasy);
                valittuKategoria = null;
            }
            if (t instanceof Group) {
                if (puu.isRoot(puu.getSelectionModel().getSelectedItem())) valittuKategoria = null;
                else {
                    valittuKategoria = (Group) t;
                    paasyChooser.getSelectionModel().select(null);
                }
            }
        });
    }
    
    /** Näytetään pääsy tiedot kukin atribuutti omaan tekstikenttään. 
     * Salasana näytetään mustina palloina, mutta käyttäjä voi paljastaa niitä halutessaan
     */
    private void naytaPaasy(Entry p) {
        if (p == null) {
            for(int i = apuPaasy.ekaKentta(); i <= apuPaasy.kenttaLkm(); i++) {
                edits[i-1].setText("");
            }
            return;
        }
        for(int i = apuPaasy.ekaKentta(); i <= apuPaasy.kenttaLkm(); i++) {
            edits[i-1].setText(p.anna(i));
        }
    }
    
    /**
     * Kysytään käyttäjältä onko hän varma ohjelman sulkemisesta
     * @return true jos tallentamattomia muutoksia löytyi tai 
     */
    public boolean voiSulkea() {
        if (passreg.onMuutettu()) {
            return Dialogs.showQuestionDialog("Suljetaanko?", 
                    "Tallentamattomia muutoksia löytyi! Haluatko silti sulkea ohjelman?", "kyllä", "ei");
        } return true;
    }
    
    /** Kytketään passText ja passFied -kentät toisiinsä. Eli molemmilla tulee olemaan sama teksti. */
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
     * Asettaa kategoriat kuuluvineen pääsyineen puurakenteeseen. 
     * @param t tietue, joka tulee valituksi prosessin päätyttyä.
     */
    private void paivitaPuu(ShowableData t) {
        puu.getRoot().getChildren().clear();
        List<Group> kategoriat = passreg.annaKategoriat();
        int index = 0;
        boolean lasketaan = true;
        for (Group lisattava : kategoriat) {
            if (lisattava != null) {
                puu.add(lisattava, lisattava.getView());
                if (lasketaan) index += 1;
                if (lisattava == t) lasketaan = false;
                List<Entry> paasyt = passreg.getPaasyt(lisattava.getTunnusNro());
                for (ShowableData p : paasyt) {
                    if (lasketaan) index += 1;
                    if (p == t) lasketaan = false;
                    puu.addToSelected(p, p.getView());
                }
            }
        }
        puu.getSelectionModel().select(index);
    }
}
