package ru.startseva.controllers;

import ru.startseva.dtos.CatDto;
import ru.startseva.services.CatService;

import java.util.List;

public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    public List<CatDto> getAllCats() {
        return catService.getAllCats();
    }

    public CatDto getCatById(int id) {
        return catService.getCatById(id);
    }

    public void addCat(CatDto cat) {
        catService.addCat(cat);
    }

    public void updateCat(int id, CatDto cat) {
        catService.updateCat(id, cat);
    }

    public void deleteCat(int catId) {
        catService.deleteCat(catId);
    }

    public void addFriend(int catId, int friendId) {
        catService.addFriend(catId, friendId);
    }
}
