package com.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO for User
 *  
 * @author rrobles2
 */
@Repository
public class UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Save the user in the database.
   */
  @Transactional
  public void create(User user) {
    entityManager.persist(user);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  @Transactional
  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return entityManager.createQuery("from User").getResultList();
  }
  
  /**
   * Return the user matching this email address
   */
  public User getByEmail(String email) {
    return (User) entityManager.createQuery("from User where email = :email")
    		.setParameter("email", email).getSingleResult();
  }

  /**
   * Return the user having the passed id
   */
  public User getById(int id) {
    return entityManager.find(User.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  @Transactional
  public void update(User user) {
    entityManager.merge(user);
    return;
  }

}