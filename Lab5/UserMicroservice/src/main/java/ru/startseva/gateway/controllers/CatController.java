package ru.startseva.gateway.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.startseva.dtos.CatColor;
import ru.startseva.dtos.CatDto;
import ru.startseva.gateway.services.CatService;

@RestController
@RequestMapping("api/cats")
public class CatController {
  private final CatService catService;

  @Autowired
  public CatController(CatService catService) {
    this.catService = catService;
  }

  @GetMapping("")
  public List<CatDto> getAllCats(
      @RequestParam(value = "color", required = false) CatColor color, Principal principal) {
    if (color != null) {
      return catService.getCatsByColor(color, principal.getName());
    }
    return catService.getAllCats(principal.getName());
  }

  @GetMapping("/{id}")
  public CatDto getCatById(@PathVariable("id") String id, Principal principal) {
    return catService.getCatById(id, principal.getName());
  }

  @PostMapping("")
  public void addCat(@RequestBody CatDto catDto) {
    catService.addCat(catDto);
  }

  @PutMapping("/{id}")
  public void updateCat(@PathVariable("id") int id, @RequestBody CatDto catDto, Principal principal) {
    catService.updateCat(id, catDto, principal.getName());
  }

  @DeleteMapping("/{id}")
  public void deleteCat(@PathVariable("id") int catId, Principal principal) {
    catService.deleteCat(catId, principal.getName());
  }

  @PostMapping("/{id}/friend")
  public void addFriend(@PathVariable("id") int catId, @RequestParam("friendId") int friendId) {
    catService.addFriend(catId, friendId);
  }

  @DeleteMapping("/{id}/friend")
  public void removeFriend(@PathVariable("id") int catId, @RequestParam("friendId") int friendId) {
    catService.removeFriend(catId, friendId);
  }

  @PostMapping("/{id}/owner")
  public void addOwner(@PathVariable("id") int catId, @RequestParam("ownerId") int ownerId) {
    catService.addOwner(catId, ownerId);
  }
}
