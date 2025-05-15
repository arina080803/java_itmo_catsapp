package ru.startseva.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userID;

  @Column(unique = true)
  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private List<Role> roles;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "owner_id", referencedColumnName = "ownerid")
  private Owner owner;

  public User(String username, String password, List<Role> roles) {
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.owner = null;
  }
}