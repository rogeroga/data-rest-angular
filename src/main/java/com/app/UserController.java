package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * REST controller 
 * 
 * @author rrobles2
 */
@Controller
@RequestMapping(value="/rest")
public class UserController {

  @Autowired
  private UserDao userDao;
	
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   */
  @RequestMapping(value="/create")
  @ResponseBody
  public String create(String email, String userName) {
    try {
      User user = new User(email, userName);
      userDao.create(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(int id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }

  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/get-by-userid")
  @ResponseBody
  public String getByUserId(int userId) {
    String userName;
    try {
      User user = userDao.getById(userId);
      userName = user.getUserName();
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The userName is: " + userName;
  }
  
  /**
   * Retrieve the id for the user with the passed email address.
   */
  @RequestMapping(value="/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      User user = userDao.getByEmail(email);
      userId = String.valueOf(user.getUserId());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user id is: " + userId;
  }
  
  /**
   * Update the email and the name for the user identified by the passed id.
   */
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(int id, String email, String name) {
    try {
      User user = userDao.getById(id);
      user.setEmail(email);
      user.setUserName(name);
      userDao.update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  } 
  
}
