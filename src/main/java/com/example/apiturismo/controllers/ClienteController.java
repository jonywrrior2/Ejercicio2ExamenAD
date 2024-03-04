package com.example.apiturismo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.apiturismo.pojos.Cliente;
import com.example.apiturismo.pojos.Estadisticas;
import com.example.apiturismo.repositories.ClienteRepository;



import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/nuevo")
    public Cliente insertarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @GetMapping("/activos")
    public List<Cliente> listarClientesActivosConVentasMayores(@RequestParam Double cantidad) {
        return clienteRepository.findByEstadoAndTotalGreaterThan("activo", cantidad);
    }

    @GetMapping("/estadisticas")
    public Estadisticas estadisticas() {
        Estadisticas estadisticas = new Estadisticas();

        List<Cliente> clientes = clienteRepository.findAll();
        double totalVentas = clientes.stream()
                .mapToDouble(Cliente::getTotal)
                .sum();
        estadisticas.setTotal(totalVentas);

        List<Cliente> clientesActivos = clienteRepository.findByEstado("activo");
        double promedioVentasActivos = clientesActivos.stream()
                .mapToDouble(Cliente::getTotal)
                .average()
                .orElse(0.0);
        estadisticas.setPromedio(promedioVentasActivos);

        long inactivosConVentas = clientes.stream()
                .filter(cliente -> cliente.getEstado().equals("inactivo") && cliente.getTotal() > 0)
                .count();
        estadisticas.setInactivos(inactivosConVentas);

        return estadisticas;
    }
}


