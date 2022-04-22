/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerossia.managedbean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import mg.itu.tpbanquerossia.ejb.GestionnaireCompte;
import mg.itu.tpbanquerossia.entities.CompteBancaire;

/**
 *
 * @author Rossia
 */
@Named(value = "depotRetraitMBean")
@ViewScoped
public class DepotRetraitMBean implements Serializable {
    private Long idCompte;
    private int montant;
    private CompteBancaire compteBancaire;

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long id) {
        this.idCompte = id;
    }

    @EJB
    GestionnaireCompte compte;

    public CompteBancaire getCompte() {
        return compteBancaire;
    }

    public void loadCompte() {
        this.compteBancaire = compte.recupererCompteById(idCompte);
    }

    public String deposer() {
        compte.deposerArgent(compteBancaire, montant);
        FacesMessage message = new FacesMessage("Dépot d'argent effectué avec succés !");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "listeComptes";
    }

    public String retirer() {
        if (compteBancaire.getSolde() < montant) {
            FacesMessage message = new FacesMessage("Solde insuffisant pour effectuer ce retrait !");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "listeComptes";
        } else {
            compte.retirerArgent(compteBancaire, montant);
            FacesMessage message = new FacesMessage("Retrait d'argent effectué avec succés !");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "listeComptes";
        }
    }

}
