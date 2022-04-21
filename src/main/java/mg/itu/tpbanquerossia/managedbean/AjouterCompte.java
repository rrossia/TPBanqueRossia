/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerossia.managedbean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import mg.itu.tpbanquerossia.ejb.GestionnaireCompte;
import mg.itu.tpbanquerossia.entities.CompteBancaire;

/**
 *
 * @author Rossia
 */
@Named(value = "ajouterCompte")
@RequestScoped
public class AjouterCompte {
    private String nom;
    private int solde;
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Creates a new instance of AjouterCompte
     */
    public AjouterCompte() {
    }
    
    @EJB
    GestionnaireCompte compte;
    public String ajouter(){
        CompteBancaire m = new CompteBancaire(nom,solde);
        compte.creerCompte(m);
        FacesMessage message = new FacesMessage( "Création compte effectuée !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
        return "listeComptes";
    }
}
