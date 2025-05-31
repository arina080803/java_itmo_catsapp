package ru.startseva.gateway.services;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.startseva.CatClient;
import ru.startseva.dtos.CatColor;
import ru.startseva.dtos.CatDto;
import ru.startseva.gateway.dtos.AddOwnerDto;
import ru.startseva.gateway.dtos.FriendDto;
import ru.startseva.users.dao.entities.Role;
import ru.startseva.users.dao.entities.User;
import ru.startseva.users.dao.repositories.UserRepository;

@Service
public class CatServiceImpl implements CatService {
  private final CatClient catClient;
  private final UserRepository userRepository;
  private final KafkaTemplate<String, CatDto> catKafkaTemplate;
  private final KafkaTemplate<String, FriendDto> friendKafkaTemplate;
  private final KafkaTemplate<String, AddOwnerDto> addOwnerKafkaTemplate;

  @Autowired
  public CatServiceImpl(
      CatClient catClient,
      UserRepository userRepository,
      KafkaTemplate<String, CatDto> catKafkaTemplate,
      KafkaTemplate<String, FriendDto> friendKafkaTemplate,
      KafkaTemplate<String, AddOwnerDto> addOwnerKafkaTemplate) {
    this.catClient = catClient;
    this.userRepository = userRepository;
    this.catKafkaTemplate = catKafkaTemplate;
    this.friendKafkaTemplate = friendKafkaTemplate;
    this.addOwnerKafkaTemplate = addOwnerKafkaTemplate;
  }

  @Override
  @Transactional
  public List<CatDto> getAllCats(String username) {
    User user = userRepository.findByUsername(username);
    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
      return catClient.getAllCats();
    }
    return catClient.getAllCatsByOwner(Integer.toString(user.getOwner()));
  }

  @Override
  @Transactional
  public CatDto getCatById(String id, String username) {
    User user = userRepository.findByUsername(username);
    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
      return catClient.getCatById(id);
    }
    return catClient.getCatByIdAndOwner(id, Integer.toString(user.getOwner()));
  }

  @Override
  @Transactional
  public List<CatDto> getCatsByColor(CatColor color, String username) {
    User user = userRepository.findByUsername(username);
    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
      return catClient.getCatsByColor(color);
    }
    return catClient.getCatsByColorAndOwner(color, Integer.toString(user.getOwner()));
  }

  @Override
  @Transactional
  public void addCat(CatDto catDto) {
    catKafkaTemplate.send("create_cat", catDto);
  }

  @Override
  @Transactional
  public void updateCat(int id, CatDto catDto, String username) {
    CatDto cat = catClient.getCatById(Integer.toString(id));
    User user = userRepository.findByUsername(username);
    if (cat != null && cat.getOwner() != user.getOwner()) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    catDto.setCatID(id);
    catKafkaTemplate.send("update_cat", catDto);
  }

  @Override
  @Transactional
  public void deleteCat(int id, String username) {
    CatDto cat = catClient.getCatById(Integer.toString(id));
    User user = userRepository.findByUsername(username);
    if (cat != null && cat.getOwner() != user.getOwner()) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    cat = new CatDto();
    cat.setCatID(id);
    catKafkaTemplate.send("delete_cat", cat);
  }

  @Override
  @Transactional
  public void addFriend(int catId, int friendId) {
    FriendDto friendDto = new FriendDto(catId, friendId);
    friendKafkaTemplate.send("friend_cat", friendDto);
  }

  @Override
  @Transactional
  public void removeFriend(int catId, int friendId) {
    FriendDto friendDto = new FriendDto(catId, friendId);
    friendKafkaTemplate.send("unfriend_cat", friendDto);
  }

  @Override
  @Transactional
  public void addOwner(int catId, int ownerId) {
    AddOwnerDto addOwnerDto = new AddOwnerDto(catId, ownerId);
    addOwnerKafkaTemplate.send("add_owner", addOwnerDto);
  }
}
