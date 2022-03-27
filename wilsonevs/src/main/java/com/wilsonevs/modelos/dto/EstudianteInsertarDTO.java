package com.wilsonevs.modelos.dto;

import com.wilsonevs.modelos.modelo.Respuesta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteInsertarDTO {

    private String nombre;
    private String edad;
    private String ciudad;
    private String zona_horaria;
    private List<EstudianteRespuestaDTO> respuesta;

}
