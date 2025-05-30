package ru.startseva.users.services.mappers;

import ru.startseva.users.dao.entities.User;
import ru.startseva.users.services.dtos.UserDto;

public class UserMapper {
  public static User userDtoToEntity(UserDto userDto) {
    return new User(userDto.getUsername(), userDto.getPassword(), userDto.getRoles());
  }
}