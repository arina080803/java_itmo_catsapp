package ru.startseva.mappers;

import ru.startseva.dtos.CatDto;
import ru.startseva.dtos.OwnerDto;
import ru.startseva.models.Cat;
import ru.startseva.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerMapper {
    public static Owner ownerDtoToEntity(OwnerDto ownerDto) {
        return new Owner(ownerDto.getName(), ownerDto.getBirthDay());
    }

    public static OwnerDto ownerEntityToDto(Owner owner) {
        return new OwnerDto(owner.getOwnerID(), owner.getName(), owner.getBirthDay(), ownerCatsToDto(owner.getCats()));
    }

    public static OwnerDto ownerWithoutCats(Owner owner) {
        return new OwnerDto(owner.getOwnerID(), owner.getName(), owner.getBirthDay());
    }

    public static List<CatDto> ownerCatsToDto(List<Cat> cats) {
        List<CatDto> ownerCats = new ArrayList<>();
        for (Cat cat : cats) {
            ownerCats.add(CatMapper.catWithoutFriends(cat));
        }
        return ownerCats;
    }
}
