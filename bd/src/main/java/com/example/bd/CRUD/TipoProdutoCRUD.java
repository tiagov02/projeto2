package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Tipoproduto;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TipoProdutoCRUD {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    //public TipoProdutoCRUD(){}
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createTipoProduto(Tipoproduto tipo){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipo);
            em.getTransaction().commit();
        } finally {
            if(em != null){
                em.close();
            }
        }
    }


    public static Tipoproduto findTipoProduto(int id){
        EntityManager em= getEntityManager();
        try{
            return em.find(Tipoproduto.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Tipoproduto> findTiposProduto(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoproduto.class));
            Query q = em.createQuery(cq);
            return ((List<Tipoproduto>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteTipoProduto(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproduto tipo;
            try {
                tipo = em.getReference(Tipoproduto.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Esta morada não existe na base de dados!!");
            }
            em.remove(tipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static void editFornecedor(Tipoproduto tipo) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoproduto tp = em.find(Tipoproduto.class,tipo.getIdtipoproduto());
            tp = em.merge(tipo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tipo.getIdtipoproduto();
                if (findTipoProduto(tipo.getIdtipoproduto()) == null) {
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

    public static Tipoproduto findBySeccao(String seccao){
        EntityManager em=getEntityManager();
        return em.createNamedQuery("TipoProduto.FindBySeccao",Tipoproduto.class)
                .setParameter("seccao",seccao).getSingleResult();
    }
}
