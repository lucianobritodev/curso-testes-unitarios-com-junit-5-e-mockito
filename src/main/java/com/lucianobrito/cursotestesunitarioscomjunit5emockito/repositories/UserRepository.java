package com.lucianobrito.cursotestesunitarioscomjunit5emockito.repositories;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
