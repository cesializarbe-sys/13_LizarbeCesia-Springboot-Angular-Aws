package vallegrande.edu.pe.cesia.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vallegrande.edu.pe.cesia.model.Estudiantes;
import vallegrande.edu.pe.cesia.service.EstudiantesService;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstudiantesRest {

    private final EstudiantesService service;

    @GetMapping
    public List<Estudiantes> listar() {
        return service.listar();
    }

    @PostMapping
    public Estudiantes guardar(@RequestBody Estudiantes estudiante) {
        return service.guardar(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiantes actualizar(@PathVariable Integer id,
                                  @RequestBody Estudiantes estudiante) {
        return service.actualizar(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}