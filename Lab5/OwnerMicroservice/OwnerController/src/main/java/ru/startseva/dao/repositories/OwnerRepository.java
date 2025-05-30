package ru.startseva.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.startseva.dao.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {}
