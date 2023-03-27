import { Component, Injectable } from '@angular/core';
import { CancionesService } from './mostrar-canciones.service';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-mostrar-canciones',
  templateUrl: './mostrar-canciones.component.html',
  styleUrls: ['./mostrar-canciones.component.css']
})
export class MostrarCancionesComponent {
  canciones: any;
  city: any;

  constructor(private cancionesService: CancionesService) { }

  imprimir (ciudad:string) {
    this.cancionesService.getCanciones(ciudad).subscribe(
      (canciones: any[]) => {
        this.canciones = canciones;
      },
      (error) => {
        console.log(error);
      }
    );

    this.cancionesService.getCity(ciudad).subscribe(
      (city: any) => {
        this.city = city;
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
