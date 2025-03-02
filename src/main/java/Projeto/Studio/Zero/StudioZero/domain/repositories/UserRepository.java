package Projeto.Studio.Zero.StudioZero.domain.repositories;

import Projeto.Studio.Zero.StudioZero.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
