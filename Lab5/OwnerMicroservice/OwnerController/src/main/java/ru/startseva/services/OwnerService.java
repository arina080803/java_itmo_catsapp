package ru.startseva.services;

import java.util.List;
import ru.startseva.services.dtos.OwnerDto;

public interface OwnerService {
    
  List<OwnerDto> getAllOwners();
  OwnerDto getOwnerById(int id);
  void addOwner(OwnerDto ownerDto);
  void updateOwner(OwnerDto ownerDto);
  void deleteOwner(OwnerDto ownerDto);
}
