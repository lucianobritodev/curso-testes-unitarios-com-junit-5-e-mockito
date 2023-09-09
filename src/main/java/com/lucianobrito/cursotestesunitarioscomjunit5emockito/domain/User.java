package com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String name;

    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;
    private String password;


}
