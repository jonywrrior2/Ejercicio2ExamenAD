package com.example.apiturismo.repositories;


import com.example.apiturismo.pojos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByEstadoAndTotalGreaterThan(String estado, Double cantidad);

    List<Cliente> findByEstado(String activo);
}

