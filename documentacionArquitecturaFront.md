# Ducumentación
La documentación de esta aplicación incluye un mockup, el cual se puede visualizar en la siguiente imagen:
<img src="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/Boceto%20de%20FrontEnd.png">

## Paquete "buscar-canciones"

### buscar-canciones.component.html
Este código representa una sección de la interfaz de usuario de una aplicación web que permite a los usuarios buscar canciones de Spotify por nombre de ciudad. Contiene una imagen del logotipo de Spotify, un campo de entrada de texto para que el usuario escriba el nombre de la ciudad, un botón de búsqueda que se habilita cuando el usuario ingresa texto en el campo de entrada y un componente personalizado de la aplicación para mostrar los resultados de la búsqueda de canciones de Spotify en la ciudad especificada.

### buscar-canciones.component.ts 
Este código define un componente de Angular llamado BuscarCancionesComponent que se encarga de buscar canciones de Spotify por nombre de ciudad. El componente tiene una propiedad para almacenar el texto de búsqueda, una propiedad para habilitar o deshabilitar el botón de búsqueda, un decorador para obtener una referencia al componente MostrarCancionesComponent y un método que llama al método imprimir() del componente MostrarCancionesComponent para mostrar los resultados de la búsqueda en la interfaz de usuario.



## Paquete "mostrar-canciones"

### mostrar-canciones.component.html
El código representa una sección de la interfaz de usuario de una aplicación web que muestra información sobre la ciudad seleccionada y una lista de canciones encontradas para esa ciudad. Se utiliza la directiva estructural *ngFor para iterar sobre la lista de canciones y mostrar información sobre cada una, como el título, el artista y el álbum. Además, se utiliza la directiva *ngIf para mostrar un mensaje si no se ha realizado una búsqueda de canciones para la ciudad seleccionada.


### mostrar-canciones.component.ts
Este código representa un componente Angular llamado MostrarCancionesComponent, que se utiliza para mostrar una lista de canciones y la información de la ciudad seleccionada. Se utiliza una instancia del servicio CancionesService para obtener la información de la ciudad y la lista de canciones. Si la llamada es exitosa, los datos se asignan a las propiedades city y canciones del componente. Este componente se utiliza en el componente BuscarCancionesComponent mediante la directiva app-mostrar-canciones.


### mostrar-canciones.model.ts
Este código define una interfaz TypeScript llamada ApiResponse. La interfaz describe la estructura de los datos de respuesta que se reciben desde algún API. La interfaz tiene dos propiedades: city, que es un objeto que contiene las coordenadas de la ciudad, el nombre de la ciudad, la temperatura y una lista de reproducción de Spotify asociada a la ciudad; y songs, que es una matriz de objetos que contiene información sobre las canciones, como el nombre de la canción, el nombre del artista, el álbum y la URL de la canción en Spotify.


### mostrar-canciones.service.ts
Este código define un servicio llamado CancionesService, que se utiliza para realizar peticiones HTTP al servidor. El servicio tiene un método llamado getPeticionCanciones que realiza una petición HTTP GET al servidor y devuelve la respuesta. También tiene un método llamado getInfo que llama al método getPeticionCanciones y utiliza el operador map de RxJS para asignar la respuesta de la petición a las propiedades canciones y citys del servicio. La clase utiliza la inyección de dependencias para obtener una instancia de HttpClient, que se utiliza para realizar la petición HTTP.



