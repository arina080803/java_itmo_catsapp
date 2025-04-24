package ru.startseva.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cat", schema = "public", catalog = "postgres")
@Setter
@Getter
@NoArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatID", nullable = false)
    private int CatID;

    private String name;
    private LocalDate birthDay;
    private CatColor color;
    private String breed;

    @ManyToOne
    @JoinColumn(name = "OwnerID")
    private Owner Owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CatFriends", schema = "public", catalog = "postgres",
            joinColumns = @JoinColumn(name = "CatID"),
            inverseJoinColumns = @JoinColumn(name = "FriendID"))    
    private List<Cat> friends;

    public Cat(String name, LocalDate birthDay, String breed, CatColor color) {
        this.name = name;
        this.birthDay = birthDay;
        this.color = color;
        this.breed = breed;
        this.Owner = null;
        this.friends = new ArrayList<>();
    }
}
