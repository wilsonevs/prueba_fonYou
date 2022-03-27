package com.wilsonevs.modelos.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteFiltroDTO extends PaginacionDTO {

    private Integer id_estudiante;
    private String nombre;
    private String edad;
    private String ciudad;
    private Integer nota_total;

}
