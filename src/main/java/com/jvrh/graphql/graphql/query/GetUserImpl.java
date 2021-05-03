package com.jvrh.graphql.graphql.query;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jvrh.graphql.graphql.model.ReturnInfoDTO;
import com.jvrh.graphql.graphql.model.UserDTO;
import com.jvrh.graphql.graphql.model.UserReturnDTO;
import com.jvrh.graphql.mongo.model.User;
import com.jvrh.graphql.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GetUserImpl implements GraphQLQueryResolver {
    @Autowired
    UserRepository userRepository;

    public UserReturnDTO getUser(String id) {
        ReturnInfoDTO returnInfo;
        List<User> users;
        if (id == null) {
            users = userRepository.findAll();
        }
        else {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                returnInfo = new ReturnInfoDTO(true, "User not found");
                return new UserReturnDTO(null, returnInfo);
            }
            users = List.of(userOptional.get());
        }
        List<UserDTO> userDTOList = users.stream().map(UserDTO::new).collect(Collectors.toList());
        returnInfo = new ReturnInfoDTO(false, "User found");
        return new UserReturnDTO(userDTOList, returnInfo);
    }
}
