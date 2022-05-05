package com.example.bd.CRUD;

import java.io.Serializable;
import java.util.List;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Tipocliente;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;

public class TipoClienteCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //public TipoClienteCRUD(){}

    public static EntityManager getEntityManager(){return emf.createEntityManager();}

    public static void createTipoCliente(Tipocliente tipocliente){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public static Tipocliente findTipoCliente(int id){
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipocliente.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Tipocliente> findTiposClientesTodos() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipocliente.class));
            Query q = em.createQuery(cq);
            return ((List<Tipocliente>) q.getResultList());
        } finally {
            em.close();
        }
    }


    public static void deleteTiposClientes(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocliente tipocliente;
            try {
                tipocliente = em.getReference(Tipocliente.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Não existe nenhum cliente com tal ID na base de dados!!");
            }
            em.remove(tipocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public void editTipoClientes(Tipocliente tipos) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipocliente tipocliente = em.find(Tipocliente.class,tipos.getIdtipocliente());
            tipocliente = em.merge(tipos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipos.getIdtipocliente();
                if (findTipoCliente(tipos.getIdtipocliente()) == null) {
                    throw new IdNaoEncontradoException("O tipo de cliente com id " + id + " não existe.");
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
