package com.wilsonevs.repositorio;

import com.wilsonevs.modelos.dto.RespuestaFiltroDTO;

public class RespuestaJdbc {

    public String obtenerLista(RespuestaFiltroDTO filtro) {
        String consulta = " SELECT R.*, ROW_NUMBER() OVER (ORDER BY R.ID_RESPUESTA DESC) RNUM ";
        consulta += " FROM %s R";
        consulta += " WHERE R.ID_RESPUESTA > 0 ";

        if (filtro.getId_respuesta() != null) {
            consulta += " AND R.ID_RESPUESTA IN ( " + filtro.getId_respuesta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getId_estudiante() != null) {
            consulta += " AND R.ID_ESTUDIANTE IN ( " + filtro.getId_estudiante().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getId_pregunta() != null) {
            consulta += " AND R.ID_PREGUNTA IN ( " + filtro.getId_pregunta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getRespuesta() != null) {
            consulta += " AND UPPER(R.RESPUESTA) LIKE UPPER('%" + filtro.getRespuesta().toUpperCase()  + "%')";
        }

        consulta += " ORDER BY R.ID_RESPUESTA DESC ";
        consulta += " LIMIT " + filtro.getPaginacion();
        consulta += " OFFSET " + ((filtro.getPaginaActual() - 1) * filtro.getPaginacion());
        return consulta;
    }


    public String totalLista(RespuestaFiltroDTO filtro) {
        String consulta = " SELECT COUNT(*) ";
        consulta += " FROM %s R";
        consulta += " WHERE R.ID_RESPUESTA > 0 ";

        if (filtro.getId_respuesta() != null) {
            consulta += " AND R.ID_RESPUESTA IN ( " + filtro.getId_respuesta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getId_estudiante() != null) {
            consulta += " AND R.ID_ESTUDIANTE IN ( " + filtro.getId_estudiante().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getId_pregunta() != null) {
            consulta += " AND R.ID_PREGUNTA IN ( " + filtro.getId_pregunta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getRespuesta() != null) {
            consulta += " AND UPPER(R.RESPUESTA) LIKE UPPER('%" + filtro.getRespuesta().toUpperCase()  + "%')";
        }

        return consulta;
    }


    public String obtenerPorId(Integer id) {
        String consulta = "SELECT ID_RESPUESTA, ID_ESTUDIANTE, ID_PREGUNTA, RESPUESTA ";
        consulta += " FROM %s ";
        consulta += " WHERE ID_RESPUESTA = ?";
        return consulta;
    }

    public String obtenerListaPorId() {
        String consulta = "SELECT ID_RESPUESTA, ID_ESTUDIANTE, ID_PREGUNTA, RESPUESTA ";
        consulta += " FROM %s ";
        return consulta;
    }


    public String obtenerRespuesta(Integer id, String respuesta) {
        String consulta = "SELECT ID_PREGUNTA, PREGUNTA, OPCION1, OPCION2, OPCION3, OPCION4, RESPUESTA, VALOR_NOTA ";
        consulta += " FROM %s ";
        consulta += " WHERE ID_PREGUNTA = "+ id;
        consulta += " AND RESPUESTA = "+ respuesta;
        return consulta;
    }

    public String insertar() {
        String consulta = "INSERT INTO %s (ID_ESTUDIANTE, ID_PREGUNTA, RESPUESTA) VALUES (?,?,?) ";
        return consulta;
    }

    public String actualizar() {
        String consulta = " UPDATE %s SET ";
        consulta += " ID_ESTUDIANTE = ?,";
        consulta += " ID_PREGUNTA = ?, ";
        consulta += " RESPUESTA = ? ";
        consulta += " WHERE ID_RESPUESTA = ? ";
        return consulta;
    }

    public String eliminar() {
        String consulta = " DELETE FROM %s WHERE ID_RESPUESTA = ?";
        return consulta;
    }

}
