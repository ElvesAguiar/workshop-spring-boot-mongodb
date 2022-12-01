package com.elvesaguiar.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.elvesaguiar.workshopmongo.domain.Post;
import com.elvesaguiar.workshopmongo.domain.User;
import com.elvesaguiar.workshopmongo.dto.AuthorDTO;
import com.elvesaguiar.workshopmongo.dto.commentDTO;
import com.elvesaguiar.workshopmongo.repository.PostRepository;
import com.elvesaguiar.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Abraços", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei", new AuthorDTO(maria));

		commentDTO c1 = new commentDTO("Boa viagem mano", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		commentDTO c2 = new commentDTO("AProveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		commentDTO c3 = new commentDTO("Tenha um bom dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);

	}

}
