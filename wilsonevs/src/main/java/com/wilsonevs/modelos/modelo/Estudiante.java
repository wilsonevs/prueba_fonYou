package com.wilsonevs.modelos.modelo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estudiante {

    private Integer id_estudiante;
    private String nombre;
    private String edad;
    private String ciudad;
    private Integer nota_total;
    private String zona_horaria;

}
