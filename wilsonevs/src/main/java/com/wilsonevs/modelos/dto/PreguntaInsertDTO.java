package com.wilsonevs.modelos.dto;

import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaInsertDTO {

    private String pregunta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String respuesta;
    private String valor_nota;

}
