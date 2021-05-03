package com.jvrh.graphql.mongo.model;

import com.jvrh.graphql.graphql.model.UserInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Users")
public class User {
    @Id
    String id;

    String firstName;
    String lastName;
    LocalDate birthday;

    public void updateFields(UserInput input) {
        firstName = input.getFirstName() != null ? input.getFirstName() : firstName;
        lastName = input.getLastName() != null ? input.getLastName() : lastName;
        birthday = input.getBirthday() != null ? input.getBirthday() : birthday;
    }

    public boolean checkFields() {
        return firstName != null && lastName != null && birthday != null;
    }
}
