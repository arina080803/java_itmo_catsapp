package ru.startseva;

import ru.startseva.dtos.CatColor;
import ru.startseva.dtos.CatDto;

import java.util.List;

public interface CatClient {
    List<CatDto> getAllCats();
    CatDto getCatById(String id);
    List<CatDto> getCatsByColor(CatColor color);
    List<CatDto> getAllCatsByOwner(String ownerId);
    CatDto getCatByIdAndOwner(String id, String ownerId);
    List<CatDto> getCatsByColorAndOwner(CatColor color, String ownerId);
}
