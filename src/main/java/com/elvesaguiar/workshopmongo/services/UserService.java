package com.elvesaguiar.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elvesaguiar.workshopmongo.domain.User;
import com.elvesaguiar.workshopmongo.dto.UserDTO;
import com.elvesaguiar.workshopmongo.repository.UserRepository;
import com.elvesaguiar.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {

		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public void delete(String id) {
		try {
			repository.deleteById(id);
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objecto não encontrado");
			}
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
