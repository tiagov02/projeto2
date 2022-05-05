package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Linhafatura;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class LinhaFaturaCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public LinhaFaturaCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createLinhaFatura(Linhafatura linhafatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(linhafatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Linhafatura findLinhasFaturas(int linhaFatura) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Linhafatura.class, linhaFatura);
        } finally {
            em.close();
        }
    }

    public static List<Linhafatura> findAllLinhasFaturas() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Linhafatura.class));
            Query q = em.createQuery(cq);
            return ((List<Linhafatura>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteLinhaFatura(int linhaFatura) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linhafatura linhafat;
            try {
                linhafat = em.getReference(Linhafatura.class, linhaFatura);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Esta linha fatura não existe na base de dados!!");
            }
            em.remove(linhafat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void editLinhaFatura(Linhafatura linhafatura) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linhafatura linha = em.find(Linhafatura.class,linhafatura.getNumfatura());
            Linhafatura linha1 = em.find(Linhafatura.class, linhafatura.getNumproduto());
            linha = em.merge(linhafatura);
            linha1 = em.merge(linhafatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = linhafatura.getNumfatura();
                int id1 = linhafatura.getNumproduto();
                if (findLinhasFaturas(linhafatura.getNumfatura()) == null &&
                findLinhasFaturas(linhafatura.getNumproduto()) == null) {
                    throw new IdNaoEncontradoException("Esta linha de faturação não existe.");
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
