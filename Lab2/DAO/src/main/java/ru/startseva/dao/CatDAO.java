package ru.startseva.dao;

import ru.startseva.models.Cat;

import java.util.List;

public interface CatDAO {
    List<Cat> getAllCats();
    Cat getCatById(int id);
    void addCat(Cat cat);
    void updateCat(Cat cat);
    void deleteCat(Cat cat);
}

