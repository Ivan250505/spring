package com.futbol.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asociacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asociacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String pais;

    @Column(name = "fecha_fundacion")
    private LocalDate fechaFundacion;

    // Relación @OneToMany: Una Asociación tiene muchos Clubes
    @OneToMany(mappedBy = "asociacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Club> clubes = new ArrayList<>();
}
