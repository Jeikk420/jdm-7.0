package com.foro.ms_usuarios.controller;

import com.foro.ms_usuarios.dto.UsuarioRequestDTO;
import com.foro.ms_usuarios.dto.UsuarioResponseDTO;
import com.foro.ms_usuarios.service.UsuarioService;
import jakarta.validation.Valid;

// Asegúrate de que esta sea la única importación de List
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

    // ==========================================
    // ➕ CREAR
    // ==========================================
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO response = service.crearUsuario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ==========================================
    // 📋 LISTAR TODOS (¡Corregido y blindado!)
    // ==========================================
    @GetMapping(path = "/lista") 
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    // ==========================================
    // 🔍 BUSCAR POR ID
    // ==========================================
    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.obtenerPorId(id), HttpStatus.OK);
    }

    // ==========================================
    // 🛠️ ACTUALIZAR (PUT)
    // ==========================================
    @PutMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable("id") Long id, @Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO response = service.actualizarUsuario(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK); 
    }

    // ==========================================
    // 🗑️ ELIMINAR (DELETE)
    // ==========================================
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        service.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}