package ru.startseva.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class OwnerDto {
    private int OwnerID;
    private String Name;
    private LocalDate BirthDay;
    private List<CatDto> cats;

    public OwnerDto(String name, LocalDate birthDay) {
        this.Name = name;
        this.BirthDay = birthDay;
    }

    public OwnerDto(int ownerID, String name, LocalDate birthDay) {
        this.OwnerID = ownerID;
        this.Name = name;
        this.BirthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Owner: id = " + this.OwnerID + ", name = " + this.Name + ", birthday = " + this.BirthDay.toString() + ", cats = " + (this.cats == null ? null : this.cats.toString());
    }
}
