package vallegrande.edu.pe.cesia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vallegrande.edu.pe.cesia.model.Estudiantes;

@Repository
public interface EstudiantesRepository extends JpaRepository<Estudiantes, Integer> {
}