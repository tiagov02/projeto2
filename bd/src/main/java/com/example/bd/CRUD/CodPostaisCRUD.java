package com.example.bd.CRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class CodPostaisCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public CodPostaisCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void create(Codpostais cp) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public static Codpostais findCodPostal(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Codpostais.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Codpostais> findCodPostaisAllEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Codpostais.class));
            Query q = em.createQuery(cq);
            return ((List<Codpostais>) q.getResultList());
        } finally {
            em.close();
        }
    }


    public static void deleteCodPostal(String cp) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostais cPostal;
            try {
                cPostal = em.getReference(Codpostais.class, cp);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("O codPostal não existe na base de dados!!");
            }
            em.remove(cPostal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void editCodPostal(Codpostais cod) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostais cp = em.find(Codpostais.class,cod.getCodpostal());
            cp = em.merge(cod);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cod.getCodpostal();
                if (findCodPostal(cod.getCodpostal()) == null) {
                    throw new IdNaoEncontradoException("O codigo postal " + id + " não existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
