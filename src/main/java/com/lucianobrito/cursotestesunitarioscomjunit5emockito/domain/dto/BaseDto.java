package com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDto {

    private Long id;

    @JsonIgnore
    private String criadoPor;

    @JsonIgnore
    private String modificadoPor;
    private Boolean ativo;

    @JsonIgnore
    private LocalDateTime criadoEm;

    @JsonIgnore
    private LocalDateTime modificadoEm;

}