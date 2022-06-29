package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Linhaencomendafornecedor;
import com.example.bd.Entity.LinhaencomendafornecedorPK;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class LinhaEncomendaFornecedorCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public LinhaEncomendaFornecedorCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createLinhaEncomendaFornecedor(Linhaencomendafornecedor linhaencomendafornecedor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(linhaencomendafornecedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Linhaencomendafornecedor findLinhaEncomendaForncedor(int linhaencomenda) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Linhaencomendafornecedor.class, linhaencomenda);
        } finally {
            em.close();
        }
    }

    public static List<Linhaencomendafornecedor> findAllLinhasEncomendasFornecedores() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Linhaencomendafornecedor.class));
            Query q = em.createQuery(cq);
            return ((List<Linhaencomendafornecedor>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteLinhaEncomendaFornecedor(LinhaencomendafornecedorPK linhaEncomenda) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linhaencomendafornecedor linhaEncomendaFornecedor;
            try {
                linhaEncomendaFornecedor = em.getReference(Linhaencomendafornecedor.class, linhaEncomenda);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("Esta linha de encomenda não existe na base de dados!!");
            }
            em.remove(linhaEncomendaFornecedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void editLinhaEncomendaFornecedor(Linhaencomendafornecedor linhaencomenda) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linhaencomendafornecedor linha = em.find(Linhaencomendafornecedor.class,linhaencomenda.getNumencomenda());
            Linhaencomendafornecedor linha1 = em.find(Linhaencomendafornecedor.class, linhaencomenda.getNumproduto());
            linha = em.merge(linhaencomenda);
            linha1 = em.merge(linhaencomenda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = linhaencomenda.getNumencomenda();
                int id1 = linhaencomenda.getNumproduto();
                if (findLinhaEncomendaForncedor(linhaencomenda.getNumproduto()) == null &&
                        findLinhaEncomendaForncedor(linhaencomenda.getNumencomenda()) == null) {
                    throw new IdNaoEncontradoException("Esta linha de encomenda não existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public static List<Linhaencomendafornecedor> pesquisa(String nome){
        EntityManager em=getEntityManager();
        return em.createNamedQuery("EncomendaFornecedor.SearchProduto",Linhaencomendafornecedor.class).
                setParameter("nome",nome).getResultList();
    }
    public static List<Linhaencomendafornecedor> pesquisaIdProd(int numProd){
        EntityManager em=getEntityManager();
        return em.createNamedQuery("EncomendaFornecedor.SearchIdProd",Linhaencomendafornecedor.class).
                setParameter("num",numProd).getResultList();
    }
}
