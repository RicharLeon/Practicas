package com.example.practicas1.controllers;

import com.example.practicas1.models.entity.Empleado;
import com.example.practicas1.models.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4040"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestControllers {

    Map<String, Object> response = new HashMap<>();
    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> index(){
        return empleadoService.findAll();
    }


    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> unSoloEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoService.findById(id);

        try {
            if (empleado == null){
                response.put("message", "El empleado con el ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/empleado")
    public ResponseEntity<?> agregarNuevoEmpelado(@Valid @RequestBody Empleado empleado, BindingResult result){
        Empleado empleadoNuevo = null;

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("error", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            empleadoNuevo = empleadoService.save(empleado);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El empleado a sido creado con exito");
        response.put("empleado", empleadoNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);


    }


    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> editarEmpleado(@RequestBody Empleado empleado, @PathVariable Long id){
        empleadoService.update(empleado, id);

        response.put("mensaje", "El empleado ah sido actualizado con exito");
        response.put("empleado", empleado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id){

        empleadoService.delete(id);
        response.put("mensaje", "El empleado ah sido eliminado con exito");
        response.put("empleado", id);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }
}
