package com.smartlogi.controller;

import com.smartlogi.entity.Colis;
import com.smartlogi.entity.StatutColis;
import com.smartlogi.service.ColisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colis")
public class ColisController {

    private final ColisService colisService;

    @Autowired
    public ColisController(ColisService colisService) {
        this.colisService = colisService;
    }

    @PostMapping
    public ResponseEntity<Colis> creerColis(
            @RequestBody Colis colis,
            @RequestParam(required = false) Long livreurId) {
        try {
            Colis nouveauColis = colisService.EnregistrerColis(colis, livreurId);
            return new ResponseEntity<>(nouveauColis, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Colis>> obtenirTousLesColis() {
        List<Colis> colis = colisService.ObtenirToutColis();
        return new ResponseEntity<>(colis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colis> obtenirColisParId(@PathVariable("id") Long id) {
        Optional<Colis> colis = colisService.ObtenirColisParId(id);
        return colis.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/livreur/{livreurId}")
    public ResponseEntity<List<Colis>> obtenirColisParLivreur(@PathVariable("livreurId") Long livreurId) {
        List<Colis> colis = colisService.listToutColisParLivreur(livreurId);
        return new ResponseEntity<>(colis, HttpStatus.OK);
    }

    @PutMapping("/{id}/assigner/{livreurId}")
    public ResponseEntity<Colis> assignerColisALivreur(
            @PathVariable("id") Long id,
            @PathVariable("livreurId") Long livreurId) {
        try {
            Colis colis = colisService.AssignColisAuLivreur(id, livreurId);
            return new ResponseEntity<>(colis, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Colis> mettreAJourStatut(
            @PathVariable("id") Long id,
            @RequestParam("statut") StatutColis statut
    ) {
        Colis colis = colisService.MidifierStatus(id, statut);
        return ResponseEntity.ok(colis);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerColis(@PathVariable("id") Long id) {
        try {
            colisService.SupprimerColis(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
