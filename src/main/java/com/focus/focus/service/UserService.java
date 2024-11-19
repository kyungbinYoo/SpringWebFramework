package com.focus.focus.service;

import com.focus.focus.data.ScoringRepository;
import com.focus.focus.data.UserRepository;
import com.focus.focus.data.Utils;
import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.dto.UserDto;
import com.focus.focus.data.entity.Scoring;
import com.focus.focus.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(v -> Utils.toDTO2(v))
                .collect(Collectors.toList());
    }

    public User findById(long id) {

        return userRepository.findById(id).orElse(null);
    }

    public void save(UserDto userDto) {
        User user = Utils.toEntity2(userDto);
        userRepository.save(user);
    }

}
