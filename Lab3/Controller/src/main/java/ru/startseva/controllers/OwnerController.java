package ru.startseva.controllers;

import ru.startseva.dtos.OwnerDto;
import ru.startseva.services.OwnerService;

import java.util.List;

public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners();
    }
    
    public OwnerDto getOwnerById(int id) {
        return ownerService.getOwnerById(id);
    }
    
    public void addOwner(OwnerDto ownerDto) {
        ownerService.addOwner(ownerDto);
    }
    
    public void updateOwner(int id, OwnerDto owner) {
        ownerService.updateOwner(id, owner);
    }
    
    public void deleteOwner(int ownerId) {
        ownerService.deleteOwner(ownerId);
    }
    
    public void addOwnerToCat(int ownerId, int catId) {
        ownerService.addCatToOwner(ownerId, catId);
    }
}
