package Projeto.Studio.Zero.StudioZero.domain.services.impl;

import Projeto.Studio.Zero.StudioZero.domain.entities.User;
import Projeto.Studio.Zero.StudioZero.domain.repositories.UserRepository;
import Projeto.Studio.Zero.StudioZero.domain.services.UserService;
import Projeto.Studio.Zero.StudioZero.applications.dtos.request.UserCreateRequestDTO;
import Projeto.Studio.Zero.StudioZero.applications.dtos.request.UserDeleteRequestDTO;
import Projeto.Studio.Zero.StudioZero.applications.dtos.request.UserReadRequestDTO;
import Projeto.Studio.Zero.StudioZero.applications.dtos.request.UserUpdateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.webjars.NotFoundException;

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
//        if (userOpt.isEmpty()) {
//            throw new NotFoundException("User not found for search");
//        }
        return userOpt;
    }

    @Override
    public User update(UserUpdateRequestDTO userDto) {
        Optional<User> userOpt = userRepository.findById(userDto.getId());
//        if (userOpt.isEmpty()) {
//            throw new NotFoundException("User not found for update");
//        }
        User user = userOpt.get();
        return userRepository.save(
                new User(
                        user.getId(),
                        userDto.getName(),
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );
    }

    @Override
    public Optional<User> delete(UserDeleteRequestDTO userDto) {
        Optional<User> userOpt = userRepository.findById(userDto.getId());
//        if (userOpt.isEmpty()) {
//            throw new NotFoundException("User not found for delete");
//        }
        userRepository.delete(userOpt.get());
        return userOpt;
    }
}
