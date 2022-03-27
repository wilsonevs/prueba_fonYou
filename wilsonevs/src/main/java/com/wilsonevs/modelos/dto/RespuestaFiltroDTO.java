package com.wilsonevs.modelos.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaFiltroDTO extends PaginacionDTO {

    private Integer id_respuesta;
    private Integer id_estudiante;
    private Integer id_pregunta;
    private String respuesta;

}
