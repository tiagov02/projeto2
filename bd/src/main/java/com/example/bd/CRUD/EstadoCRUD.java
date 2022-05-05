package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Estado;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class EstadoCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //public EstadoCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createEstado(Estado estado){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estado);
            em.getTransaction().commit();
        } finally {
            if(em != null){
                em.close();
            }
        }
    }


    public static Estado findEstados(int id){
        EntityManager em= getEntityManager();
        try{
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }


    public static List<Estado> findTotalEstados(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
            Query q = em.createQuery(cq);
            return ((List<Estado>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static Estado findLinhasEstadosFaturas(int idestado){
        EntityManager em= getEntityManager();
        Estado prod;
        try{
            prod = em.find(Estado.class, idestado);
            Hibernate.initialize(prod.getEstadofaturasByIdestado());
        } finally {
            em.close();
        }
        return prod;
    }


    public static void deleteEstado(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Este estado não existe na base de dados!!");
            }
            em.remove(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    public static void editEstado(Estado estado) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado est = em.find(Estado.class,estado.getIdestado());
            est = em.merge(estado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estado.getIdestado();
                if (findEstados(estado.getIdestado()) == null) {
                    throw new IdNaoEncontradoException("O estado com id " + id + " não existe.");
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
