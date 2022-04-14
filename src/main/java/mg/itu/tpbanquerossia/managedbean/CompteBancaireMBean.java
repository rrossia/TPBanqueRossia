/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerossia.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import mg.itu.tpbanquerossia.ejb.GestionnaireCompte;
import mg.itu.tpbanquerossia.entities.CompteBancaire;

/**
 *
 * @author Rossia
 */
@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable {
    private List<CompteBancaire> compteBancaireList;
    
    @EJB
    private GestionnaireCompte compte;
    /**
     * Creates a new instance of CompteBancaireMBean
     */    
    public CompteBancaireMBean() {
    }
    
    public List<CompteBancaire>  getCompteBancaire(){
        if(compteBancaireList==null){
            compteBancaireList = compte.getAllComptes();
        }
        return compteBancaireList;
    }
}
