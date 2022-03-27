package com.wilsonevs.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaFiltroDTO extends PaginacionDTO {

    private Integer id_pregunta;
    private String pregunta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;

}
