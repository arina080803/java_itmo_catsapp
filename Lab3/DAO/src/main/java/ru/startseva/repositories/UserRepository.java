package ru.startseva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.startseva.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);
  User findByUserID(int UserID);
}
