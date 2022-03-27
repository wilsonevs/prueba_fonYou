package com.wilsonevs.controladores;

import com.wilsonevs.modelos.dto.*;
import com.wilsonevs.modelos.modelo.Estudiante;
import com.wilsonevs.negocio.EstudianteNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;

@RestController

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/estudiante")
public class EstudianteControlador {

    @Autowired
    private EstudianteNegocio estudianteNegocio;

    @GetMapping("/")
    public ResponseEntity<RespuestaPaginada<Estudiante>> obtenerLista(@PathParam("id") EstudianteFiltroDTO datos){
        return new ResponseEntity<>(estudianteNegocio.obtenerLista(datos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerPorId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(estudianteNegocio.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EstudianteInsertarDTO> insertar(@RequestBody EstudianteInsertarDTO dto)  {
        return new ResponseEntity<>(estudianteNegocio.insertar(dto), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante dto) {
        return new ResponseEntity<>(estudianteNegocio.actualizar(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estudiante> eliminar(@PathVariable("id") Integer id){
        return new ResponseEntity<>(estudianteNegocio.eliminar(id), HttpStatus.OK);
    }


}