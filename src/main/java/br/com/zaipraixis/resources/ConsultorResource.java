package br.com.zaipraixis.resources;

import br.com.zaipraixis.dtos.ConsultorDTO;
import br.com.zaipraixis.services.ConsultorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultores")
public class ConsultorResource {

    private final ConsultorService consultorService;

    public ConsultorResource(ConsultorService consultorService) {
        this.consultorService = consultorService;
    }

    // 🔹 Listar todos
    @GetMapping
    public ResponseEntity<List<ConsultorDTO>> findAll() {
        return ResponseEntity.ok(consultorService.findAll());
    }

    // 🔹 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConsultorDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(consultorService.findById(id));
    }

    // 🔹 Criar
    @PostMapping
    public ResponseEntity<ConsultorDTO> create(@Valid @RequestBody ConsultorDTO dto) {
        ConsultorDTO created = consultorService.create(dto);
        return ResponseEntity.ok(created);
    }

    // 🔹 Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<ConsultorDTO> update(
            @PathVariable String id,
            @Valid @RequestBody ConsultorDTO dto) {

        ConsultorDTO updated = consultorService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        consultorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
