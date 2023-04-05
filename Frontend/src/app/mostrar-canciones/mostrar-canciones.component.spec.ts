import { TestBed } from '@angular/core/testing';

import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CancionesService } from './mostrar-canciones.service';

describe('CancionesService', () => {
  let service: CancionesService;
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

  it('should return an Observable<ApiResponse>', () => {
    const mockApiResponse = {
      city: {
        cordinates: "123.456,789.012",
        playlist: "some playlist",
        name: "some city",
        temperature: "20.00"
      },
      songs: [
        {
          artista: "some artist",
          name: "some song",
          albun: "some album",
          URL: "http://someurl.com/song.mp3"
        }
      ]
    };

    const city = 'Buenos Aires';

    service.getCanciones(city).subscribe((response) => {
      expect(response).toEqual(mockApiResponse);
    });

    const req = httpMock.expectOne(`http://localhost:8080/api/temperatura?ciudad=${city}`);
    expect(req.request.method).toBe('GET');
    req.flush(mockApiResponse);
  });

});
