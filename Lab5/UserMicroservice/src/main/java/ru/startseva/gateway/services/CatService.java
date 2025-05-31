package ru.startseva.gateway.services;

import java.util.List;
import ru.startseva.dtos.CatColor;
import ru.startseva.dtos.CatDto;

public interface CatService {
  List<CatDto> getAllCats(String username);
  CatDto getCatById(String id, String username);
  List<CatDto> getCatsByColor(CatColor color, String username);
  void addCat(CatDto catDto);
  void updateCat(int id, CatDto catDto, String username);
  void deleteCat(int id, String username);
  void addFriend(int catId, int friendId);
  void removeFriend(int catId, int friendId);
  void addOwner(int catId, int ownerId);
}
