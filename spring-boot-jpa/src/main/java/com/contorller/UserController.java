package com.contorller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDTO;
import com.model.User;
import com.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("RestAPI")
@Api(value = "UserDataSet", description = "Perform CRUD operation user data using Rest API")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping(value = "/users")
	@ApiOperation(value = "View the list of Users", response = Iterable.class)
	public Iterable<User> getAllUsers() {
		return service.findAllUsers();
	}

	@GetMapping(value = "/users/{id}")
	@ApiOperation(value = "fetch user by its Id", response = User.class)
	public Optional<User> getUserById(@PathVariable Long id) {
		return service.findUserById(id);
	}

	@GetMapping(value = "/users/filter")
	@ApiOperation(value = "Filter user by first Name, last Name, age, and department", response = User.class)
	public Iterable<User> getUserByDepartment(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "age", required = false) String age,
			@RequestParam(value = "department", required = false) String department) {
		return service.findUserByDepartment(UserDTO.of(firstName, lastName, age, department));
	}

	@PostMapping(value = "/users/save")
	@ApiOperation(value = "Create new User", response = User.class)
	public User addStudent(@RequestBody UserDTO user) {
		return service.saveUser(user);
	}

	@PatchMapping(value = "/users/edit/{id}")
	@ApiOperation(value = "Update existing User", response = User.class)
	public User updateStudent(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
		return service.updateUser(updatedUser, id);
	}

	@DeleteMapping(value = "/users/delete/{id}")
	@ApiOperation(value = "Delete existing User", response = User.class)
	public String deleteStudent(@PathVariable Long id) {
		service.deleteById(id);
		return id.toString();
	}
}
