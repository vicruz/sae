package com.mx.visolutions.sae.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.visolutions.sae.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsuario(String usuario);
}
