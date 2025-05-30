package ru.startseva.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.startseva.dtos.CatDto;
import ru.startseva.entities.User;
import ru.startseva.mappers.CatMapper;
import ru.startseva.entities.Cat;
import ru.startseva.entities.CatColor;
import ru.startseva.repositories.CatRepository;
import ru.startseva.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final UserRepository userRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, UserRepository userRepository) {
        this.catRepository = catRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<CatDto> getAllCats() {
        List<CatDto> cats = new ArrayList<>();
        for (Cat cat : catRepository.findAll()) {
            cats.add(CatMapper.catEntityToDto(cat));
        }
        return cats;
    }

    @Override
    @Transactional
    public CatDto getCatById(int id) {
        Cat cat = catRepository.getReferenceById(id);
        return CatMapper.catEntityToDto(cat);
    }

    @Override
    @Transactional
    public void addCat(CatDto catDto) {
        Cat cat = CatMapper.catDtoToEntity(catDto);
        System.out.println(cat.getName());
        catRepository.save(cat);
    }

    @Override
    @Transactional
    public void updateCat(int id, CatDto catDto, String username) {
        Cat cat = catRepository.getReferenceById(id);
        User user = userRepository.findByUsername(username);
        if (user.getOwner() != cat.getOwner()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        cat.setName(catDto.getName());
        cat.setBirthDay(catDto.getBirthDay());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        catRepository.save(cat);
    }

    @Override
    @Transactional
    public void deleteCat(int catId) {
        Cat cat = catRepository.getReferenceById(catId);
        for (Cat friend : cat.getFriends()) {
            friend.getFriends().remove(cat);
            catRepository.save(friend);
        }
        catRepository.save(cat);
    }

    @Override
    @Transactional
    public void addFriend(int catId, int friendId) {
        Cat cat = catRepository.getReferenceById(catId);
        Cat friend = catRepository.getReferenceById(friendId);

        cat.getFriends().add(friend);
        friend.getFriends().add(cat);
        catRepository.save(cat);
        catRepository.save(friend);
    }

    @Override
    @Transactional
    public void deleteFriend(int catId, int friendId) {
        Cat cat = catRepository.getReferenceById(catId);
        Cat friend = catRepository.getReferenceById(friendId);

        cat.getFriends().remove(friend);
        friend.getFriends().remove(cat);
        catRepository.save(cat);
        catRepository.save(friend);
    }

    @Override
    @Transactional
    public List<CatDto> getCatsByColor(CatColor color) {
        List<CatDto> cats = new ArrayList<>();
        for (Cat cat : catRepository.findByColor(color)) {
            cats.add(CatMapper.catEntityToDto(cat));
        }
        return cats;
    }
}
