package com.example.nycappmonolith.repositories;

import com.example.nycappmonolith.models.User;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Before
    public void setUp() {
        User firstUser = new User(
                "user1",
                "first name",
                "last name",
                "email",
                "job"
        );

        User secondUser = new User(
                "user2",
                "second name",
                "other last name",
                "second email",
                "second job"
        );

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllUsers() {
        Iterable<User> usersFromDb = userRepository.findAll();

        assertThat(Iterables.size(usersFromDb), is(2));
    }

    @Test
    public void findAll_returnsUserName() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersUserName = Iterables.get(usersFromDb, 1).getUserName();

        assertThat(secondUsersUserName, is("user2"));
    }

    @Test
    public void findAll_returnsFirstName() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersFirstName = Iterables.get(usersFromDb, 1).getFirstName();

        assertThat(secondUsersFirstName, is("second name"));
    }

    @Test
    public void findAll_returnsLastName() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersLastName = Iterables.get(usersFromDb, 1).getLastName();

        assertThat(secondUsersLastName, is(" other last name"));
    }

    @Test
    public void findAll_returnsEmail() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersEmail = Iterables.get(usersFromDb, 1).getEmail();

        assertThat(secondUsersEmail, is("second email"));
    }

    @Test
    public void findAll_returnsJob() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersJob = Iterables.get(usersFromDb, 1).getJob();

        assertThat(secondUsersJob, is("second job"));
    }


}
