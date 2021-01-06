package com.cybertek.implementation;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDTO> listAllUsers() {
       List<User> list =  userRepository.findAll(Sort.by("firstName"));
       // Cover to DTO and return

        return list.stream().
                map(obj -> { return userMapper.convertToDto(obj);})
                .collect(Collectors.toList());

    }

    @Override
    public UserDTO findByUserName(String userName) {

        User user = userRepository.findByUserName(userName);
        return userMapper.convertToDto(user);
    }

    @Override
    public void save(UserDTO dto) {

      User obj = userMapper.covertToEntity(dto);
      userRepository.save(obj);

    }

    @Override
    public UserDTO update(UserDTO dto) {

        // Find current User
        User user = userRepository.findByUserName(dto.getUserName());
        // Map update user to entity object
        User convertedUser = userMapper.covertToEntity(dto);
        // set id to the converted object
        convertedUser.setId(user.getId());
        // Save the updated user
        userRepository.save(convertedUser);

        return findByUserName(dto.getUserName());
    }

    @Override
    public void delete(String userName) {
        User user = userRepository.findByUserName(userName);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    // Hard Deleted , deleted from both UI and DataBase
    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }
}
