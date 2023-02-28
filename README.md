# Microcontrolador
Aceptar solicitudes RESTful que reciba como parámetro el nombre de la ciudad o las coordenadas largas de latitud y devuelva una sugerencia de lista de reproducción de acuerdo con la temperatura.


## Requisitos:
Para poder utilizar este programa, es necesario que su sistema operativo tenga instalado Java 11 o una versión más reciente. Si no cuenta con Java, puede descargar e instalar la versión correspondiente, por ejemplo desde la página de: https://adoptium.net/temurin/releases/.

Además, es imprescindible contar con una base de datos PostgreSQL que cuente con los siguientes campos: hora, genero, ciudad, clima y canciones. Cabe destacar que todos los campos deben ser de tipo "carácter varying". Por otra parte cambiar el usuario y contraseña al igual que el nombre de la base de datos  en el application.properties.

Además, para usar el código, es necesario modificar el archivo llamado "service.properties" de la carpeta "resources". Este archivo debe contener la siguiente información:
>AuthUrlSpotify = valor

>keyOpenWeatherMap = valor

>ClienteIdSpotify = valor

>ClienteSecretoSpotify = valor

>urlSpotifyPeticion = valor

Estos datos son necesarios para la autenticación y autorización en las APIs de Spotify y OpenWeatherMap. Asegúrese de tener esta información correcta y actualizada antes de ejecutar el programa.

Es el repositorio se encuentra una carpeta llamada “Base_Datos” que contiene  el archivo “schemaAPI” que contiene el codigo necesario para crear la tabla de la base de datos.

## Uso de la aplicación: 
Este código utiliza los servicios web de OpenWeatherMap y Spotify para obtener la temperatura de una ciudad o coordenadas de latitud y longitud,
y encontrar canciones que corresponden al género de música que se ajusta a la temperatura.

Es una implementación de un servicio que utiliza la API de OpenWeatherMap para obtener la temperatura actual de una ciudad o ubicación geográfica, 
y la API de Spotify para hacer solicitudes de canciones de un género determinado basándose en la temperatura del clima. Los resultados de las APIs
se mantienen en caché y se actualizan cada cierto tiempo. Los datos obtenidos de la API de OpenWeatherMap se actualizan cada 3 minutos y se 
mantienen en caché durante ese período. Por otro lado, los datos de la API de Spotify se actualizan cada 12 horas y se almacenan en caché durante ese 
tiempo. Después de obtener los datos de ambas API, se guarda la información relevante, como la hora de la solicitud, el género musical, la ciudad y 
las canciones recomendadas en una base de datos Postgres.

<img src="https://github.com/EdwinC27/Microcontrolador/blob/main/diagramaSecuencia.png">

La ruta para entrar a la aplicacion es:    localhost:8080/api/temperatura/

### Para acceder a la aplicación, son con las siguientes URLs:

> http://localhost:8080/api/temperatura/?ciudad=

> http://localhost:8080/api/temperatura/?latitud=&longitud=

### Para acceder a la documentación de OpenAPI, puedes utilizar las siguientes URLs:

>http://localhost:8080/v3/api-docs: esta URL te llevará a la especificación OpenAPI en formato JSON. Aquí podrás encontrar toda la información sobre los endpoints, parámetros, respuestas, y demás detalles de la API.

>http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config: esta URL te llevará a la interfaz de usuario Swagger UI. Esta herramienta te permitirá explorar la documentación de la API de una manera más amigable y visual. Aquí podrás ver una lista de endpoints, sus detalles y también realizar pruebas directamente desde la interfaz.


## Inicializacion
1. Descargar el proyecto. 
2. Modificar el archivo service.propertiese 
3. Implementar la dase de datos corespondiente.
4. Correr con spring boot run.
