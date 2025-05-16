package ru.startseva.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.startseva.dtos.UserDto;
import ru.startseva.entities.Owner;
import ru.startseva.entities.User;
import ru.startseva.mappers.UserMapper;
import ru.startseva.repositories.OwnerRepository;
import ru.startseva.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final OwnerRepository ownerRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      OwnerRepository ownerRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.ownerRepository = ownerRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void addUser(UserDto userDto) {
    System.out.println(userDto.getRoles());
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    User user = UserMapper.userDtoToEntity(userDto);
    System.out.println(user.getRoles());
    if (userRepository.findByUsername(user.getUsername()) != null) {
      return;
    }
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void addOwner(int userID, int ownerID) {
    User user = userRepository.findByUserID(userID);
    if (user.getOwner() != null) {
      return;
    }
    Owner owner = ownerRepository.getReferenceById(ownerID);
    user.setOwner(owner);
    owner.setUser(user);
    ownerRepository.save(owner);
    userRepository.save(user);
  }
}
