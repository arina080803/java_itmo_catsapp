package ru.startseva.dao;

import ru.startseva.models.Owner;

import java.util.List;

public interface OwnerDAO {
    List<Owner> getAllOwners();
    Owner getOwnerById(int id);
    void addOwner(Owner owner);
    void updateOwner(Owner owner);
    void deleteOwner(Owner owner);
}
