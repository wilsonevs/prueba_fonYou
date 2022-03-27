package com.wilsonevs.negocio;

import com.wilsonevs.excepciones.DatosInvalidosExcepcion;
import com.wilsonevs.modelos.dto.*;
import com.wilsonevs.modelos.modelo.Estudiante;
import com.wilsonevs.repositorio.EstudianteDao;
import com.wilsonevs.repositorio.PreguntaDao;
import com.wilsonevs.repositorio.RespuestaDao;
import com.wilsonevs.utilidades.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EstudianteNegocio {

    @Autowired
    private EstudianteDao estudianteDao;

    @Autowired
    private PreguntaNegocio preguntaNegocio;

    @Autowired
    private PreguntaDao preguntaDao;

    @Autowired
    private RespuestaDao respuestaDao;



    public RespuestaPaginada<Estudiante> obtenerLista(EstudianteFiltroDTO datos){
        RespuestaPaginada<Estudiante> autoRespuestaPaginada = new RespuestaPaginada<>();
        autoRespuestaPaginada.setLista(estudianteDao.obtenerLista(datos));
        autoRespuestaPaginada.setTotal(estudianteDao.totalLista(datos));
        return autoRespuestaPaginada;
    }

    public EstudianteDTO obtenerPorId(Integer id){
        validarId(id);
        EstudianteDTO dto = new EstudianteDTO();
        dto.setUsuario(estudianteDao.obtenerPorId(id));
        return dto;
    }


    public EstudianteInsertarDTO insertar (EstudianteInsertarDTO dto)  {
        validarInsertar(dto);
        EstudianteInsertarBackgroundDTO datos = new EstudianteInsertarBackgroundDTO();

        datos.setNota_total(totalNota(dto));
        datos.setNombre(dto.getNombre());
        datos.setEdad(dto.getEdad());
        datos.setCiudad(dto.getCiudad());
        datos.setRespuesta(dto.getRespuesta());

        estudianteDao.insertar(datos);
        return dto;
    }

    public Estudiante actualizar (Estudiante dto){
        validarId(dto.getId_estudiante());
        validarActualizar(dto);
        estudianteDao.actualizar(dto);
        return dto;
    }

    public Estudiante eliminar(Integer id){
        validarId(id);
        estudianteDao.eliminar(id);
        Estudiante accion = new Estudiante();
        return accion;
    }

    public Boolean validarId (Integer id) {
        boolean ban = false;
        List<Estudiante> listaAuto = estudianteDao.obtenerListaPorId();
        for (Estudiante item : listaAuto) {
            if(item.getId_estudiante().equals(id)) {
                ban = true;
            }
        }
        if(ban == false) {
            throw new DatosInvalidosExcepcion(Constantes.VALIDACION_NEGOCIO.ID);
        }
        return ban;
    }


    public Integer totalNota (EstudianteInsertarDTO dto) {
        Integer contador= 0;
        Integer aux= 0;
        List<EstudianteRespuestaDTO> lista = dto.getRespuesta();
        for (Iterator<EstudianteRespuestaDTO> listaSubtiposIncidencia = lista.iterator(); listaSubtiposIncidencia.hasNext(); ) {
            EstudianteRespuestaDTO e = (EstudianteRespuestaDTO) listaSubtiposIncidencia.next();
            preguntaNegocio.validarId(e.getId_pregunta());

            PreguntaValorNotaDTO pregunta = preguntaDao.obtenerValorNota(e.getId_pregunta());
            if(pregunta.getId_pregunta().equals(e.getId_pregunta()) && pregunta.getRespuesta().equals(e.getRespuesta().toUpperCase())) {
                contador= Integer.parseInt(pregunta.getValor_nota());
            }
            aux += contador;
        }

        return aux;
    }


    private boolean validarInsertar(EstudianteInsertarDTO dato) {


        Boolean validar2 = dato.getNombre() != null;
        if (!validar2) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " NOMBRE");
        }

        Boolean validar3 = dato.getNombre() != "";
        if (!validar3) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " NOMBRE");
        }

        Boolean validar4 = dato.getEdad() != null;
        if (!validar4) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " EDAD");
        }

        Boolean validar5 = dato.getEdad() != "";
        if (!validar5) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " EDAD");
        }

        Boolean validar6 = dato.getCiudad() != null;
        if (!validar6) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " CIUDAD");
        }

        Boolean validar7 = dato.getCiudad() != "";
        if (!validar7) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " CIUDAD");
        }

        Boolean validar8 = dato.getZona_horaria() != null;
        if (!validar8) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " ZONA HORARIA");
        }

        Boolean validar9 = dato.getZona_horaria() != "";
        if (!validar9) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " ZONA HORARIA");
        }

        return  validar2 &&
                validar3 &&
                validar4 &&
                validar5 &&
                validar6 &&
                validar7 &&
                validar8 &&
                validar9
                ;
    }

    private boolean validarActualizar(Estudiante dato) {


        Boolean validar1 = dato.getId_estudiante() != null;
        if (!validar1) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " ID");
        }

        Boolean validar2 = dato.getNombre() != null;
        if (!validar2) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " NOMBRE");
        }

        Boolean validar3 = dato.getNombre() != "";
        if (!validar3) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " NOMBRE");
        }

        Boolean validar4 = dato.getEdad() != null;
        if (!validar4) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " EDAD");
        }

        Boolean validar5 = dato.getEdad() != "";
        if (!validar5) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " EDAD");
        }

        Boolean validar6 = dato.getCiudad() != null;
        if (!validar6) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " CIUDAD");
        }

        Boolean validar7 = dato.getCiudad() != "";
        if (!validar7) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " CIUDAD");
        }

        Boolean validar8 = dato.getNota_total() != null;
        if (!validar8) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " NOTA TOTAL");
        }

        Boolean validar9 = dato.getZona_horaria() != null;
        if (!validar9) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.VACIO + " ZONA HORARIA");
        }

        Boolean validar10 = dato.getZona_horaria() != "";
        if (!validar10) {
            throw new DatosInvalidosExcepcion(Constantes.Validacion_Negocio.INCOMPLETOS + " ZONA HORARIA");
        }

        return  validar1 &&
                validar2 &&
                validar3 &&
                validar4 &&
                validar5 &&
                validar6 &&
                validar7 &&
                validar8 &&
                validar9 &&
                validar10
                ;
    }

}
