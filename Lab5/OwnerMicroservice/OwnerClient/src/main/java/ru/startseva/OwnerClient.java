package ru.startseva;

import org.springframework.web.bind.annotation.PathVariable;
import ru.startseva.dtos.OwnerDto;

import java.util.List;

public interface OwnerClient {
    List<OwnerDto> getAllOwners();
    OwnerDto getOwnerById(String id);
}
