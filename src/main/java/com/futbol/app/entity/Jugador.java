package com.futbol.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jugador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 50)
    private String posicion;

    @Column(name = "numero_camiseta")
    private Integer numeroCamiseta;

    @Column(length = 100)
    private String nacionalidad;

    private Integer edad;

    // La relación @ManyToOne con Club se maneja desde Club con @JoinColumn
    // Por eso aquí no ponemos @ManyToOne, ya que Club usa @OneToMany con @JoinColumn
}
