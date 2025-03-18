package com.example.demo;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar um cliente
    @PostMapping
    public void create(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // Buscar um cliente por ID
    @GetMapping("/{id}")
    public Cliente get(@PathVariable String id) {
        return clienteRepository.findById(id);
    }
    
    // Buscar todos os clientes
    @GetMapping
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
    
    // Atualizar um cliente completamente
    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody Cliente cliente){
        cliente.setId(id);
        clienteRepository.save(cliente);
    }
    
    // Atualizar parcialmente um cliente
    @PatchMapping("/{id}")
    public void updatePartial(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "nome":
                        cliente.setNome((String) value);
                        break;
                    case "email":
                        cliente.setEmail((String) value);
                        break;
                }
            });
            clienteRepository.save(cliente);
        }
    }
    
    // Deletar cliente
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        clienteRepository.deleteById(id);
    }
}
