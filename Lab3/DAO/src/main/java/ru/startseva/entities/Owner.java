package ru.startseva.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Owner", schema = "public", catalog = "postgres")
@Data
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OwnerID;

    private String OwnerName;
    private LocalDate OwnerBirthDay;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Cat> cats;

    @OneToOne(mappedBy = "owner")
    private User user;

    public Owner(String ownerName, LocalDate ownerBirthDay) {
        this.OwnerName = ownerName;
        this.OwnerBirthDay = ownerBirthDay;
        cats = new ArrayList<>();
        this.user = null;
    }
}
