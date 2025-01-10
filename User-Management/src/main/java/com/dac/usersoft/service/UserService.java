package com.dac.usersoft.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dac.usersoft.model.User;
import com.dac.usersoft.repository.UserRepository;

/**
 * Author : rajgs
 * Date   : 31 Dec 2024
 * Time   : 12:19:35 pm
*/
/*
 * Implements Business Logic Application
 */
@Service
public class UserService {
	
	//DI
	@Autowired
	private UserRepository userRepository;
	
	public void registerUser(User user) {
        userRepository.save(user); //Invokes save() pre-defined method of JPARepository
    }
//Optional is used to handle Null Pointer Exception - In case if user doesn't exist
	public Optional<User> loginUser(String email, String password) {
        return userRepository.findByEmail(email) //calls custom method
                .filter(user -> user.getPassword().equals(password));
    }

	

}
