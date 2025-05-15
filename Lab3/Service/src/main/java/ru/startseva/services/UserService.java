package ru.startseva.services;

import ru.startseva.dtos.UserDto;

public interface UserService {
  
  void addUser(UserDto userDto);
  void addOwner(int userID, int ownerID);
}
