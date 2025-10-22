
package com.smartlogi.repository;

import com.smartlogi.entity.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LivreurRepository extends JpaRepository<Livreur, Long> {
    Optional<Livreur> findByTelephone(String telephone);
    boolean existsByTelephone(String telephone);
}