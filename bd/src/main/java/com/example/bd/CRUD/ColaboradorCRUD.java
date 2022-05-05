package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Colaborador;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class ColaboradorCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //public ColaboradorCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createColaborador(Colaborador colaborador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(colaborador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static Colaborador findColaboradores(int id){
        EntityManager em= getEntityManager();
        try{
            return em.find(Colaborador.class, id);
        } finally {
            em.close();
        }
    }


    public static List<Colaborador> findTodosColaboradores(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Colaborador.class));
            Query q = em.createQuery(cq);
            return ((List<Colaborador>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteColaborador(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colaborador colaborador;
            try {
                colaborador = em.getReference(Colaborador.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("O colaborador não existe na base de dados!!");
            }
            em.remove(colaborador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static void editColaborador(Colaborador colaborador) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colaborador colaborador1 = em.find(Colaborador.class,colaborador.getIdtipo());
            colaborador1 = em.merge(colaborador);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = colaborador.getIdtipo();
                if (findColaboradores(colaborador.getIdtipo()) == null) {
                    throw new IdNaoEncontradoException("O colaborador com id " + id + " não existe.");
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
