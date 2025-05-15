package ru.startseva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.startseva.entities.Cat;
import ru.startseva.entities.CatColor;
import ru.startseva.entities.Owner;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    List<Cat> findByColor(CatColor color);
    List<Cat> findByOwner(Owner owner);
    List<Cat> findByColorAndOwner(CatColor color, Owner owner);
}
