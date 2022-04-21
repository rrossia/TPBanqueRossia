/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquerossia.managedbean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import mg.itu.tpbanquerossia.ejb.GestionnaireCompte;

/**
 *
 * @author Rossia
 */
@Named(value = "transfertMBean")
@ViewScoped
public class TransfertMBean implements Serializable {
    private Long idRetrait;
    private Long idDeposer;
    private int montant;
    
    public void setIdRetrait(Long idRetrait) {
        this.idRetrait = idRetrait;
    }

    public void setIdDeposer(Long idDeposer) {
        this.idDeposer = idDeposer;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public Long getIdRetrait(){
        return idRetrait;
    }  
    public Long getIdDeposer(){
        return idDeposer;
    }
    public int getMontant(){
        return montant;
    }

    
    @EJB
    private GestionnaireCompte compte;
    /**
     * Creates a new instance of TransfertMBean
     */
    public TransfertMBean() {
    
    }

    public String update(){
        compte.transfertArgent(idRetrait,idDeposer,montant);
        return "listeComptes";
    }
    
}
