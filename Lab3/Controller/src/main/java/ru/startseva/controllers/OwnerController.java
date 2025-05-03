package ru.startseva.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.startseva.dtos.OwnerDto;
import ru.startseva.services.OwnerService;

import java.util.List;

@RestController
@RequestMapping("api/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("")
    public List<OwnerDto> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable("id") int id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping("/add")
    public void addOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.addOwner(ownerDto);
    }

    @PutMapping("/{id}")
    public void updateOwner(@PathVariable("id") int id, @RequestBody OwnerDto owner) {
        ownerService.updateOwner(id, owner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") int ownerId) {
        ownerService.deleteOwner(ownerId);
    }

    @PostMapping("/{id}/cats")
    public void addCatToOwner(@PathVariable("id") int ownerId, @RequestParam("catId") int catId) {
        ownerService.addCatToOwner(ownerId, catId);
    }

    @DeleteMapping("/{id}/cats")
    public void removeCatFromOwner(@PathVariable("id") int ownerId, @RequestParam("catId") int catId) {
        ownerService.removeCatFromOwner(ownerId, catId);
    }
}
