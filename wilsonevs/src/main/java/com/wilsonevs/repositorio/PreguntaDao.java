package com.wilsonevs.repositorio;

import com.wilsonevs.modelos.dto.PreguntaFiltroDTO;
import com.wilsonevs.modelos.dto.PreguntaInsertDTO;
import com.wilsonevs.modelos.dto.PreguntaUpdateDTO;
import com.wilsonevs.modelos.dto.PreguntaValorNotaDTO;
import com.wilsonevs.modelos.modelo.Pregunta;
import com.wilsonevs.utilidades.Constantes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PreguntaDao {

    private PreguntaJdbc preguntaJdbc = new PreguntaJdbc();
    private PreguntaJRomMapper preguntaJRomMapper = new PreguntaJRomMapper();
    private String contexto = Constantes.BDS.PREGUNTA;

    private JdbcTemplate jdbcTemplate;
    PreguntaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Pregunta> obtenerListaUsuario(PreguntaFiltroDTO dto) {
        String sql = preguntaJdbc.obtenerListaUsuario(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.query(sql, preguntaJRomMapper);
    }

    public int totalListaUsuario(PreguntaFiltroDTO dto) {
        String sql = preguntaJdbc.totalListaUsuario(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{} , Integer.class);
    }


    public List<PreguntaValorNotaDTO> obtenerListaAdministrador(PreguntaFiltroDTO dto) {
        String sql = preguntaJdbc.obtenerListaAdministrador(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.query(sql, new PreguntaValorNotaDTOJRomMapper());
    }

    public int totalListaAdministrador(PreguntaFiltroDTO dto) {
        String sql = preguntaJdbc.totalListaAdministrador(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{} , Integer.class);
    }

    public Pregunta obtenerPorId(Integer id) {
        String sql = preguntaJdbc.obtenerPorId(id);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{id} , preguntaJRomMapper);
    }

    public List<Pregunta> obtenerListaPorId() {
        String sql = preguntaJdbc.obtenerListaPorId();
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.query(sql,preguntaJRomMapper);
    }


    public PreguntaValorNotaDTO obtenerValorNota(Integer id) {
        String sql = preguntaJdbc.obtenerPorId(id);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{id} , new PreguntaValorNotaDTOJRomMapper());
    }

    @Transactional
    public void insertar (PreguntaInsertDTO datos) {
        String sql = String.format(preguntaJdbc.insertar(), contexto);
        jdbcTemplate.update(sql,
                datos.getPregunta(),
                datos.getOpcion1(),
                datos.getOpcion2(),
                datos.getOpcion3(),
                datos.getOpcion4(),
                datos.getRespuesta().toUpperCase(),
                datos.getValor_nota());
    }

    @Transactional
    public void actualizar (PreguntaUpdateDTO datos) {
        String sql = String.format(preguntaJdbc.actualizar(), contexto);
        jdbcTemplate.update(sql,
                datos.getPregunta(),
                datos.getOpcion1(),
                datos.getOpcion2(),
                datos.getOpcion3(),
                datos.getOpcion4(),
                datos.getRespuesta().toUpperCase(),
                datos.getValor_nota(),
                datos.getId_pregunta());
    }


    @Transactional
    public void eliminar (Integer id) {
        String sql = preguntaJdbc.eliminar();
        sql = sql.replace("%s", contexto);
        jdbcTemplate.update(sql, id);
    }


    public static class PreguntaJRomMapper implements RowMapper<Pregunta> {
        @Override
        public Pregunta mapRow(ResultSet rs, int romNum ) throws SQLException {
            return new Pregunta(
                    rs.getInt("id_pregunta"),
                    rs.getString("pregunta"),
                    rs.getString("opcion1"),
                    rs.getString("opcion2"),
                    rs.getString("opcion3"),
                    rs.getString("opcion4")
            );
        }
    }

    public static class PreguntaValorNotaDTOJRomMapper implements RowMapper<PreguntaValorNotaDTO> {
        @Override
        public PreguntaValorNotaDTO mapRow(ResultSet rs, int romNum ) throws SQLException {
            return new PreguntaValorNotaDTO(
                    rs.getInt("id_pregunta"),
                    rs.getString("pregunta"),
                    rs.getString("opcion1"),
                    rs.getString("opcion2"),
                    rs.getString("opcion3"),
                    rs.getString("opcion4"),
                    rs.getString("respuesta"),
                    rs.getString("valor_nota")
            );
        }
    }

}
