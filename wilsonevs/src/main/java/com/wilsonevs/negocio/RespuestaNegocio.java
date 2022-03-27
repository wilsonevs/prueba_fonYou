package com.wilsonevs.negocio;

import com.wilsonevs.excepciones.DatosInvalidosExcepcion;
import com.wilsonevs.modelos.dto.*;
import com.wilsonevs.modelos.modelo.Respuesta;
import com.wilsonevs.repositorio.RespuestaDao;
import com.wilsonevs.utilidades.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaNegocio {

    @Autowired
    private RespuestaDao respuestaDao;

    public RespuestaPaginada<Respuesta> obtenerLista(RespuestaFiltroDTO datos){
        RespuestaPaginada<Respuesta> autoRespuestaPaginada = new RespuestaPaginada<>();
        autoRespuestaPaginada.setLista(respuestaDao.obtenerLista(datos));
        autoRespuestaPaginada.setTotal(respuestaDao.totalLista(datos));
        return autoRespuestaPaginada;
    }

    public RespuestaDTO obtenerPorId(Integer id){
        validarId(id);
        RespuestaDTO dto = new RespuestaDTO();
        dto.setRespuesta(respuestaDao.obtenerPorId(id));
        return dto;
    }


    public RespuestaInsertDTO insertar (RespuestaInsertDTO dto) {
        respuestaDao.insertar(dto);
        return dto;
    }

    public Respuesta actualizar (Respuesta dto){
        validarId(dto.getId_pregunta());
        respuestaDao.actualizar(dto);
        return dto;
    }

    public Respuesta eliminar(Integer id){
        validarId(id);
        respuestaDao.eliminar(id);
        Respuesta accion = new Respuesta();
        return accion;
    }

    public Boolean validarId (Integer id) {
        boolean ban = false;
        List<Respuesta> listaAuto = respuestaDao.obtenerListaPorId();
        for (Respuesta item : listaAuto) {
            if(item.getId_pregunta().equals(id))
                ban = true;
        }

        if(ban == false)
            throw new DatosInvalidosExcepcion(Constantes.VALIDACION_NEGOCIO.ID);

        return ban;
    }

}
