package com.wilsonevs.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaInsertDTO {

    private Integer id_estudiante;
    private Integer id_pregunta;
    private String respuesta;


}
