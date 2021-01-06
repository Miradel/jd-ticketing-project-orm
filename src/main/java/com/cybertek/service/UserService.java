package com.cybertek.service;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String userName);
    void save(UserDTO dto);
    UserDTO update(UserDTO dto);
    void delete(String userName);

    void deleteByUserName(String userName);


}
