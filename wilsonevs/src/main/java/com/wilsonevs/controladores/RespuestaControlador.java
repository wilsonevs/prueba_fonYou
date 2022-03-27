package com.wilsonevs.controladores;

import com.wilsonevs.modelos.dto.*;
import com.wilsonevs.modelos.modelo.Respuesta;
import com.wilsonevs.negocio.RespuestaNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/respuesta")
public class RespuestaControlador {

    @Autowired
    private RespuestaNegocio respuestaNegocio;

    @GetMapping("/")
    public ResponseEntity<RespuestaPaginada<Respuesta>> obtenerLista(@PathParam("id") RespuestaFiltroDTO datos){
        return new ResponseEntity<>(respuestaNegocio.obtenerLista(datos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO> obtenerPorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(respuestaNegocio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaInsertDTO> insertar(@RequestBody RespuestaInsertDTO dto) throws ParseException {
        return new ResponseEntity<>(respuestaNegocio.insertar(dto), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Respuesta> actualizar(@RequestBody Respuesta dto) {
        return new ResponseEntity<>(respuestaNegocio.actualizar(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Respuesta> eliminar(@PathVariable("id") Integer id){
        return new ResponseEntity<>(respuestaNegocio.eliminar(id), HttpStatus.OK);
    }


}
