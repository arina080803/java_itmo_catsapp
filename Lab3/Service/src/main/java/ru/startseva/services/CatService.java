package ru.startseva.services;

import ru.startseva.dtos.CatDto;
import ru.startseva.entities.CatColor;

import java.util.List;

public interface CatService {
    List<CatDto> getAllCats();
    CatDto getCatById(int id);
    void addCat(CatDto catDto);
    void updateCat(int id, CatDto catDto, String username);
    void deleteCat(int catId);
    void addFriend(int catId, int friendId);
    List<CatDto> getCatsByColor(CatColor color);
    void deleteFriend(int catId, int friendId);
}
