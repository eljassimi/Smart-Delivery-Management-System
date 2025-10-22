package com.smartlogi.repository;

import com.smartlogi.entity.Colis;
import com.smartlogi.entity.StatutColis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColisRepository extends JpaRepository<Colis, Long> {
    List<Colis> findByLivreurId(Long livreurId);
    List<Colis> findColisByStatut(StatutColis statut);
}