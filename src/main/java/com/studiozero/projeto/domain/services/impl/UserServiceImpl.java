package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.entities.User;
import com.studiozero.projeto.domain.exceptions.EntityNotFoundException;
import com.studiozero.projeto.domain.repositories.UserRepository;
import com.studiozero.projeto.domain.services.UserService;
import com.studiozero.projeto.applications.web.dtos.request.UserCreateRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.UserDeleteRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.UserReadRequestDTO;
import com.studiozero.projeto.applications.web.dtos.request.UserUpdateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserCreateRequestDTO userDto) {
        return userRepository.save(
                new User(
                        userDto.getName(),
                        userDto.getCpf(),
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );
    }

    @Override
    public List<User> searchAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> read(UserReadRequestDTO userDto) {
        Optional<User> userOpt = userRepository.findById(userDto.getId());
        if (userOpt.isEmpty()) {
            throw new EntityNotFoundException("User not found for search");
        }
        return userOpt;
    }

    @Override
    public User update(UserUpdateRequestDTO userDto) {
        Optional<User> userOpt = userRepository.findById(userDto.getId());
        if (userOpt.isEmpty()) {
            throw new EntityNotFoundException("User not found for update");
        }
        User user = userOpt.get();
        return userRepository.save(
                new User(
                        user.getId(),
                        userDto.getName(),
                        userDto.getCpf(),
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );
    }

    @Override
    public Optional<User> delete(UserDeleteRequestDTO userDto) {
        Optional<User> userOpt = userRepository.findById(userDto.getId());
        if (userOpt.isEmpty()) {
            throw new EntityNotFoundException("User not found for delete");
        }
        userRepository.delete(userOpt.get());
        return userOpt;
    }
}
