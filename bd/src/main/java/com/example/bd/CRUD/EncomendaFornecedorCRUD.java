package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Encomendafornecedor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class EncomendaFornecedorCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public EncomendaFornecedorCRUD(){}

    public static  EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createEncomendaFornecedor(Encomendafornecedor encomenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(encomenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Encomendafornecedor findLinhasEncomenda(int numEncomenda){
        EntityManager em= getEntityManager();
        Encomendafornecedor encomendafornecedor;
        try{
            encomendafornecedor = em.find(Encomendafornecedor.class, numEncomenda);
            Hibernate.initialize(encomendafornecedor.getLinhaencomendafornecedorsByNumencomenda());
        } finally {
            em.close();
        }
        return encomendafornecedor;
    }


    public static Encomendafornecedor findEncomendaFornecedor(int numEncomenda) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encomendafornecedor.class, numEncomenda);
        } finally {
            em.close();
        }
    }


    public static List<Encomendafornecedor> findTodasEncomendasFornecedores() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encomendafornecedor.class));
            Query q = em.createQuery(cq);
            return ((List<Encomendafornecedor>) q.getResultList());
        } finally {
            em.close();
        }
    }


    public static void deleteEncomendaFornecedor(int numEncomenda) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encomendafornecedor encomenda;
            try {
                encomenda = em.getReference(Encomendafornecedor.class, numEncomenda);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("A encomenda não existe na base de dados!!");
            }
            em.remove(encomenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    public static void editEncomendaFornecedor(Encomendafornecedor encomenda) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encomendafornecedor encomendafornecedor = em.find(Encomendafornecedor.class,encomenda.getNumencomenda());
            encomendafornecedor = em.merge(encomenda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = encomenda.getNumencomenda();
                if (findEncomendaFornecedor(encomenda.getNumencomenda()) == null) {
                    throw new IdNaoEncontradoException("A encomenda com id " + id + " nao existe");
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
