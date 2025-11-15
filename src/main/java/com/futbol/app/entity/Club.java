package com.futbol.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "club")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(name = "fecha_fundacion")
    private LocalDate fechaFundacion;

    @Column(length = 100)
    private String estadio;

    // Relación @OneToOne: Un Club tiene un Entrenador
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;

    // Relación @ManyToOne: Muchos Clubes pertenecen a una Asociación
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asociacion_id", nullable = false)
    private Asociacion asociacion;

    // Relación @OneToMany: Un Club tiene muchos Jugadores
    // Se usa @JoinColumn para evitar tabla intermedia
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_club")
    @Builder.Default
    private List<Jugador> jugadores = new ArrayList<>();

    // Relación @ManyToMany: Un Club participa en muchas Competiciones
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "clubes_competiciones",
        joinColumns = @JoinColumn(name = "club_id"),
        inverseJoinColumns = @JoinColumn(name = "competicion_id")
    )
    @Builder.Default
    private List<Competicion> competiciones = new ArrayList<>();
}
