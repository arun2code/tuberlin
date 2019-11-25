package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.dto.UserDTO;
import com.exceptions.UserNotFoundException;
import com.model.User;
import com.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@PersistenceContext
	private EntityManager entityManager;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public Iterable<User> findAllUsers() {
		return repository.findAll();
	}

	@Override
	public Optional<User> findUserById(Long id) throws UserNotFoundException {
		return repository.findById(id);
	}

	@Override
	public User saveUser(UserDTO user) {
		return repository.save(User.of(user.getFirstName(), user.getLastName(), user.getAge(), user.getDepartment()));
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public User updateUser(UserDTO updatedUser, Long id) {
		Optional<User> userOpt = repository.findById(id);

		if (userOpt.isPresent() && userOpt.get() != null) {

			if (updatedUser.getFirstName() != null) {
				userOpt.get().setFirstName(updatedUser.getFirstName());
			}
			if (updatedUser.getLastName() != null) {
				userOpt.get().setLastName(updatedUser.getLastName());
			}
			if (updatedUser.getAge() != null) {
				userOpt.get().setAge(updatedUser.getAge());
			}
			if (updatedUser.getDepartment() != null) {
				userOpt.get().setDepartment(updatedUser.getDepartment());
			}
			return repository.save(userOpt.get());
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	@Override
	public Iterable<User> findUserByDepartment(UserDTO userDTO) {
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteria.createQuery(User.class);
		Root<User> root = query.from(User.class);
		List<Predicate> predicate = new ArrayList<>();

		if (userDTO.getFirstName() != null && !userDTO.getFirstName().isEmpty()) {
			predicate.add(criteria.equal(root.get("firstName"), userDTO.getFirstName()));
		}
		if (userDTO.getLastName() != null && !userDTO.getLastName().isEmpty()) {
			predicate.add(criteria.equal(root.get("lastName"), userDTO.getLastName()));
		}
		if (userDTO.getAge() != null && !userDTO.getAge().isEmpty()) {
			predicate.add(criteria.equal(root.get("age"), userDTO.getAge()));
		}
		if (userDTO.getDepartment() != null && !userDTO.getDepartment().isEmpty()) {
			predicate.add(criteria.equal(root.get("department"), userDTO.getDepartment()));
		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.select(root);
		TypedQuery<User> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}
}
