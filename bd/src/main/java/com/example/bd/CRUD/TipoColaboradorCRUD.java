package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Tipocolaborador;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class TipoColaboradorCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //public TipoColaboradorCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createTipoColaborador(Tipocolaborador tipoColaborador){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoColaborador);
            em.getTransaction().commit();
        } finally {
            if(em != null){
                em.close();
            }
        }
    }

    public static Tipocolaborador findTiposColaboradores(int id){
        EntityManager em= getEntityManager();
        try{
            return em.find(Tipocolaborador.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Tipocolaborador> findTodosTiposColaboradores(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipocolaborador.class));
            Query q = em.createQuery(cq);
            return ((List<Tipocolaborador>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteTipoColaborador(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocolaborador tipoColaborador;
            try {
                tipoColaborador = em.getReference(Tipocolaborador.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("O tipo de colaborador não existe na base de dados!!");
            }
            em.remove(tipoColaborador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void editTipoColaborador(Tipocolaborador tipo) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocolaborador cp = em.find(Tipocolaborador.class,tipo.getIdtipo());
            cp = em.merge(tipo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipo.getIdtipo();
                if (findTiposColaboradores(tipo.getIdtipo()) == null) {
                    throw new IdNaoEncontradoException("O tipo de colaborador com id " + id + " não existe.");
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
