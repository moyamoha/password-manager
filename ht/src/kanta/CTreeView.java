/**
 * 
 */
package kanta;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
/**
 * @author Yahya
 * @version 4.3.2021
 *
 * @param <T>
 */
/**
 * Muokattu TreeView-luokka, johon on helppo liitt‰‰ olioita.
 * @author Yahya
 * @version 3.3.2021
 * @param <T> K‰ytetty tyyppi
 *
 */
public class CTreeView<T> extends TreeView<T> {
    
    /**
     * Oletus muodostaja
     */
    public CTreeView() {
        initialize();
    }
    
    /**
     * Alustetaan puu juurioliolla
     * @param rootOlio puun juuri
     * @param view juuri merkkijonona
     */
    public CTreeView (T rootOlio, String view) {
        this.setRoot(rootOlio, view);
        initialize();
    }
    
    private final void initialize() {
        this.setCellFactory(new Callback<TreeView<T>, TreeCell<T>>() {
            @Override
            public TreeCell<T> call(TreeView<T> arg0) {
                return new CTreeCell<T>();
            }
        });
    }
    
    /**
     * @param t olio, jota liitet‰‰n puun juureen
     * @param view juuren merkkijono esitys
     */
    public void setRoot(T t, String view) {
        CTreeItem<T> juuri = new CTreeItem<T>(t, view);
        juuri.setView(view);
        this.setRoot(juuri);
    }
    
    /**
     * Lis‰t‰‰n puuhun olio
     * @param t lisattava olio
     * @param view olion merkkijonoesitys
     */
    public void add(T t, String view) {
        CTreeItem<T> lisattava = new CTreeItem<>(t, view);
        this.getRoot().getChildren().add(lisattava);
    }
    
    /**
     * Palautetaan paikassa <i>indeksi</i> oleva olio
     * @param indeksi indeksi josta haetaan 
     * @return olio paikassa <i>indeksi</i>
     */
    public T getObjectAt(int indeksi) {
        if (indeksi == 0) return this.getRoot().getValue();
        return this.getRoot().getChildren().get(indeksi).getValue();
    }
    
    /**
     * Poistetaan puuelementti annetusta indeksista
     * @param indeksi josta poistetaan
     */
    public void removeAt(int indeksi) {
        if (this.getRoot().getChildren().isEmpty()) {
            this.setRoot(null);
        }
        else {
            this.getRoot().getChildren().remove(indeksi);
            this.refresh();
        }
    }
    
    /**
     * @param t poistettava olio
     */
    public void remove(T t) {
        ObservableList<TreeItem<T>> tlista = this.getRoot().getChildren();
        for (TreeItem<T> tItem : tlista) {
            if (tItem.getValue().equals(t)) {
                this.getRoot().getChildren().remove(tItem);
                return;
            }
        }
    }
    
    /**
     * Palautetaan valittu olio
     * @return viite valittuun olioon
     */
    public T getSelectedObject() {
        TreeItem<T> item = this.getSelectionModel().getSelectedItem();
        if (item == null) return null;
        return item.getValue();
    }
    
    /**
     * @param object olio jonka merkkijonoesitys haetaan
     * @return objektin merkkijono esityks
     */
    public String getObjectView (T object) {
        if (this.getRoot().getValue() == object) return this.getRoot().toString();
        for (TreeItem<T> item : this.getRoot().getChildren()) {
            if (item.getValue() != null) {
                if (item.getValue() == object) return item.toString();
            }
        }
        return null;
    }
    
    
    /**
     * @author Yahya
     * @version 1.3.2021
     * @param <E> K‰ytetty tyyppi
     *
     */
    public class CTreeItem<E> extends TreeItem<E> {
        
        private String view = "";
        
        /**
         * @param e olio, jota t‰m‰ puuelementti esitt‰‰
         * @param show puuelementin merkkijonoesitys
         */
        public CTreeItem (E e, String show) {
            setValue(e);
            setView(show);
        }
        
        /**
         * Asetetaan puuelementille, jokin merkkijonoesitys
         * @param view t‰m‰n elementin merkkijonoesitys
         */
        public void setView(String view) {
            this.view = view;
        }
        
        @Override
        public String toString() {
            if ( view.equals("") ) return this.getValue().toString();
            return this.view;
        }
        
    }
    
    /**
     * @author Yahya
     * @version 1.3.2021
     * @param <TYPE> K‰ytetty tyyppi
     */
    public class CTreeCell<TYPE> extends TreeCell<TYPE> {
        
        /**
         * Oleetus muodostaja
         */
        public CTreeCell() { }
        
        @Override
        protected void updateItem(TYPE item, boolean empty) {
            super.updateItem(item, empty);
            TreeItem<TYPE> x = getTreeItem(); 
            if (x != null) {
                setText(x.toString());
            }
            else this.setText("");
        }
        
    }
}
