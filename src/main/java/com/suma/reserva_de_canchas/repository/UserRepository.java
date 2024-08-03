package com.suma.reserva_de_canchas.repository;

import com.suma.reserva_de_canchas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsuario(String username);
}
