package datalayer.dataCentric_Agritur.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum AgriturDao {
	instance;
    private EntityManagerFactory emf;

    private AgriturDao() {
        if (emf!=null) {
            emf.close();
        }
        emf = Persistence.createEntityManagerFactory("datalayer");
    }

    public EntityManager createEntityManager() {
        try {
            return emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnections(EntityManager em) {
        em.close();
    }

    public EntityTransaction getTransaction(EntityManager em) {
        return em.getTransaction();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
