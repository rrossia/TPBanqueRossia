/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package mg.itu.tpbanquerossia.ejb;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mg.itu.tpbanquerossia.entities.CompteBancaire;

/**
 *
 * @author Rossia
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "root", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        //TypedQuery<CompteBancaire> query = (TypedQuery<CompteBancaire>) em.createNamedQuery("CompteBancaire.findAll");
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        //Query query =em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }

    public Long nbComptes() {
        TypedQuery<Long> query
                = em.createQuery("select count(c) from CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    public CompteBancaire recupererCompteById(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    public int solde(Long id) {
        TypedQuery<Integer> query
                = em.createQuery("select c.solde from CompteBancaire c where c.id=:id", Integer.class);
        return query.getSingleResult();
    }

    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }

    public void transfertArgent(Long idRetrait, Long idDeposer, int montant) {
        CompteBancaire source = recupererCompteById(idRetrait);
        CompteBancaire depot = recupererCompteById(idDeposer);
        source.retirer(montant);
        depot.deposer(montant);
        update(source);
        update(depot);
    }

    public void deposerArgent(CompteBancaire compte, int montant) {
        compte.deposer(montant);
        update(compte);
    }

    public void retirerArgent(CompteBancaire compte, int montant) {
        compte.retirer(montant);
        update(compte);
    }

    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
}
