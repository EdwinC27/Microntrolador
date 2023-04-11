import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CancionesService } from './mostrar-canciones.service';
import { MostrarCancionesComponent } from './mostrar-canciones.component';

describe('CancionesService', () => {
  let service: CancionesService;
  let component: MostrarCancionesComponent;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CancionesService]
    });
    service = TestBed.inject(CancionesService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have getPeticionCanciones function', () => {
    const city = "zapopan";

    service.getPeticionCanciones(city).subscribe({
      next: () => fail('expected an error, but got a success response'),
      error: (err: any) => {
        expect(err).toBeTruthy();
      }
    });
  });

  it('should have getInfo function', () => {
    const city = "zapopan";

    service.getInfo(city).subscribe({
      next: () => fail('expected an error, but got a success response'),
      error: (err: any) => {
        expect(err).toBeTruthy();
      }
    });
  });

  it('should get songs and city from server', () => {
    const city = 'zapopan';
    const songs = [{ title: 'Song 1' }, { title: 'Song 2' }];

    service.getInfo(city).subscribe(() => {
      expect(service.canciones).toEqual(songs);
      expect(service.citys).toEqual(city);
    });

    const req = httpMock.expectOne(`http://localhost:8080/api/temperatura?ciudad=${city}`);
    expect(req.request.method).toBe('GET');
    req.flush({ songs, city });
  });


  it('should get songs and city on print', () => {
    const city = 'zapopan';
    const songs = [{ title: 'Song 1' }, { title: 'Song 2' }];

    component.imprimir(city);

    const req = httpMock.expectOne(`http://localhost:8080/api/temperatura?ciudad=${city}`);
    expect(req.request.method).toBe('GET');
    req.flush({ songs, city });

    expect(component.canciones).toEqual(songs);
    expect(component.city).toEqual(city);
  });
  
});
