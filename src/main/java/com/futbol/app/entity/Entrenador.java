package com.futbol.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrenador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(length = 100)
    private String nacionalidad;

    @Column(name = "experiencia_anios")
    private Integer experienciaAnios;

    // Relación @OneToOne: Un Entrenador puede estar en un Club
    @OneToOne(mappedBy = "entrenador", fetch = FetchType.LAZY)
    private Club club;
}
