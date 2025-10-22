package com.smartlogi.service;


import com.smartlogi.entity.Colis;
import com.smartlogi.entity.Livreur;
import com.smartlogi.entity.StatutColis;
import com.smartlogi.repository.ColisRepository;
import com.smartlogi.repository.LivreurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class ColisService {

    private  ColisRepository colisRepository;

    private  LivreurRepository livreurRepository;


    public void setColisRepository(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    public void setLivreurRepository(LivreurRepository livreurRepository){
        this.livreurRepository = livreurRepository;
    }

    @Transactional
    public Colis EnregistrerColis(Colis colis, Long livreurId){
        if(livreurId != null){
            Livreur livreur = livreurRepository.findById(livreurId).orElseThrow(()->new RuntimeException("Aucun livreur avec ce id ! "));
            colis.setLivreur(livreur);
        }
        return colisRepository.save(colis);
    }

    @Transactional
    public Colis AssignColisAuLivreur(Long colisId, Long livreurId){

        Livreur livreur = livreurRepository.findById(livreurId).orElseThrow(()->new RuntimeException("Aucun livreur avec ce id ! "));

        Colis colis = colisRepository.findById(colisId).orElseThrow(()->new RuntimeException("Aucune Colis avec ce id ! "));
        colis.setLivreur(livreur);

        return colisRepository.save(colis);
    }

    public Colis MidifierStatus(Long colisId, StatutColis neaveauStatut){
        Colis colis = colisRepository.findById(colisId).orElseThrow(()->new RuntimeException("Aucune Colis avec ce id ! "));
        colis.setStatut(neaveauStatut);
        return colisRepository.save(colis);
    }

    public List<Colis> listToutColisParLivreur(Long livreurId){
        return colisRepository.findByLivreurId(livreurId);
    }

    public List<Colis> ObtenirToutColis(){
        return colisRepository.findAll();
    }

    public Optional<Colis> ObtenirColisParId(Long id){
        return colisRepository.findById(id);
    }

    @Transactional
    public void SupprimerColis(Long id){
        if (!colisRepository.existsById(id)) {
            throw new RuntimeException("Colis introuvable");
        }
        colisRepository.deleteById(id);
    }



}
