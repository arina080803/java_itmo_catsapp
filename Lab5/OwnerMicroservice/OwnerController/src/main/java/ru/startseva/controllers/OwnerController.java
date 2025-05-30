package ru.startseva.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.startseva.services.OwnerService;
import ru.startseva.services.dtos.OwnerDto;

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
}
