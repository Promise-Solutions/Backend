package Projeto.Studio.Zero.StudioZero.domain.services;

import Projeto.Studio.Zero.StudioZero.domain.entities.User;
import Projeto.Studio.Zero.StudioZero.web.dtos.request.UserCreateRequestDTO;
import Projeto.Studio.Zero.StudioZero.web.dtos.request.UserDeleteRequestDTO;
import Projeto.Studio.Zero.StudioZero.web.dtos.request.UserReadRequestDTO;
import Projeto.Studio.Zero.StudioZero.web.dtos.request.UserUpdateRequestDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(UserCreateRequestDTO request);
    List<User> searchAll();
    Optional<User> read(UserReadRequestDTO request);
    User update(UserUpdateRequestDTO request);
    Optional<User> delete(UserDeleteRequestDTO request);
}



