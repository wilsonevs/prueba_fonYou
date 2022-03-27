package com.wilsonevs.repositorio;

import com.wilsonevs.configuracion.ContextoEsquema;
import com.wilsonevs.modelos.dto.*;
import com.wilsonevs.modelos.modelo.Estudiante;
import com.wilsonevs.utilidades.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EstudianteDao {

    @Autowired
    private RespuestaDao respuestaDao;

    private EstudianteJdbc estudianteJdbc = new EstudianteJdbc();
    private RespuestaJdbc respuestaJdbc = new RespuestaJdbc();

    private EstudianteJRomMapper estudianteJRomMapper = new EstudianteJRomMapper();
    private String contexto = Constantes.BDS.ESTUDIANTE;

    private JdbcTemplate jdbcTemplate;
    EstudianteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Estudiante> obtenerLista(EstudianteFiltroDTO dto) {
        String sql = estudianteJdbc.obtenerLista(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.query(sql, estudianteJRomMapper);
    }

    public int totalLista(EstudianteFiltroDTO dto) {
        String sql = estudianteJdbc.totalLista(dto);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{} , Integer.class);
    }

    public Estudiante obtenerPorId(Integer id) {
        String sql = estudianteJdbc.obtenerPorId(id);
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.queryForObject(sql, new Object[]{id} , estudianteJRomMapper);
    }

    public List<Estudiante> obtenerListaPorId() {
        String sql = estudianteJdbc.obtenerListaPorId();
        sql = sql.replace("%s", contexto);
        return jdbcTemplate.query(sql,estudianteJRomMapper);
    }

    @Transactional
    public void insertar (EstudianteInsertarBackgroundDTO datos) {
        String sql = String.format(estudianteJdbc.insertar(), contexto);
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sqlEstudiante = String.format(estudianteJdbc.insertar(), contexto);
                PreparedStatement ps = connection.prepareStatement(sqlEstudiante, new String[]{"id_estudiante"});
                ps.setString(1, datos.getNombre());
                ps.setString(2, datos.getEdad());
                ps.setString(3, datos.getCiudad());
                ps.setInt(4, datos.getNota_total());
                ps.setString(5, datos.getZona_horaria());
                return ps;
            }
        }, holder);

        Integer idEstudiante = holder.getKey().intValue();


        for (EstudianteRespuestaDTO dto :datos.getRespuesta()){
            RespuestaInsertDTO insertDTO = new RespuestaInsertDTO();
            insertDTO.setId_estudiante(idEstudiante);
            insertDTO.setId_pregunta(dto.getId_pregunta());
            insertDTO.setRespuesta(dto.getRespuesta());
            respuestaDao.insertar(insertDTO);
        }


    }

    @Transactional
    public void actualizar (Estudiante datos) {
        String sql = String.format(estudianteJdbc.actualizar(), contexto);
        jdbcTemplate.update(sql,
                datos.getNombre(),
                datos.getEdad(),
                datos.getCiudad(),
                datos.getNota_total(),
                datos.getZona_horaria(),
                datos.getId_estudiante());
    }


    @Transactional
    public void eliminar (Integer id) {
        String sql = estudianteJdbc.eliminar();
        sql = sql.replace("%s", contexto);
        jdbcTemplate.update(sql, id);
    }


    public static class EstudianteJRomMapper implements RowMapper<Estudiante> {
        @Override
        public Estudiante mapRow(ResultSet rs, int romNum ) throws SQLException {
            return new Estudiante(
                    rs.getInt("id_estudiante"),
                    rs.getString("nombre"),
                    rs.getString("edad"),
                    rs.getString("ciudad"),
                    rs.getInt("nota_total"),
                    rs.getString("zona_horaria")
            );
        }
    }

}
