package com.wilsonevs.repositorio;

import com.wilsonevs.modelos.dto.RespuestaFiltroDTO;
import com.wilsonevs.modelos.dto.RespuestaInsertDTO;
import com.wilsonevs.modelos.modelo.Pregunta;
import com.wilsonevs.modelos.modelo.Respuesta;
import com.wilsonevs.utilidades.Constantes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RespuestaDao {

    private RespuestaJdbc respuestaJdbc = new RespuestaJdbc();
    private RespuestaJRomMapper respuestaJRomMapper = new RespuestaJRomMapper();
    private String contexto = Constantes.BDS.RESPUESTA;

    private JdbcTemplate jdbcTemplate;
    RespuestaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Respuesta> obtenerLista(RespuestaFiltroDTO dto) {
        String sql = respuestaJdbc.obtenerLista(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.query(sql, respuestaJRomMapper);
    }

    public int totalLista(RespuestaFiltroDTO dto) {
        String sql = respuestaJdbc.totalLista(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{} , Integer.class);
    }

    public Respuesta obtenerPorId(Integer id) {
        String sql = respuestaJdbc.obtenerPorId(id);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{id} , respuestaJRomMapper);
    }

    public List<Respuesta> obtenerListaPorId() {
        String sql = respuestaJdbc.obtenerListaPorId();
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.query(sql, respuestaJRomMapper);
    }

    public Respuesta obtenerRespuesta(Integer id, String respuesta) {
        String sql = respuestaJdbc.obtenerRespuesta(id, respuesta);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql,  respuestaJRomMapper);
    }

    @Transactional
    public void insertar (RespuestaInsertDTO datos) {
        String sql = String.format(respuestaJdbc.insertar(), contexto);
        jdbcTemplate.update(sql,
                datos.getId_estudiante(),
                datos.getId_pregunta(),
                datos.getRespuesta().toUpperCase());
    }

    @Transactional
    public void actualizar (Respuesta datos) {
        String sql = String.format(respuestaJdbc.actualizar(), contexto);
        jdbcTemplate.update(sql,
                datos.getId_estudiante(),
                datos.getId_pregunta(),
                datos.getRespuesta().toUpperCase(),
                datos.getId_respuesta());
    }


    @Transactional
    public void eliminar (Integer id) {
        String sql = respuestaJdbc.eliminar();
        sql = sql.replace("%s", contexto);
        jdbcTemplate.update(sql, id);
    }


    public static class RespuestaJRomMapper implements RowMapper<Respuesta> {
        @Override
        public Respuesta mapRow(ResultSet rs, int romNum ) throws SQLException {
            return new Respuesta(
                    rs.getInt("id_respuesta"),
                    rs.getInt("id_estudiante"),
                    rs.getInt("id_pregunta"),
                    rs.getString("respuesta")
            );
        }
    }


}
