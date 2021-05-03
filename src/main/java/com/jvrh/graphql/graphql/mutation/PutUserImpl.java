package com.jvrh.graphql.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jvrh.graphql.graphql.model.ReturnInfoDTO;
import com.jvrh.graphql.graphql.model.UserDTO;
import com.jvrh.graphql.graphql.model.UserInput;
import com.jvrh.graphql.graphql.model.UserReturnDTO;
import com.jvrh.graphql.mongo.model.User;
import com.jvrh.graphql.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PutUserImpl implements GraphQLMutationResolver {
    @Autowired
    UserRepository userRepository;

    public UserReturnDTO putUser(UserInput input) {
        User user = new User();
        ReturnInfoDTO returnInfo;
        if(input.getId() != null) {
            Optional<User> userOptional = userRepository.findById(input.getId());
            if(userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                returnInfo = new ReturnInfoDTO(true, "Id not found");
                return new UserReturnDTO(null, returnInfo);
            }
        }

        user.updateFields(input);
        if(!user.checkFields()) {
            returnInfo = new ReturnInfoDTO(true, "Missing field");
            return new UserReturnDTO(null, returnInfo);
        }

        user = userRepository.save(user);

        UserDTO userDTO = new UserDTO(user);
        returnInfo = new ReturnInfoDTO(false, input.getId() != null ? "User updated successfully" : "User created successfully");
        return new UserReturnDTO(List.of(userDTO), returnInfo);
    }
}
