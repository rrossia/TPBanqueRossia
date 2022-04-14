/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package mg.itu.tpbanquerossia.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import mg.itu.tpbanquerossia.entities.CompteBancaire;

/**
 *
 * @author Rossia
 */
@Singleton
@Startup
public class Init {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @EJB
    private GestionnaireCompte compte;
    
    @PostConstruct
    public void initialise(){
    /*  if(compte.getAllComptes().size()!=0){
          return;
      }*/
       compte.creerCompte(new CompteBancaire("John Lennon",150000));
       compte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
       compte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
       compte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }
}
