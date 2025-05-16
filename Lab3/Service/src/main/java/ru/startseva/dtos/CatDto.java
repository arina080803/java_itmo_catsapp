package ru.startseva.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.startseva.entities.CatColor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {
    private int CatID;
    private String Name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate BirthDay;
    private String Breed;
    private CatColor Color;
    private OwnerDto Owner;
    private List<CatDto> friends;

    public CatDto(String name, LocalDate birthDay, String breed, CatColor color) {
        this.Name = name;
        this.BirthDay = birthDay;
        this.Breed = breed;
        this.Color = color;
        this.Owner = null;
    }

    public CatDto(int catID, String name, LocalDate birthDay, String breed, CatColor color, OwnerDto owner) {
        this.CatID = catID;
        this.Name = name;
        this.BirthDay = birthDay;
        this.Breed = breed;
        this.Color = color;
        this.Owner = owner;
    }

    @Override
    public String toString() {
        return "Cat: id = " + this.CatID + ", name = " + this.Name + ", birthday = "
                + this.BirthDay.toString() + ", breed = " + this.Breed + ", color = " + this.Color + ", owner = " + (this.Owner == null ? null : this.Owner.toString()) + ", friends = " + (this.friends == null ? null : this.friends.toString());
    }
}
