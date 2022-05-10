package com.yourautospa.cms;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yourautospa.cms.dao.UserRepository;
import com.yourautospa.cms.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void testFindAll() {
		User User1 = new User("testy", "mctesty");
		User User2 = new User("joe", "shmoe");
		repository.save(User1);
		repository.save(User2);
		
		List<User> UserList = repository.findAll();
		Assertions.assertThat(UserList).isNotNull();
		
	}

	@Test
	public void testFindById() {
		User User1 = new User("testy", "mctesty");
		repository.save(User1);
		Optional<User> foundUser = repository.findById(User1.getId());
		
		Assertions.assertThat(foundUser.get().getId()).isEqualTo(User1.getId());
	}
	
	@Test
	public void testSave() {
		User theUser = new User("testy", "mctesty");
		repository.save(theUser);

		Assertions.assertThat(theUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testDeleteById() {
		User User1 = new User("testy", "mctesty");
		repository.save(User1);
		repository.deleteById(User1.getId());
		Optional<User> foundUser = repository.findById(User1.getId());
		
		Assertions.assertThat(foundUser.isEmpty());
	}
	
}
