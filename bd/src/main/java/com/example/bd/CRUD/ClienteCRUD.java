package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Cliente;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class ClienteCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public ClienteCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createCliente(Cliente cliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static Cliente findCliente(int numCliente) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, numCliente);
        } finally {
            em.close();
        }
    }

    public static List<Cliente> findClientesTodos() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            return ((List<Cliente>) q.getResultList());
        } finally {
            em.close();
        }
    }

    public static void deleteCliente(int numCliente) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, numCliente);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("O Cliente não existe na base de dados!!");
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static void editCliente(Cliente cli) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class,cli.getIdcliente());
            cliente = em.merge(cli);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cli.getIdcliente();
                if (findCliente(cli.getIdcliente()) == null) {
                    throw new IdNaoEncontradoException("O cliente com id " + id + " nao existe");
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
