import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';


@Component({
  selector: 'app-buscar-canciones',
  templateUrl: './buscar-canciones.component.html',
  styleUrls: ['./buscar-canciones.component.css']
})
export class BuscarCancionesComponent {
  inputText: string = '';
  botonHabilitado: boolean = false;

  
  buscarCiudad() {
    console.log(this.inputText);
    
  }
}
