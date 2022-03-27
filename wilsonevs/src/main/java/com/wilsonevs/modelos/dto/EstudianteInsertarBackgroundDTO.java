package com.wilsonevs.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteInsertarBackgroundDTO {

    private String nombre;
    private String edad;
    private String ciudad;
    private Integer nota_total;
    private String zona_horaria;
    private List<EstudianteRespuestaDTO> respuesta;

}
