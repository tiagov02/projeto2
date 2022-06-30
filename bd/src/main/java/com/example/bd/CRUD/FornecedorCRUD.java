package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Fornecedor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FornecedorCRUD {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //public FornecedorCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public static void createFornecedor(Fornecedor forn){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(forn);
            em.getTransaction().commit();
        } finally {
            if(em != null){
                em.close();
            }
        }
    }


    public static Fornecedor findFornecedor(int id){
        EntityManager em= getEntityManager();
        try{
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Fornecedor> findFornecedores(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fornecedor.class));
            Query q = em.createQuery(cq);
            return ((List<Fornecedor>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteFornecedor(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor forn;
            try {
                forn = em.getReference(Fornecedor.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Esta morada não existe na base de dados!!");
            }
            em.remove(forn);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static void editFornecedor(Fornecedor forn) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor f = em.find(Fornecedor.class,forn.getIdfornecedor());
            f = em.merge(forn);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = forn.getIdfornecedor();
                if (findFornecedor(forn.getIdfornecedor()) == null) {
                    throw new IdNaoEncontradoException("O fornecedor com id:  " + id + " não existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Fornecedor findByName(String nome){
        EntityManager em=getEntityManager();
        return em.createNamedQuery("Fornecedores.FindByNome",Fornecedor.class).setParameter("nome",nome).getSingleResult();
    }
}
