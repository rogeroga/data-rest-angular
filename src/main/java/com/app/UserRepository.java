package com.app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * REST wrapper for the entity, find by methods are implemented by the framework 
 * 
 * @author rrobles2
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Integer> {
	List<User> findByUserId(@Param("userId") Integer userId);
	List<User> findByUserName(@Param("userName") String userName);
	List<User> findByEmail(@Param("email") String email);
	List<User> findByFirstName(@Param("firstName") String firstName);
	List<User> findByDeleted(@Param("deleted") int deleted);
}
