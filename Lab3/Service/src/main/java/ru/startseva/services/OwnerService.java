package ru.startseva.services;

import ru.startseva.dtos.OwnerDto;

import java.util.List;

public interface OwnerService {
    List<OwnerDto> getAllOwners();
    OwnerDto getOwnerById(int id);
    void addOwner(OwnerDto ownerDto);
    void updateOwner(int id, OwnerDto ownerDto);
    void deleteOwner(int ownerId);
    void addCatToOwner(int ownerId, int catId);
    void removeCatFromOwner(int ownerId, int catId);
}
