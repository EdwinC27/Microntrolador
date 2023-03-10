package com.API.musica.repositorios;

import com.API.musica.componentes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUser extends JpaRepository<User,String> {
    User findByuserName(String nombreUsuario);

}