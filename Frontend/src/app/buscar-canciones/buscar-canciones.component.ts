import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MostrarCancionesComponent } from '../mostrar-canciones/mostrar-canciones.component';

@Component({
  selector: 'app-buscar-canciones',
  templateUrl: './buscar-canciones.component.html',
  styleUrls: ['./buscar-canciones.component.css']
})
export class BuscarCancionesComponent {
  inputText: string = '';
  botonHabilitado: boolean = false;

  @ViewChild('mostrarCanciones', { static: false })
  mostrarCancionesComponent!: MostrarCancionesComponent;

  buscarCiudad() {
    console.log(this.inputText);
    this.mostrarCancionesComponent.imprimir(this.inputText);
  }
}
