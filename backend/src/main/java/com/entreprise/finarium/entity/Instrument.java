package com.entreprise.finarium.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "instrument")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isin", unique = true, nullable = false, length = 12)
    private String isin;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private InstrumentType type;

    @Column(name = "devise", nullable = false, length = 3)
    private String devise;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 20)
    private InstrumentStatus statut;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_maj", nullable = false)
    private LocalDateTime dateMaj;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.dateMaj = LocalDateTime.now();
        if (this.statut == null) {
            this.statut = InstrumentStatus.BROUILLON;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.dateMaj = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsin() { return isin; }
    public void setIsin(String isin) { this.isin = isin; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public InstrumentType getType() { return type; }
    public void setType(InstrumentType type) { this.type = type; }

    public String getDevise() { return devise; }
    public void setDevise(String devise) { this.devise = devise; }

    public InstrumentStatus getStatut() { return statut; }
    public void setStatut(InstrumentStatus statut) { this.statut = statut; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public LocalDateTime getDateMaj() { return dateMaj; }
    public void setDateMaj(LocalDateTime dateMaj) { this.dateMaj = dateMaj; }
}
