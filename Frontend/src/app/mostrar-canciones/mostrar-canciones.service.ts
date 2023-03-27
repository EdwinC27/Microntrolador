import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CancionesService {
  constructor(private http: HttpClient) { }

  public citys: any;
  public canciones:any;

  getPeticionCanciones(ciudad: string) {
    const url = `http://localhost:8080/api/temperatura?ciudad=${ciudad}`;
    return this.http.get(url);
  }

  getInfo(ciudad: string) {
    return this.getPeticionCanciones(ciudad).pipe(
      map((response: any) => {
        this.canciones = response.songs;
        this.citys = response.city;
      })
    );
  }

}
