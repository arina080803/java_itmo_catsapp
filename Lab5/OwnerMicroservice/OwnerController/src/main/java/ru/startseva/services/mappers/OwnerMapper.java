package ru.startseva.services.mappers;

import ru.startseva.dao.entities.Owner;
import ru.startseva.services.dtos.OwnerDto;

public class OwnerMapper {
  public static Owner ownerDtoToEntity(OwnerDto ownerDto) {
    return new Owner(ownerDto.getName(), ownerDto.getBirthDay());
  }

  public static OwnerDto ownerEntityToDto(Owner owner) {
    return new OwnerDto(owner.getOwnerID(), owner.getName(), owner.getBirthDay());
  }
}
