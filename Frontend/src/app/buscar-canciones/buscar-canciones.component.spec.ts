import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarCancionesComponent } from './buscar-canciones.component';

describe('BuscarCancionesComponent', () => {
  let component: BuscarCancionesComponent;
  let fixture: ComponentFixture<BuscarCancionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuscarCancionesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuscarCancionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have inputText and botonHabilitado initialized to empty string and false', () => {
    expect(component.inputText).toEqual('');
    expect(component.botonHabilitado).toEqual(false);
  });

  it('should update botonHabilitado when inputText is changed', () => {
    component.inputText = 'test';
    expect(component.botonHabilitado).toEqual(true);
  });

  it('should call mostrarCancionesComponent.imprimir when buscarCiudad is called', () => {
    spyOn(component.mostrarCancionesComponent, 'imprimir');
    component.inputText = 'test';
    component.buscarCiudad();
    expect(component.mostrarCancionesComponent.imprimir).toHaveBeenCalledWith('test');
  });
});
