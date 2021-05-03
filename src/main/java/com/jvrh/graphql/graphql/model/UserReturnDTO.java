package com.jvrh.graphql.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReturnDTO {
    List<UserDTO> user;
    ReturnInfoDTO info;
}
