package com.example.practicas1.models.dao;

import com.example.practicas1.models.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoDao extends JpaRepository<Empleado, Long> {
}
