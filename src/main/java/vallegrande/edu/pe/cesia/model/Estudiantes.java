package vallegrande.edu.pe.cesia.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Estudiantes")
public class Estudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Carrera")
    private String carrera;

    @Column(name = "Ciclo")
    private Integer ciclo;
}