package com.example.bd.CRUD;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Fatura;
import com.example.bd.Entity.Linhafatura;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FaturaCRUD implements Serializable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    //public FaturaCRUD(){}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createFatura(Fatura fatura){
        fatura.setValorfatura(new BigDecimal(0));
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fatura);
            em.getTransaction().commit();
        } finally {
            if(em != null){
                em.close();
            }
        }
    }

    public static Fatura findLinhasFatura(int numFatura){
        EntityManager em= getEntityManager();
        Fatura fat;
        try{
            fat = em.find(Fatura.class, numFatura);
            Hibernate.initialize(fat.getLinhafaturasByNumfatura());
        } finally {
            em.close();
        }
        return fat;
    }

    public static Fatura findEstadosFatura(int numFatura){
        EntityManager em= getEntityManager();
        Fatura fat;
        try{
            fat = em.find(Fatura.class, numFatura);
            Hibernate.initialize(fat.getEstadofaturasByNumfatura());
        } finally {
            em.close();
        }
        return fat;
    }

    public static Fatura findColaboradoresResponsaveisFatura(int numFatura){
        EntityManager em= getEntityManager();
        Fatura fat;
        try{
            fat = em.find(Fatura.class, numFatura);
            Hibernate.initialize(fat.getColaboradorByIdcolaborador());
        } finally {
            em.close();
        }
        return fat;
    }

    public static Fatura findFatura(int numFatura) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fatura.class, numFatura);
        } finally {
            em.close();
        }
    }

    public static List<Fatura> findTodasFaturas(){
        EntityManager em = getEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fatura.class));
            Query q = em.createQuery(cq);
            return ((List<Fatura>) q.getResultList());
        } finally {
            em.close();
        }
    }


    public static void deleteFatura(int id) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fatura fatura;
            try {
                fatura = em.getReference(Fatura.class, id);
            } catch (EntityNotFoundException enfe) {
                throw new IdNaoEncontradoException("A referida fatura não existe na base de dados!!");
            }
            em.remove(fatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static void editFatura(Fatura fatura) throws IdNaoEncontradoException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fatura ft = em.find(Fatura.class,fatura.getNumfatura());
            ft = em.merge(fatura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = fatura.getNumfatura();
                if (findFatura(fatura.getNumfatura()) == null) {
                    throw new IdNaoEncontradoException("A fatura com id " + id + " não existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Lógica
     */

    public void addLinhaFatura(Linhafatura lf,Fatura f){
        lf.setNumfatura(f.getNumfatura());
        LinhaFaturaCRUD.createLinhaFatura(lf);
    }
}
