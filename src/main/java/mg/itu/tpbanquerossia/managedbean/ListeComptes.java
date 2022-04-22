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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import jsf.Util;
import mg.itu.tpbanquerossia.ejb.GestionnaireCompte;
import mg.itu.tpbanquerossia.entities.CompteBancaire;

/**
 *
 * @author Rossia
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> compteBancaireList;

    @EJB
    private GestionnaireCompte compte;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (compteBancaireList == null) {
            compteBancaireList = compte.getAllComptes();
        }
        return compteBancaireList;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        compte.supprimerCompte(compteBancaire);
        //FacesMessage message = new FacesMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        //FacesContext.getCurrentInstance().addMessage(null, message);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
    
    public void modifierCompte(CompteBancaire compteBancaire){
    
    }

}
