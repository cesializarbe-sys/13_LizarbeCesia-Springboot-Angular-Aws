package vallegrande.edu.pe.cesia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vallegrande.edu.pe.cesia.model.Estudiantes;
import vallegrande.edu.pe.cesia.repository.EstudiantesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudiantesService {

    private final EstudiantesRepository repository;

    public List<Estudiantes> listar() {
        return repository.findAll();
    }

    public Estudiantes guardar(Estudiantes estudiante) {
        return repository.save(estudiante);
    }

    public Estudiantes actualizar(Integer id, Estudiantes estudiante) {
        estudiante.setId(id);
        return repository.save(estudiante);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}