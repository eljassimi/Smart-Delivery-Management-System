package com.smartlogi.controller;

import com.smartlogi.entity.Livreur;
import com.smartlogi.service.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {

    private final LivreurService livreurService;

    @Autowired
    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @PostMapping
    public ResponseEntity<Livreur> creerLivreur(@RequestBody Livreur livreur) {
        try {
            Livreur nouveauLivreur = livreurService.creerLivreur(livreur);
            return new ResponseEntity<>(nouveauLivreur, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Livreur>> obtenirTousLesLivreurs() {
        List<Livreur> livreurs = livreurService.obtenirTousLivreur();
        return new ResponseEntity<>(livreurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livreur> obtenirLivreurParId(@PathVariable("id") Long id) {
        Optional<Livreur> livreur = livreurService.obtenirLivreurParId(id);
        return livreur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livreur> modifierLivreur(
            @PathVariable("id") Long id,
            @RequestBody Livreur livreur) {
        try {
            Livreur livreurModifie = livreurService.ModifierLivreur(id, livreur);
            return new ResponseEntity<>(livreurModifie, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerLivreur(@PathVariable("id") Long id) {
        try {
            livreurService.SupprimerLivreur(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
