package com.jvrh.graphql.graphql.model;

import com.jvrh.graphql.mongo.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    String id;
    String firstName;
    String lastName;
    LocalDate birthday;

    public UserDTO(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        birthday = user.getBirthday();
    }
}
