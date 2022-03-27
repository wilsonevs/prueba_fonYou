package com.wilsonevs.modelos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespuestaPaginada<E> {
    private List<E> lista;
    private Number total;
}
