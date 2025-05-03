package ru.startseva.controllers;

import ru.startseva.dtos.CatDto;
import ru.startseva.entities.CatColor;
import ru.startseva.services.CatService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cats")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("")
    public List<CatDto> getAllCats(@RequestParam(value = "color", required = false) CatColor color) {
        if (color != null) {
            return catService.getCatsByColor(color);
        }
        return catService.getAllCats();
    }

    @GetMapping("/{id}")
    public CatDto getCatById(@PathVariable("id") int id) {
        return catService.getCatById(id);
    }

    @PostMapping("")
    public void addCat(@RequestBody CatDto cat) {
        catService.addCat(cat);
    }

    @PutMapping("/{id}")
    public void updateCat(@PathVariable("id") int id, @RequestBody CatDto cat) {
        catService.updateCat(id, cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable("id") int catId) {
        catService.deleteCat(catId);
    }

    @PostMapping("/{id}/friend")
    public void addFriend(@PathVariable("id") int catId, @RequestParam("friendId") int friendId) {
        catService.addFriend(catId, friendId);
    }

    @DeleteMapping("/{id}/friend")
    public void removeFriend(@PathVariable("id") int catId, @RequestParam("friendId") int friendId) {
        catService.deleteFriend(catId, friendId);
    }
}
