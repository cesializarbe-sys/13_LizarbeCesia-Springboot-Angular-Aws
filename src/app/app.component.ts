import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EstudianteService } from './estudiante.service';
import { Estudiante } from './estudiante.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  estudiantes: Estudiante[] = [];
  form: Estudiante = { nombre: '', carrera: '', ciclo: 0 };
  editandoId: number | null = null;

  constructor(private service: EstudianteService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.service.listar().subscribe(data => this.estudiantes = data);
  }

  guardar(): void {
    if (this.editandoId !== null) {
      this.service.actualizar(this.editandoId, this.form).subscribe(() => {
        this.cargar();
        this.limpiar();
      });
    } else {
      this.service.guardar(this.form).subscribe(() => {
        this.cargar();
        this.limpiar();
      });
    }
  }

  editar(e: Estudiante): void {
    this.editandoId = e.id!;
    this.form = { ...e };
  }

  eliminar(id: number): void {
    this.service.eliminar(id).subscribe(() => this.cargar());
  }

  limpiar(): void {
    this.form = { nombre: '', carrera: '', ciclo: 0 };
    this.editandoId = null;
  }
}