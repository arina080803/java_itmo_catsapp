package ru.startseva.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.startseva.dtos.OwnerDto;
import ru.startseva.mappers.OwnerMapper;
import ru.startseva.entities.Cat;
import ru.startseva.entities.Owner;
import ru.startseva.repositories.CatRepository;
import ru.startseva.repositories.OwnerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    @Transactional
    public List<OwnerDto> getAllOwners() {
        List<OwnerDto> owners = new ArrayList<>();
        for (Owner owner : ownerRepository.findAll()) {
            owners.add(OwnerMapper.ownerEntityToDto(owner));
        }
        return owners;
    }

    @Override
    @Transactional
    public OwnerDto getOwnerById(int id) {
        Owner owner = ownerRepository.getReferenceById(id);
        return OwnerMapper.ownerEntityToDto(owner);
    }

    @Override
    public void addOwner(OwnerDto ownerDto) {
        ownerRepository.save(OwnerMapper.ownerDtoToEntity(ownerDto));
    }

    @Override
    @Transactional
    public void updateOwner(int id, OwnerDto ownerDto) {
        Owner owner = ownerRepository.getReferenceById(id);
        owner.setOwnerName(ownerDto.getName());
        owner.setOwnerBirthDay(ownerDto.getBirthDay());
        ownerRepository.save(owner);
    }

    @Override
    @Transactional
    public void deleteOwner(int ownerId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        for (Cat cat : owner.getCats()) {
            cat.setOwner(null);
            catRepository.save(cat);
        }
        ownerRepository.delete(owner);
    }

    @Override
    @Transactional
    public void addCatToOwner(int ownerId, int catId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        Cat cat = catRepository.getReferenceById(catId);

        owner.getCats().add(cat);
        cat.setOwner(owner);
        ownerRepository.save(owner);
        catRepository.save(cat);
    }

    @Override
    @Transactional
    public void removeCatFromOwner(int ownerId, int catId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        Cat cat = catRepository.getReferenceById(catId);

        owner.getCats().remove(cat);
        cat.setOwner(null);
        ownerRepository.save(owner);
        catRepository.save(cat);
    }
}
