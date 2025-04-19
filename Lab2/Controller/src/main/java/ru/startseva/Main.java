package ru.startseva;

import ru.startseva.controllers.CatController;
import ru.startseva.controllers.OwnerController;
import ru.startseva.dao.CatDAO;
import ru.startseva.dao.CatDAOImpl;
import ru.startseva.dao.OwnerDAO;
import ru.startseva.dao.OwnerDAOImpl;
import ru.startseva.dtos.CatDto;
import ru.startseva.dtos.OwnerDto;
import ru.startseva.models.CatColor;
import ru.startseva.services.OwnerService;
import ru.startseva.services.OwnerServiceImpl;
import ru.startseva.services.CatService;
import ru.startseva.services.CatServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CatDAO catDAO = new CatDAOImpl();
        OwnerDAO catOwnerDAO = new OwnerDAOImpl();
        CatService catService = new CatServiceImpl(catDAO);
        OwnerService catOwnerService = new OwnerServiceImpl(catOwnerDAO, catDAO);
        CatController catController = new CatController(catService);
        OwnerController ownerController = new OwnerController(catOwnerService);
        OwnerDto owner = new OwnerDto("Arina", LocalDate.now());
        CatDto cat1 = new CatDto("Coffi", LocalDate.now(), "xz", CatColor.Pink);
        CatDto cat2 = new CatDto("Lara", LocalDate.now(), "xz", CatColor.White);
        catController.addCat(cat1);
        catController.addCat(cat2);
        catController.addFriend(1, 2);
        ownerController.addOwner(owner);
        ownerController.addOwnerToCat(1, 1);
        List<CatDto> allCats = catController.getAllCats();
        for (CatDto allCat : allCats) {
            System.out.println(allCat);
        }
        OwnerDto ow = ownerController.getOwnerById(1);
        System.out.println(ow);
        ownerController.deleteOwner(1);
        catController.deleteCat(1);
    }
}