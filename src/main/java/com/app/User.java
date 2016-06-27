package com.app;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * User Entity
 * 
 * @author rrobles2
 */
@Entity
@Table (name="User")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id", nullable = false)
	private Integer userId;
	
    @Column(name = "user_name", nullable = false, unique=true)
    String userName;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email", unique=true)
    String email;
    
	@Column(name = "last_modified_date")
    Date lastModifiedDate = new Date();

    @Column(name = "last_modified_by")
    String lastModifiedBy = "System";
    
    @Column(name = "deleted")
    int deleted = 0;

    public User() { }

    public User(int id) { 
      this.userId = id;
    } 

    public User(String email, String userName) {
      this.email = email;
      this.userName = userName;
    }    
    
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer id) {
		this.userId = id;
	}
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

}
