package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Produto;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class ProdutoCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public ProdutoCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createProduto(Produto produto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Produto findLinhasEncomendaProduto(int numProduto){
        EntityManager em= getEntityManager();
        Produto prod;
        try{
            prod = em.find(Produto.class, numProduto);
            Hibernate.initialize(prod.getLinhaencomendafornecedorsByNumproduto());
        } finally {
            em.close();
        }
        return prod;
    }

    public static Produto findLinhasFaturasProduto(int numProduto){
        EntityManager em= getEntityManager();
        Produto prod;
        try{
            prod = em.find(Produto.class, numProduto);
            Hibernate.initialize(prod.getLinhafaturasByNumproduto());
        } finally {
            em.close();
        }
        return prod;
    }

    public static Produto findProduto(int numProduto) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, numProduto);
        } finally {
            em.close();
        }
    }



    public static List<Produto> findTodosProdutos() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
            Query q = em.createQuery(cq);
            return ((List<Produto>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteProduto(int numProduto) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produto;
            try {
                produto = em.getReference(Produto.class, numProduto);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("O Produto não existe na base de dados!!");
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    public static void editProduto(Produto prod) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class,prod.getNumproduto());
            produto = em.merge(prod);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = prod.getNumproduto();
                if (findProduto(prod.getNumproduto()) == null) {
                    throw new IdNaoEncontradoException("O produto com id " + id + " nao existe");
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
