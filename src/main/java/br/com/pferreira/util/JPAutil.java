package br.com.pferreira.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author Pedro Ferreira
 */

public class JPAutil {

  private static final EntityManagerFactory emf =
          Persistence.createEntityManagerFactory("agendamentoBarbearia");

  public static EntityManager getEntityManager(){
    return emf.createEntityManager();
  }
}
