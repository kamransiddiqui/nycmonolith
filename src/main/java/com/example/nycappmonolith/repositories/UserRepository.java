package com.example.nycappmonolith.repositories;
import com.example.nycappmonolith.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}