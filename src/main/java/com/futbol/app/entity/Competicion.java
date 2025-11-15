package com.futbol.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competicion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(length = 100)
    private String pais;

    private Integer anio;

    // Relación @ManyToMany: Muchas Competiciones tienen muchos Clubes
    @ManyToMany(mappedBy = "competiciones", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Club> clubes = new ArrayList<>();
}
