package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Estado;
import com.example.bd.Entity.Estadofatura;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class EstadoFaturaCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public EstadoFaturaCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createEstadoFatura(Estadofatura estadofatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadofatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Estadofatura findEstadoFatura(int estadofatura) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadofatura.class, estadofatura);
        } finally {
            em.close();
        }
    }

    public static List<Estadofatura> findAllEstadosFaturas() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadofatura.class));
            Query q = em.createQuery(cq);
            return ((List<Estadofatura>) q.getResultList());
        } finally {
            em.close();
        }
    }


    public static void deleteEstadosFaturas(int estadosFaturas) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadofatura estadofat;
            try {
                estadofat = em.getReference(Estadofatura.class, estadosFaturas);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Este estado fatura não existe na base de dados!!");
            }
            em.remove(estadofat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static void editEstadoFatura(Estadofatura estado) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadofatura est1 = em.find(Estadofatura.class,estado.getNumfatura());
            Estadofatura est2 = em.find(Estadofatura.class, estado.getIdestado());
            est1 = em.merge(estado);
            est2 = em.merge(estado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estado.getNumfatura();
                int id1 = estado.getIdestado();
                if (findEstadoFatura(estado.getNumfatura()) == null &&
                        findEstadoFatura(estado.getIdestado()) == null) {
                    throw new IdNaoEncontradoException("Este estado de fatura não existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Estado getUltimoEstadoFatura(int idfatura){
        EntityManager em=getEntityManager();
        return em.createNamedQuery("EstadoFatura.FindUltimoEstadoByIdFatura", Estado.class).setParameter("id",idfatura).getSingleResult();
    }
}
