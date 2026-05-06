package br.com.pferreira.dao.generic;

import br.com.pferreira.util.JPAutil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Pedro Ferreira
 */

public abstract class GenericDAO<T, E extends Serializable>
        implements IGenericDAO<T,E> {

  protected Class<T> persistenteClass;


  public GenericDAO(Class<T> persistenteClass){
    this.persistenteClass = persistenteClass;
  }

  @Override
  public T cadastrar(T entity) throws Exception {
   EntityManager em = JPAutil.getEntityManager();
   try {
     em.getTransaction().begin();
     em.persist(entity);
     em.getTransaction().commit();
     return entity;
   }catch (Exception e ){
     if(em.getTransaction().isActive()){
       em.getTransaction().rollback();
     }
     throw e;
   }finally {
     em.close();
   }
  }

  @Override
  public T alterar(T entity) throws Exception {
    EntityManager em = JPAutil.getEntityManager();
    try {
      em.getTransaction().begin();
      T entityMerged = em.merge(entity);
      em.getTransaction().commit();
      return entityMerged;
    }catch (Exception e ){
      if(em.getTransaction().isActive()){
        em.getTransaction().rollback();
      }
      throw e;
    }finally {
      em.close();
    }
  }

  @Override
  public void excluir(T entity) throws Exception {
    EntityManager em = JPAutil.getEntityManager();
    try {
      em.getTransaction().begin();
      if (em.contains(entity)){
        em.remove(entity);
      }else {
        T managed = em.merge(entity);
        em.remove(managed);
      }
      em.getTransaction().commit();
    }catch (Exception e ){
      if(em.getTransaction().isActive()){
        em.getTransaction().rollback();
      }
      throw e;
    }finally {
      em.close();
    }

  }

  @Override
  public T consultar(E id) throws Exception {
    EntityManager em = JPAutil.getEntityManager();
    try {
      return em.find(persistenteClass, id);
    }finally {
      em.close();
    }
  }

  @Override
  public Collection<T> buscarTodos() throws Exception {
    EntityManager em = JPAutil.getEntityManager();
    try {
      TypedQuery<T> query = em.createQuery(getSelect(), persistenteClass);
      List<T> list = query.getResultList();
      return list;
    }finally {
      em.close();
    }
  }

  private String getSelect(){
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT obj FROM ");
    sb.append(this.persistenteClass.getSimpleName());
    sb.append(" obj");
    return sb.toString();
  }
}
