package ru.startseva.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private int ownerId;
    private String name;
    private LocalDate birthDay;
    private List<CatDto> cats;

    public OwnerDto(String name, LocalDate birthDay) {
         this.name = name;
         this.birthDay = birthDay;
     }

     public OwnerDto(int ownerId, String name, LocalDate birthDay) {
         this.ownerId = ownerId;
         this.name = name;
         this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Owner: id = " + this.ownerId + ", name = " + this.name + ", birthday = " + this.birthDay.toString() + ", cats = " + (this.cats == null ? null : this.cats.toString());
    }
}
