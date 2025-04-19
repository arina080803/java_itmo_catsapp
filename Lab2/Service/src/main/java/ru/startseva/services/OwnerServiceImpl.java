package ru.startseva.services;

import ru.startseva.dao.CatDAO;
import ru.startseva.dao.OwnerDAO;
import ru.startseva.dtos.OwnerDto;
import ru.startseva.mappers.OwnerMapper;
import ru.startseva.models.Cat;
import ru.startseva.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private final OwnerDAO ownerDAO;
    private final CatDAO catDAO;

    public OwnerServiceImpl(OwnerDAO ownerDAO, CatDAO catDAO) {
        this.ownerDAO = ownerDAO;
        this.catDAO = catDAO;
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        List<OwnerDto> owners = new ArrayList<>();
        for (Owner owner : ownerDAO.getAllOwners()) {
            owners.add(OwnerMapper.ownerEntityToDto(owner));
        }
        return owners;
    }

    @Override
    public OwnerDto getOwnerById(int id) {
        Owner owner = ownerDAO.getOwnerById(id);
        return OwnerMapper.ownerEntityToDto(owner);
    }

    @Override
    public void addOwner(OwnerDto ownerDto) {
        ownerDAO.addOwner(OwnerMapper.ownerDtoToEntity(ownerDto));
    }

    @Override
    public void updateOwner(int id, OwnerDto ownerDto) {
        Owner owner = ownerDAO.getOwnerById(id);
        owner.setOwnerName(ownerDto.getName());
        owner.setOwnerBirthDay(ownerDto.getBirthDay());
        ownerDAO.updateOwner(owner);
    }

    @Override
    public void deleteOwner(int ownerId) {
        Owner owner = ownerDAO.getOwnerById(ownerId);
        for (Cat cat : owner.getCats()) {
            cat.setOwner(null);
            catDAO.updateCat(cat);
        }
        ownerDAO.deleteOwner(owner);
    }

    @Override
    public void addOwnerToCat(int ownerId, int catId) {
        Owner owner = ownerDAO.getOwnerById(ownerId);
        Cat cat = catDAO.getCatById(catId);

        owner.getCats().add(cat);
        cat.setOwner(owner);
        ownerDAO.updateOwner(owner);
        catDAO.updateCat(cat);
    }
}
