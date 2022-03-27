package com.wilsonevs.controladores;

import com.wilsonevs.modelos.dto.*;
import com.wilsonevs.modelos.modelo.Pregunta;
import com.wilsonevs.negocio.PreguntaNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/pregunta")
public class PreguntaControlador {

    @Autowired
    private PreguntaNegocio preguntaNegocio;

    @GetMapping("/usuario")
    public ResponseEntity<RespuestaPaginada<Pregunta>> obtenerListaUsuario(@PathParam("id") PreguntaFiltroDTO datos){
        return new ResponseEntity<>(preguntaNegocio.obtenerListaUsuario(datos), HttpStatus.OK);
    }

    @GetMapping("/administrador")
    public ResponseEntity<RespuestaPaginada<PreguntaValorNotaDTO>> obtenerListaAdministrador(@PathParam("id") PreguntaFiltroDTO datos){
        return new ResponseEntity<>(preguntaNegocio.obtenerListaAdministrador(datos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntaDTO> obtenerPorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(preguntaNegocio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PreguntaInsertDTO> insertar(@RequestBody PreguntaInsertDTO dto) {
        return new ResponseEntity<>(preguntaNegocio.insertar(dto), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<PreguntaUpdateDTO> actualizar(@RequestBody PreguntaUpdateDTO dto) {
        return new ResponseEntity<>(preguntaNegocio.actualizar(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pregunta> eliminar(@PathVariable("id") Integer id){
        return new ResponseEntity<>(preguntaNegocio.eliminar(id), HttpStatus.OK);
    }

}
