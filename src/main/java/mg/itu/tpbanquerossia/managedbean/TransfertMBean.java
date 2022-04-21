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
 * L'objet ne va intervenir qu'à chaque demande de transaction:RequestScoped
 */
@Named(value = "transfertMBean")
@RequestScoped
public class TransfertMBean {

    
    private Long idRetrait;
    private Long idDeposer;
    private int montant;
    boolean erreur = false;

    public void setIdRetrait(Long idRetrait) {
        this.idRetrait = idRetrait;
    }

    public void setIdDeposer(Long idDeposer) {
        this.idDeposer = idDeposer;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Long getIdRetrait() {
        return idRetrait;
    }

    public Long getIdDeposer() {
        return idDeposer;
    }

    public int getMontant() {
        return montant;
    }

    @EJB
    private GestionnaireCompte compte;

    /**
     * Creates a new instance of TransfertMBean
     */
    public TransfertMBean() {

    }

    /*public static void message(String messageDetail, String messageResume,
            FacesMessage.Severity severite, String champ) {
        FacesMessage msg
                = new FacesMessage(severite, messageResume, messageDetail);
        FacesContext.getCurrentInstance().addMessage(champ, msg);
    }*/

    public String update() {
        if (compte.recupererCompteById(idRetrait) == null) {
            //Util.messageErreur("Pas de compte trouvé pour cet id", "Pas de compte trouvé pour cet id", "form:source");
            //erreur = true;
            FacesMessage message = new FacesMessage( "Pas de compte trouvé pour cet id!");
            FacesContext.getCurrentInstance().addMessage( null , message );
            return "listeComptes";
        }
        if (compte.recupererCompteById(idDeposer) == null) {
            FacesMessage message = new FacesMessage( "Pas de compte trouvé pour cet id!");
            FacesContext.getCurrentInstance().addMessage( null, message );
            return "listeComptes";
        } 
        if (compte.solde(idRetrait) < montant) {
                FacesMessage message = new FacesMessage( "Reste du compte insuffisant");
                FacesContext.getCurrentInstance().addMessage( "montant", message );
                return "listeComptes";
        }
        else{
        compte.transfertArgent(idRetrait, idDeposer, montant);
        FacesMessage message = new FacesMessage( "Succès de transaction !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
       // Util.addFlashInfoMessage("Transfert correctement effectué");
       return "listeComptes";
        }
        
    }
}
