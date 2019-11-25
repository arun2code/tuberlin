package com.dataLoader;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UserRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final UserRepository repository;

	public DataLoader(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		User user1 = User.of("Shanti", "Ramar", "24", "Marketing");
		repository.save(user1);

		user1 = User.of("Andie", "Flower", "34", "Marketing");

		repository.save(user1);

		user1 = User.of("Manu", "Ronnie", "45", "IT");
		repository.save(user1);
	}

}
