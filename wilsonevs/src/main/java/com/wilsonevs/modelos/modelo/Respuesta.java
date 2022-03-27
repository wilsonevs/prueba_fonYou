package com.wilsonevs.modelos.modelo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Respuesta {

    private Integer id_respuesta;
    private Integer id_estudiante;
    private Integer id_pregunta;
    private String respuesta;

}
