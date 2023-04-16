# Documentación         
 
Este servicio web implementa una API RESTful que acepta solicitudes que contienen el nombre de la ciudad o las coordenadas de longitud y latitud como parámetros, y devuelve una lista de reproducción de Spotify con canciones que se ajustan a la temperatura de la ubicación. 

## Uso de la aplicación: 
Este servicio utiliza las APIs de OpenWeatherMap y Spotify para obtener la temperatura actual de la ubicación especificada y las canciones recomendadas basadas en la temperatura. Los datos se almacenan en caché para reducir las solicitudes a las APIs. Los datos de OpenWeatherMap se actualizan cada 3 minutos y se almacenan en caché durante ese período, mientras que los datos de Spotify se actualizan cada 12 horas y se almacenan en caché durante ese tiempo. Después de obtener los datos de ambas APIs, se guarda la información relevante, como la hora de la solicitud, el género musical, la ciudad y las canciones recomendadas en una base de datos PostgreSQL.

<img src="https://github.com/EdwinC27/Microcontrolador/blob/main/diagramaSecuencia.png">


### Para utilizar el servicio, se pueden enviar solicitudes a través de dos URLs:

> http://localhost:8080/api/temperatura/?ciudad=nombre-de-ciudad : para obtener la temperatura de una ciudad en particular.

> http://localhost:8080/api/temperatura/?latitud=valor-latitud&longitud=valor-longitud : para obtener la temperatura de una ubicación específica según sus coordenadas de longitud y latitud.

### Para explorar la documentación de la API, se pueden utilizar las siguientes URLs:

> http://localhost:8080/v3/api-docs: para acceder a la especificación OpenAPI en formato JSON.

> http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config: para acceder a la interfaz de usuario Swagger UI.

# Detalles de cada paquetes
 
## Paquete “servicios”
* La implementación del Conector_OpenWeatherMap se utiliza para obtener la temperatura actual de una ciudad o coordenadas geográficas mediante la conexión a la API de OpenWeatherMap. Tiene dos métodos principales, "getURLCiudad" y "getURLCordenada", que se intentan ejecutar hasta un máximo de 3 veces. También utiliza caché para evitar solicitudes repetitivas y dos variables de instancia para contener la clave de API y la URL base para hacer las solicitudes.

* La implementación de Llave_Spotify obtiene un token de acceso mediante la autenticación de un cliente con sus credenciales. Verifica si el token actual está vigente para obtener uno nuevo si es necesario. El método principal de la clase es "getToken()". También utiliza RestTemplate para realizar una solicitud HTTP POST a la URL de autorización de Spotify.

* La implementación de Conector_Spotify utiliza métodos para hacer peticiones a la API de Spotify y devuelve resultados basados en la temperatura y el género musical. También utiliza una instancia de la clase Llave_Spotify para obtener un token de acceso a la API. Contiene métodos para procesar la respuesta de la API y extraer información de las canciones.

* La implementación de General_Json tiene dos métodos. El primer método, infoRegresada, crea un objeto JSON y agrega información de la ciudad y su temperatura, coordenadas y nombre de la playlist en un objeto JSON cityLista. También agrega información de las canciones en un array JSON songsListas. El segundo método, erroGenerado, crea un objeto JSON que contiene información sobre el error que ocurrió.



## Paquete “controladores”
* La implementación de Consulta es un código que define un controlador REST para obtener la temperatura y canciones recomendadas para una ciudad o coordenadas geográficas. El controlador "Consulta" está anotado con "@RestController", y tiene tres campos autowired: "Conector_OpenWeatherMap", "RepositorioListaMusica" y "Conector_Spotify". El método "getTemperatura" es un controlador REST anotado con "@GetMapping" y accesible en la ruta "/api/temperatura". Toma tres parámetros opcionales ("ciudad", "latitud" y "longitud") y devuelve un objeto JSON con información sobre la temperatura y las canciones recomendadas. El método verifica la conexión a Internet y, si es exitosa, determina si se proporciona una ciudad o coordenadas geográficas. Si se proporciona una ciudad, obtiene la temperatura de la ciudad usando "getURLCiudad" del objeto "Conector_OpenWeatherMap", y luego genera una lista de canciones recomendadas para la temperatura con "peticionGenero" del objeto "Conector_Spotify". El método también guarda la información de la ciudad en una base de datos. Si se proporcionan coordenadas geográficas, realiza los mismos pasos. El método "guardarMiEntidad" guarda la información de la temperatura, la ciudad y las canciones recomendadas en un objeto "ListaMusica" que se guarda en la base de datos utilizando el objeto "RepositorioListaMusica".



## Paquete “configuraciones” 
* La implementación de ComprobarConexionJava tiene un método llamado "conexion()" que devuelve un booleano indicando si hay o no conexión a Internet. Este método crea un objeto Socket para intentar conectarse a un servidor en la dirección web y puerto especificados. Si la conexión es exitosa, devuelve "true", de lo contrario, devuelve "false".

* La implementación de OpenWeatherMapCache es un componente de Spring que usa la anotación @Component y @EnableScheduling para programar tareas en la aplicación. Esta clase maneja una caché de resultados obtenidos de la API de OpenWeatherMap. El método limpiarCache se ejecuta cada 3 minutos para borrar los resultados antiguos y asegurarse de que se obtengan los resultados más recientes de la API.

* La implementación de SpotifyCache es un componente de Spring que usa la anotación @EnableScheduling para programar tareas en la aplicación. Esta clase maneja una caché de resultados obtenidos de Spotify. El método limpiarCache se ejecuta cada 12 horas para borrar los resultados antiguos y asegurarse de que los resultados almacenados en caché no ocupen espacio innecesario en la memoria.

* La implementación de ConfigCors es la clase de configuración de Spring que habilita la política de mismo origen cruzado (CORS) en una aplicación web Spring Boot. La política CORS es una medida de seguridad implementada por los navegadores web para prevenir ataques maliciosos en los navegadores, como el robo de cookies de sesión. 



## Paquete “entidades”
* La implementación de ListaMusica se encarga de de instanciar los campos que tiene la tabla **informacion_generada**.


## Paquete “repositorios”
* La implementación de RepositorioListaMusica define una interfaz que extiende la interfaz JpaRepository de Spring Data JPA y se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la tabla de base de datos correspondiente a la entidad "ListaMusica".
