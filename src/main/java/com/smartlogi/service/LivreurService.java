package com.smartlogi.service;

import com.smartlogi.entity.Livreur;
import com.smartlogi.repository.LivreurRepository;
import jakarta.transaction.Transactional;


import java.util.List;
import java.util.Optional;

public class LivreurService {

    private final LivreurRepository livreurRepository;

    public LivreurService(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }



    @Transactional
    public Livreur creerLivreur(Livreur livreur) {
        if (livreurRepository.existsByTelephone(livreur.getTelephone())) {
            throw new RuntimeException("Un livreur avec ce telephone existe deja");
        }
        return livreurRepository.save(livreur);
    }

    public List<Livreur> obtenirTousLivreur(){
        return livreurRepository.findAll();
    }
    public Optional<Livreur> obtenirLivreurParId(Long id){
        return livreurRepository.findById(id);
    }

    @Transactional
    public Livreur ModifierLivreur(Long id, Livreur livreurModifier){
        Livreur livreur = livreurRepository.findById(id).orElseThrow(()->new RuntimeException("Livreur not found !"));

        livreur.setNom(livreurModifier.getNom());
        livreur.setPrenom(livreurModifier.getPrenom());
        livreur.setTelephone(livreurModifier.getTelephone());

        return livreurRepository.save(livreur);
    }

    @Transactional
    public void SupprimerLivreur(Long id){
        livreurRepository.deleteById(id);
    }
}
