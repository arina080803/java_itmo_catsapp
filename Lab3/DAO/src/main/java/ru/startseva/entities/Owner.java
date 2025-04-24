package ru.startseva.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Owner", schema = "public", catalog = "postgres")
@Setter
@Getter
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OwnerID;

    private String OwnerName;
    private LocalDate OwnerBirthDay;

    @OneToMany(mappedBy = "Owner", fetch = FetchType.EAGER)
    private List<Cat> cats;

    public Owner(String ownerName, LocalDate ownerBirthDay) {
        this.OwnerName = ownerName;
        this.OwnerBirthDay = ownerBirthDay;
        cats = new ArrayList<>();
    }
}
