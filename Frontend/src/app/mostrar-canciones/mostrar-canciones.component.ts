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
  canciones: any = "";
  city: any;
  error: any = "";

  constructor(private cancionesService: CancionesService) { }

  imprimir (ciudad:string) {
    this.cancionesService.getInfo(ciudad).subscribe(
      () => {
        this.canciones = this.cancionesService.canciones
        this.city = this.cancionesService.citys;
        this.error = this.cancionesService.error;

        if(typeof(this.error) == 'undefined') {
          this.error = "";
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
