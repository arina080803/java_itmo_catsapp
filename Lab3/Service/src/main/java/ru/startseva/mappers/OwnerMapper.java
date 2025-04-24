package ru.startseva.mappers;

import ru.startseva.dtos.CatDto;
import ru.startseva.dtos.OwnerDto;
import ru.startseva.entities.Cat;
import ru.startseva.entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerMapper {
    public static Owner ownerDtoToEntity(OwnerDto ownerDto) {
        return new Owner(ownerDto.getName(), ownerDto.getBirthDay());
    }

    public static OwnerDto ownerEntityToDto(Owner owner) {
        return new OwnerDto(owner.getOwnerID(), owner.getOwnerName(), owner.getOwnerBirthDay(), ownerCatsToDto(owner.getCats()));
    }

    public static OwnerDto ownerWithoutCats(Owner owner) {
        return new OwnerDto(owner.getOwnerID(), owner.getOwnerName(), owner.getOwnerBirthDay());
    }

    public static List<CatDto> ownerCatsToDto(List<Cat> cats) {
        List<CatDto> ownerCats = new ArrayList<>();
        for (Cat cat : cats) {
            ownerCats.add(CatMapper.catWithoutFriends(cat));
        }
        return ownerCats;
    }
}
