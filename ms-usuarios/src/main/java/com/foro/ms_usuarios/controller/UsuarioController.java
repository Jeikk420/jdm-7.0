package com.foro.ms_usuarios.controller;

import com.foro.ms_usuarios.dto.UsuarioRequestDTO;
import com.foro.ms_usuarios.dto.UsuarioResponseDTO;
import com.foro.ms_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO request) {
        return new ResponseEntity<>(service.crearUsuario(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/lista", produces = "application/json") 
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(service.obtenerPorId(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable(name = "id") Long id, @Valid @RequestBody UsuarioRequestDTO request) {
        return new ResponseEntity<>(service.actualizarUsuario(id, request), HttpStatus.OK); 
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Long id) {
        service.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}