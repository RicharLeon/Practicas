package com.example.practicas1.models.services;

import com.example.practicas1.models.dao.IEmpleadoDao;
import com.example.practicas1.models.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

    @Autowired IEmpleadoDao empleadoDao;

    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoDao.findAll();
    }

    @Override
    public Empleado findById(Long id) {
        return empleadoDao.findById(id).orElse(null);
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    public void delete(Long id) {
        empleadoDao.deleteById(id);
    }

    @Override
    public Empleado update(Empleado empleado, Long id) {
        Optional<Empleado> empleadoOpcional = empleadoDao.findById(id);

        if (empleadoOpcional.isPresent()){
            Empleado empleadoActual = empleadoOpcional.get();

            empleadoActual.setNombre((empleado.getNombre()));
            empleadoActual.setApellido(empleado.getApellido());
            empleadoActual.setCargo(empleado.getCargo());
            empleadoActual.setArea(empleado.getArea());
            empleadoActual.setTipoContrato(empleado.getTipoContrato());
            empleadoActual.setProyecto(empleado.getProyecto());

            return empleadoDao.save(empleadoActual);
        } else {
            throw new RuntimeException("Empleado no encontrado con ID: " + id);
        }

    }


}
