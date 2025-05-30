package ru.startseva.gateway.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendDto {
  private int CatId;
  private int FriendId;
}
