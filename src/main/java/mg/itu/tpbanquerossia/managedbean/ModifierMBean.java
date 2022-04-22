/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerossia.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import jsf.Util;
import mg.itu.tpbanquerossia.ejb.GestionnaireCompte;
import mg.itu.tpbanquerossia.entities.CompteBancaire;

/**
 *
 * @author Rossia
 */
@Named(value = "modifierMBean")
@ViewScoped
public class ModifierMBean implements Serializable {
    private Long id;
    private String nom;
    private int solde;
    private CompteBancaire compteBancaire;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    /**
     * Creates a new instance of ModifierMBean
     */
    public ModifierMBean() {
    }
    
    @EJB
    GestionnaireCompte compte;

    public CompteBancaire getCompte() {
        return compteBancaire;
    }
    
    public void loadCompte() {
        compteBancaire = compte.recupererCompteById(id);
    }
    
    public String modifierCompte(){
        compte.update(compteBancaire);
        Util.addFlashInfoMessage("Modification compte effectué avec succés!");
        return "listeComptes";
    }
    
}
