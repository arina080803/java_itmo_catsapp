package ru.startseva.services;

import java.util.List;
import ru.startseva.services.dtos.CatColor;
import ru.startseva.services.dtos.AddOwnerDto;
import ru.startseva.services.dtos.CatDto;
import ru.startseva.services.dtos.FriendDto;

public interface CatService {
    
  List<CatDto> getAllCats();
  CatDto getCatById(int id);
  List<CatDto> getCatsByColor(CatColor color);
  List<CatDto> getAllCatsByOwner(int ownerId);
  CatDto getCatByIdAndOwner(int id, int ownerId);
  List<CatDto> getCatsByColorAndOwner(CatColor color, int ownerId);
  void addCat(CatDto catDto);
  void updateCat(CatDto catDto);
  void deleteCat(CatDto catDto);
  void addFriend(FriendDto friendDto);
  void removeFriend(FriendDto friendDto);
  void addOwner(AddOwnerDto addOwnerDto);
}
