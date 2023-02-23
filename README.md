# Microcontrolador
Aceptar solicitudes RESTful que reciba como parámetro el nombre de la ciudad o las coordenadas largas de latitud y devuelva una sugerencia de lista de reproducción de acuerdo con la temperatura.


## Requisitos:
Para poder utilizar este programa, es necesario que su sistema operativo tenga instalado Java 11 o una versión más reciente. Si no cuenta con Java, puede descargar e instalar la versión correspondiente desde la página de descargas de Java en el sitio web oficial de Oracle: https://www.oracle.com/java/technologies/downloads/.

Además, es imprescindible contar con una base de datos PostgreSQL que cuente con los siguientes campos: hora, genero, ciudad, clima y canciones. Cabe destacar que todos los campos deben ser de tipo "carácter varying". Por otra parte cambiar el usuario y contraseña al igual que el nombre de la base de datos  en el application.properties.

Además, para usar el código, es necesario agregar un archivo llamado "service.properties" en la carpeta "resources". Este archivo debe contener la siguiente información:
>AuthUrlSpotify = valor

>keyOpenWeatherMap = valor

>ClienteIdSpotify = valor

>ClienteSecretoSpotify = valor


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

La ruta para entrar a la aplicacion es:    /api/temperatura/
