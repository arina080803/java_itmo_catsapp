package ru.startseva.mappers;

import ru.startseva.dtos.UserDto;
import ru.startseva.entities.User;

public class UserMapper {
  public static User userDtoToEntity(UserDto userDto) {
    return new User(userDto.getUsername(), userDto.getPassword(), userDto.getRoles());
  }
}