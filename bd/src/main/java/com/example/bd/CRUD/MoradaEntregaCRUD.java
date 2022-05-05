package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Moradaentrega;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class MoradaEntregaCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //public MoradaEntregaCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public static void createMoradaEntrega(Moradaentrega morada){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(morada);
            em.getTransaction().commit();
        } finally {
            if(em != null){
                em.close();
            }
        }
    }


    public static Moradaentrega findMorada(int id){
        EntityManager em= getEntityManager();
        try{
            return em.find(Moradaentrega.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Moradaentrega> findTodasMoradas(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Moradaentrega.class));
            Query q = em.createQuery(cq);
            return ((List<Moradaentrega>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteMorada(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moradaentrega morada;
            try {
                morada = em.getReference(Moradaentrega.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Esta morada não existe na base de dados!!");
            }
            em.remove(morada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static void editMorada(Moradaentrega morada) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Moradaentrega moradaEntrega = em.find(Moradaentrega.class,morada.getIdentrega());
            moradaEntrega = em.merge(morada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = morada.getIdentrega();
                if (findMorada(morada.getIdentrega()) == null) {
                    throw new IdNaoEncontradoException("A morada com ID " + id + " não existe.");
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
