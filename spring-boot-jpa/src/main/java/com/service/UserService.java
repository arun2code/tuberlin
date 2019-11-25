package com.service;

import java.util.Optional;

import com.dto.UserDTO;
import com.exceptions.UserNotFoundException;
import com.model.User;

public interface UserService {

	public Iterable<User> findAllUsers();

	public Optional<User> findUserById(Long id) throws UserNotFoundException;

	public User saveUser(UserDTO user);

	public void deleteById(Long id);

	public User updateUser(UserDTO updatedUser, Long id);

	public Iterable<User> findUserByDepartment(UserDTO userDTO);

}
