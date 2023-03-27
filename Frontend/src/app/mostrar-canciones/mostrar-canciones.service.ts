import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CancionesService {
  constructor(private http: HttpClient) { }

  getPeticionCanciones(ciudad: string) {
    const url = `http://localhost:8080/api/temperatura?ciudad=${ciudad}`;
    return this.http.get(url);
  }

  getCanciones(ciudad: string) {
    return this.getPeticionCanciones(ciudad).pipe(
      map((response: any) => {
        return response.songs;
      })
    );
  }

  getCity(ciudad: string) {
    return this.getPeticionCanciones(ciudad).pipe(
      map((response: any) => {
        return response.city;
      })
    );
  }

}
