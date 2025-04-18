package ru.startseva.services;

import ru.startseva.dtos.CatDto;

import java.util.List;

public interface CatService {
    List<CatDto> getAllCats();
    CatDto getCatById(int id);
    void addCat(CatDto catDto);
    void updateCat(int id, CatDto catDto);
    void deleteCat(int catId);
    void addFriend(int catId, int friendId);
}
