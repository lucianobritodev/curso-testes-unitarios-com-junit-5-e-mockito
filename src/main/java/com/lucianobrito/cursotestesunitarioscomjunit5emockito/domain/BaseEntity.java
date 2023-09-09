package com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String criadoPor;
    private String modificadoPor;
    private Boolean ativo;
    private LocalDateTime criadoEm;
    private LocalDateTime modificadoEm;

    private void criadoPor() {
        if (criadoPor == null || criadoPor.equals(""))
            criadoPor = "criador";
    }

    private void modificadoPor() {
        if (modificadoPor == null || modificadoPor.equals(""))
            modificadoPor = "modificador";
    }

    private void inativo() {
        if (id == null && ativo == null)
            ativo = true;
    }

    private void criadoEm() {
        if (criadoEm == null)
            criadoEm = LocalDateTime.now();
    }

    private void modificadoEm() {
        if (modificadoEm == null)
            modificadoEm = LocalDateTime.now();
    }

    @PrePersist
    private void prePersistCallback() {
        this.criadoPor();
        this.criadoEm();
        this.modificadoPor();
        this.modificadoEm();
        this.inativo();
    }

    @PreUpdate
    private void preUpdateCallback() {
        this.modificadoPor();
        this.modificadoEm();
    }
}