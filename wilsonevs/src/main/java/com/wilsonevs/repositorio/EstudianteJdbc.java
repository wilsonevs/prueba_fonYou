package com.wilsonevs.repositorio;

import com.wilsonevs.modelos.dto.EstudianteFiltroDTO;

public class EstudianteJdbc {

    public String obtenerLista(EstudianteFiltroDTO filtro) {
        String consulta = " SELECT E.*, ROW_NUMBER() OVER (ORDER BY E.ID_ESTUDIANTE DESC) RNUM ";
        consulta += " FROM %s E";
        consulta += " WHERE ID_ESTUDIANTE > 0 ";

        if (filtro.getId_estudiante() != null) {
            consulta += " AND E.ID_ESTUDIANTE IN ( " + filtro.getId_estudiante().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getNombre() != null) {
            consulta += " AND UPPER(E.NOMBRE) LIKE UPPER('%" + filtro.getNombre().toUpperCase()  + "%')";
        }

        if (filtro.getEdad() != null) {
            consulta += " AND UPPER(E.EDAD) LIKE UPPER('%" + filtro.getEdad().toUpperCase()  + "%')";
        }

        if (filtro.getCiudad() != null) {
            consulta += " AND UPPER(E.CIUDAD) LIKE UPPER('%" + filtro.getCiudad().toUpperCase()  + "%')";
        }

        if (filtro.getNota_total() != null) {
            consulta += " AND E.NOTA_TOTAL IN ( " + filtro.getId_estudiante().toString().replace("[", "").replace("]", "") + " ) ";
        }

        consulta += " ORDER BY E.ID_ESTUDIANTE DESC ";
        consulta += " LIMIT " + filtro.getPaginacion();
        consulta += " OFFSET " + ((filtro.getPaginaActual() - 1) * filtro.getPaginacion());
        return consulta;
    }


    public String totalLista(EstudianteFiltroDTO filtro) {
        String consulta = " SELECT COUNT(*) ";
        consulta += " FROM %s E ";
        consulta += " WHERE ID_ESTUDIANTE > 0 ";

        if (filtro.getId_estudiante() != null) {
            consulta += " AND E.ID_ESTUDIANTE IN ( " + filtro.getId_estudiante().toString().replace("[", "").replace("]", "") + " ) ";
        }

        if (filtro.getNombre() != null) {
            consulta += " AND UPPER(E.NOMBRE) LIKE UPPER('%" + filtro.getNombre().toUpperCase()  + "%')";
        }

        if (filtro.getEdad() != null) {
            consulta += " AND UPPER(E.EDAD) LIKE UPPER('%" + filtro.getEdad().toUpperCase()  + "%')";
        }

        if (filtro.getCiudad() != null) {
            consulta += " AND UPPER(E.CIUDAD) LIKE UPPER('%" + filtro.getCiudad().toUpperCase()  + "%')";
        }

        if (filtro.getNota_total() != null) {
            consulta += " AND E.NOTA_TOTAL IN ( " + filtro.getId_estudiante().toString().replace("[", "").replace("]", "") + " ) ";
        }

        return consulta;
    }


    public String obtenerPorId(Integer id) {
        String consulta = "SELECT ID_ESTUDIANTE, NOMBRE, EDAD, CIUDAD, NOTA_TOTAL, ZONA_HORARIA ";
        consulta += " FROM %s ";
        consulta += " WHERE ID_ESTUDIANTE = ?";
        return consulta;
    }

    public String obtenerListaPorId() {
        String consulta = "SELECT ID_ESTUDIANTE, NOMBRE, EDAD, CIUDAD, NOTA_TOTAL, ZONA_HORARIA ";
        consulta += " FROM %s ";
        return consulta;
    }

    public String insertar() {
        String consulta = "INSERT INTO %s (NOMBRE, EDAD, CIUDAD, NOTA_TOTAL, ZONA_HORARIA) VALUES (?,?,?,?,?) ";
        return consulta;
    }

    public String actualizar() {
        String consulta = " UPDATE %s SET ";
        consulta += " NOMBRE = ?,";
        consulta += " EDAD = ?, ";
        consulta += " CIUDAD = ?, ";
        consulta += " NOTA_TOTAL = ?, ";
        consulta += " ZONA_HORARIA = ? ";
        consulta += " WHERE ID_ESTUDIANTE = ? ";
        return consulta;
    }

    public String eliminar() {
        String consulta = " DELETE FROM %s WHERE ID_ESTUDIANTE = ?";
        return consulta;
    }

}
