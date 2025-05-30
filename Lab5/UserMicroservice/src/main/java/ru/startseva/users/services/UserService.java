package ru.startseva.users.services;

import ru.startseva.users.services.dtos.UserDto;

public interface UserService {
  
  void addUser(UserDto userDto);
  void addOwner(int userID, int ownerID);
}
