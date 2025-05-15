package ru.startseva.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cat")
@Data
@NoArgsConstructor
public class Cat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CatID", nullable = false)
  private int CatID;

  private String name;

  private LocalDate BirthDay;

  private String breed;

  private CatColor color;

  @ManyToOne
  @JoinColumn(name = "OwnerID")
  private Owner owner;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "cat_friends",
      joinColumns = @JoinColumn(name = "CatID"),
      inverseJoinColumns = @JoinColumn(name = "FriendID"))
  private List<Cat> friends;

  public Cat(String name, LocalDate birthDay, String breed, CatColor color) {
    this.name = name;
    this.BirthDay = birthDay;
    this.breed = breed;
    this.color = color;
    this.friends = new ArrayList<>();
    this.owner = null;
  }
}