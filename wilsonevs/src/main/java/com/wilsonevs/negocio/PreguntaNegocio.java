package com.wilsonevs.negocio;

import com.wilsonevs.excepciones.DatosInvalidosExcepcion;
import com.wilsonevs.modelos.dto.*;
import com.wilsonevs.modelos.modelo.Pregunta;
import com.wilsonevs.repositorio.PreguntaDao;
import com.wilsonevs.utilidades.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaNegocio {

    @Autowired
    private PreguntaDao preguntaDao;

    public RespuestaPaginada<Pregunta> obtenerListaUsuario(PreguntaFiltroDTO datos){
        RespuestaPaginada<Pregunta> autoRespuestaPaginada = new RespuestaPaginada<>();
        autoRespuestaPaginada.setLista(preguntaDao.obtenerListaUsuario(datos));
        autoRespuestaPaginada.setTotal(preguntaDao.totalListaUsuario(datos));
        return autoRespuestaPaginada;
    }

    public RespuestaPaginada<PreguntaValorNotaDTO> obtenerListaAdministrador(PreguntaFiltroDTO datos){
        RespuestaPaginada<PreguntaValorNotaDTO> autoRespuestaPaginada = new RespuestaPaginada<>();
        autoRespuestaPaginada.setLista(preguntaDao.obtenerListaAdministrador(datos));
        autoRespuestaPaginada.setTotal(preguntaDao.totalListaAdministrador(datos));
        return autoRespuestaPaginada;
    }

    public PreguntaDTO obtenerPorId(Integer id){
        validarId(id);
        PreguntaDTO dto = new PreguntaDTO();
        dto.setPregunta(preguntaDao.obtenerPorId(id));
        return dto;
    }


    public PreguntaInsertDTO insertar (PreguntaInsertDTO dto) {
        validarInsertar(dto);
        dto.setPregunta("¿"+dto.getPregunta()+"?");
        dto.setOpcion1("A. "+dto.getOpcion1());
        dto.setOpcion2("B. "+dto.getOpcion2());
        dto.setOpcion3("C. "+dto.getOpcion3());
        dto.setOpcion4("D. "+dto.getOpcion4());

        preguntaDao.insertar(dto);
        return dto;
    }

    public PreguntaUpdateDTO actualizar (PreguntaUpdateDTO dto){
        validarId(dto.getId_pregunta());
        validarActualizar(dto);
        dto.setPregunta("¿"+dto.getPregunta()+"?");
        dto.setOpcion1("A. "+dto.getOpcion1());
        dto.setOpcion2("B. "+dto.getOpcion2());
        dto.setOpcion3("C. "+dto.getOpcion3());
        dto.setOpcion4("D. "+dto.getOpcion4());

        preguntaDao.actualizar(dto);
        return dto;
    }

    public Pregunta eliminar(Integer id){
        validarId(id);
        preguntaDao.eliminar(id);
        Pregunta accion = new Pregunta();
        return accion;
    }

    public Boolean validarId (Integer id) {
        boolean ban = false;
        List<Pregunta> listaAuto = preguntaDao.obtenerListaPorId();
        for (Pregunta item : listaAuto) {
            if(item.getId_pregunta().equals(id))
                ban = true;
        }

        if(ban == false)
            throw new DatosInvalidosExcepcion(Constantes.VALIDACION_NEGOCIO.ID);

        return ban;
    }


    private boolean validarInsertar(PreguntaInsertDTO dato) {
        Boolean validar0 = dato != null;
        Boolean validar1 = dato.getPregunta() != null;
        if (!validar1) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " PREGUNTA");
        }

        Boolean validar2 = dato.getOpcion1() != null;
        if (!validar2) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 1");
        }

        Boolean validar3 = dato.getOpcion1() != "";
        if (!validar3) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 1");
        }

        Boolean validar4 = dato.getOpcion2() != null;
        if (!validar4) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 2");
        }

        Boolean validar5 = dato.getOpcion2() != "";
        if (!validar5) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 2");
        }

        Boolean validar6 = dato.getOpcion3() != null;
        if (!validar6) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 3");
        }

        Boolean validar7 = dato.getOpcion3() != "";
        if (!validar7) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 3");
        }

        Boolean validar8 = dato.getOpcion4() != null;
        if (!validar8) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 4");
        }

        Boolean validar9 = dato.getOpcion4() != "";
        if (!validar9) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 4");
        }

        Boolean validar10 = dato.getRespuesta() != null;
        if (!validar10) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " RESPUESTA");
        }

        Boolean validar11 = dato.getRespuesta() != "";
        if (!validar11) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " RESPUESTA");
        }

        Boolean validar12 = dato.getValor_nota() != null;
        if (!validar12) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " VALOR DE LA NOTA");
        }

        Boolean validar13 = dato.getValor_nota() != "";
        if (!validar13) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " VALOR DE LA NOTA");
        }



        return  validar0 &&
                validar1 &&
                validar2 &&
                validar3 &&
                validar4 &&
                validar5 &&
                validar6 &&
                validar7 &&
                validar8 &&
                validar9 &&
                validar10 &&
                validar11 &&
                validar12 &&
                validar13
                ;
    }

    private boolean validarActualizar(PreguntaUpdateDTO dato) {

        Boolean validar0 = dato.getId_pregunta() != null;
        if (!validar0) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " ID");
        }

        Boolean validar1 = dato.getPregunta() != null;
        if (!validar1) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " PREGUNTA");
        }

        Boolean validar2 = dato.getOpcion1() != null;
        if (!validar2) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 1");
        }

        Boolean validar3 = dato.getOpcion1() != "";
        if (!validar3) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 1");
        }

        Boolean validar4 = dato.getOpcion2() != null;
        if (!validar4) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 2");
        }

        Boolean validar5 = dato.getOpcion2() != "";
        if (!validar5) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 2");
        }

        Boolean validar6 = dato.getOpcion3() != null;
        if (!validar6) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 3");
        }

        Boolean validar7 = dato.getOpcion3() != "";
        if (!validar7) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 3");
        }

        Boolean validar8 = dato.getOpcion4() != null;
        if (!validar8) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " OPCION 4");
        }

        Boolean validar9 = dato.getOpcion4() != "";
        if (!validar9) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " OPCION 4");
        }

        Boolean validar10 = dato.getRespuesta() != null;
        if (!validar10) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " RESPUESTA");
        }

        Boolean validar11 = dato.getRespuesta() != "";
        if (!validar11) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " RESPUESTA");
        }

        Boolean validar12 = dato.getValor_nota() != null;
        if (!validar12) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " VALOR DE LA NOTA");
        }

        Boolean validar13 = dato.getValor_nota() != "";
        if (!validar13) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " VALOR DE LA NOTA");
        }



        return  validar0 &&
                validar1 &&
                validar2 &&
                validar3 &&
                validar4 &&
                validar5 &&
                validar6 &&
                validar7 &&
                validar8 &&
                validar9 &&
                validar10 &&
                validar11 &&
                validar12 &&
                validar13
                ;
    }

}
