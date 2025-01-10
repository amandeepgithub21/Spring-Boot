package com.dac.usersoft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.usersoft.model.User;

/**
 * Author : rajgs
 * Date   : 31 Dec 2024
 * Time   : 12:16:01 pm
*/

//Long - Data Type of primary key field in entity class User
public interface UserRepository extends JpaRepository<User, Long> {
	
	/*By Default implements JPARepository Methods- save(),findAll(), findById(),
	 * deleteById()
	 */
	
	//Custom Repository Methods - login using non-id field
	 Optional<User> findByEmail(String email);
}
