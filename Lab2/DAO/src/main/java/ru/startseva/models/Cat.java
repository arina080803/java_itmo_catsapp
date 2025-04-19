package ru.startseva.models;

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

    private String Name;
    private LocalDate BirthDay;
    private CatColor Color;
    private String Breed;

    @ManyToOne
    @JoinColumn(name = "OwnerID")
    private Owner Owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CatFriends", schema = "public", catalog = "postgres",
            joinColumns = @JoinColumn(name = "CatID"),
            inverseJoinColumns = @JoinColumn(name = "FriendID"))    
    private List<Cat> friends;

    public Cat(String name, LocalDate birthDay, String breed, CatColor color) {
        this.Name = name;
        this.BirthDay = birthDay;
        this.Color = color;
        this.Breed = breed;
        this.Owner = null;
        this.friends = new ArrayList<>();
    }
}
