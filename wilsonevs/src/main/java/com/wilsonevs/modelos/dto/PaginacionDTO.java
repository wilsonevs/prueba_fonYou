package com.wilsonevs.modelos.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginacionDTO {

    private Integer paginaActual;
    private Integer paginacion;

}
