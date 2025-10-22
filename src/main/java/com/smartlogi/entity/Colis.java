package com.smartlogi.entity;


import jakarta.persistence.*;


@Entity
@Table(name="colis")
public class Colis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String destinataire;
    @Column(nullable = false)
    private Double poids;
    @Column(nullable = false)
    private String adresse;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutColis statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;

    public Colis() {}

    public Colis(String destinataire, String adresse, Double poids, StatutColis statut) {
        this.destinataire = destinataire;
        this.adresse = adresse;
        this.poids = poids;
        this.statut = statut;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public StatutColis getStatut() {
        return statut;
    }

    public void setStatut(StatutColis statut) {
        this.statut = statut;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }
}
