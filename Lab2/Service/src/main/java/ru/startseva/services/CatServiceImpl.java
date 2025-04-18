package ru.startseva.services;

import ru.startseva.dao.CatDAO;
import ru.startseva.dtos.CatDto;
import ru.startseva.mappers.CatMapper;
import ru.startseva.models.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatServiceImpl implements CatService {
    private final CatDAO catDao;

    public CatServiceImpl(CatDAO catDao) {
        this.catDao = catDao;
    }

    @Override
    public List<CatDto> getAllCats() {
        List<CatDto> cats = new ArrayList<>();
        for (Cat cat : catDao.getAllCats()) {
            cats.add(CatMapper.catEntityToDto(cat));
        }
        return cats;
    }

    @Override
    public CatDto getCatById(int id) {
        Cat cat = catDao.getCatById(id);
        return CatMapper.catEntityToDto(cat);
    }

    @Override
    public void addCat(CatDto catDto) {
        catDao.addCat(CatMapper.catDtoToEntity(catDto));
    }

    @Override
    public void updateCat(int id, CatDto catDto) {
        Cat cat = catDao.getCatById(id);
        cat.setName(catDto.getName());
        cat.setBirthDay(catDto.getBirthDay());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        catDao.updateCat(cat);
    }

    @Override
    public void deleteCat(int catId) {
        Cat cat = catDao.getCatById(catId);
        for (Cat friend : cat.getFriends()) {
            friend.getFriends().remove(cat);
            catDao.updateCat(friend);
        }
        catDao.deleteCat(cat);
    }

    @Override
    public void addFriend(int catId, int friendId) {
        Cat cat = catDao.getCatById(catId);
        Cat friend = catDao.getCatById(friendId);

        cat.getFriends().add(friend);
        friend.getFriends().add(cat);
        catDao.updateCat(cat);
        catDao.updateCat(friend);
    }
}
