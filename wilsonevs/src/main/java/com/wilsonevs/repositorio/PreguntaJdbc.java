package com.wilsonevs.repositorio;

import com.wilsonevs.modelos.dto.PreguntaFiltroDTO;

public class PreguntaJdbc {

    public String obtenerListaUsuario(PreguntaFiltroDTO filtro) {
        String consulta = " SELECT P.*, ROW_NUMBER() OVER (ORDER BY P.ID_PREGUNTA DESC) RNUM ";
        consulta += " FROM %s P";
        consulta += " WHERE P.ID_PREGUNTA > 0 ";

        if (filtro.getId_pregunta() != null) {
            consulta += " AND P.ID_PREGUNTAS IN ( " + filtro.getId_pregunta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        consulta += " ORDER BY P.ID_PREGUNTA DESC ";
        consulta += " LIMIT " + filtro.getPaginacion();
        consulta += " OFFSET " + ((filtro.getPaginaActual() - 1) * filtro.getPaginacion());
        return consulta;
    }


    public String totalListaUsuario(PreguntaFiltroDTO filtro) {
        String consulta = " SELECT COUNT(*) ";
        consulta += " FROM %s P";
        consulta += " WHERE P.ID_PREGUNTA > 0 ";

        if (filtro.getId_pregunta() != null) {
            consulta += " AND P.ID_PREGUNTAS IN ( " + filtro.getId_pregunta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        return consulta;
    }

    public String obtenerListaAdministrador(PreguntaFiltroDTO filtro) {
        String consulta = " SELECT P.*, ROW_NUMBER() OVER (ORDER BY P.ID_PREGUNTA DESC) RNUM ";
        consulta += " FROM %s P";
        consulta += " WHERE P.ID_PREGUNTA > 0 ";

        if (filtro.getId_pregunta() != null) {
            consulta += " AND P.ID_PREGUNTAS IN ( " + filtro.getId_pregunta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        consulta += " ORDER BY P.ID_PREGUNTA DESC ";
        consulta += " LIMIT " + filtro.getPaginacion();
        consulta += " OFFSET " + ((filtro.getPaginaActual() - 1) * filtro.getPaginacion());
        return consulta;
    }


    public String totalListaAdministrador(PreguntaFiltroDTO filtro) {
        String consulta = " SELECT COUNT(*) ";
        consulta += " FROM %s P";
        consulta += " WHERE P.ID_PREGUNTA > 0 ";

        if (filtro.getId_pregunta() != null) {
            consulta += " AND P.ID_PREGUNTAS IN ( " + filtro.getId_pregunta().toString().replace("[", "").replace("]", "") + " ) ";
        }

        return consulta;
    }


    public String obtenerPorId(Integer id) {
        String consulta = "SELECT ID_PREGUNTA, PREGUNTA, OPCION1, OPCION2, OPCION3, OPCION4, RESPUESTA, VALOR_NOTA ";
        consulta += " FROM %s ";
        consulta += " WHERE ID_PREGUNTA = ?";
        return consulta;
    }

    public String obtenerListaPorId() {
        String consulta = "SELECT ID_PREGUNTA, PREGUNTA, OPCION1, OPCION2, OPCION3, OPCION4, RESPUESTA, VALOR_NOTA ";
        consulta += " FROM %s ";
        return consulta;
    }



    public String insertar() {
        String consulta = "INSERT INTO %s (PREGUNTA, OPCION1, OPCION2, OPCION3, OPCION4, RESPUESTA, VALOR_NOTA) VALUES (?,?,?,?,?,?,?) ";
        return consulta;
    }

    public String actualizar() {
        String consulta = " UPDATE %s SET ";
        consulta += " PREGUNTA = ?,";
        consulta += " OPCION1 = ?,";
        consulta += " OPCION2 = ?, ";
        consulta += " OPCION3 = ?, ";
        consulta += " OPCION4 = ?, ";
        consulta += " RESPUESTA = ?, ";
        consulta += " VALOR_NOTA = ? ";
        consulta += " WHERE ID_PREGUNTA = ? ";
        return consulta;
    }

    public String eliminar() {
        String consulta = " DELETE FROM %s WHERE ID_PREGUNTA = ?";
        return consulta;
    }

}
