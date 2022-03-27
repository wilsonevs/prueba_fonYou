package com.wilsonevs.modelos.modelo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pregunta {

    private Integer id_pregunta;
    private String pregunta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;

}
