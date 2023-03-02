# Microcontrolador
Aceptar solicitudes RESTful que reciba como parámetro el nombre de la ciudad o las coordenadas longitud y latitud ademas devuelva una sugerencia de lista de reproducción de acuerdo con la temperatura.


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

Para mas informacion de arquitectura acceda a este documento <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/documentacionArquitectura.md">documentacionArquitectura.md</a> 

### Para acceder a la aplicación, son con las siguientes URLs:

> http://localhost:8080/api/temperatura/?ciudad=

> http://localhost:8080/api/temperatura/?latitud=&longitud=

### Para acceder a la documentación de OpenAPI, puedes utilizar las siguientes URLs:

>http://localhost:8080/v3/api-docs: esta URL te llevará a la especificación OpenAPI en formato JSON. Aquí podrás encontrar toda la información sobre los endpoints, parámetros, respuestas, y demás detalles de la API.

>http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config: esta URL te llevará a la interfaz de usuario Swagger UI. Esta herramienta te permitirá explorar la documentación de la API de una manera más amigable y visual. Aquí podrás ver una lista de endpoints, sus detalles y también realizar pruebas directamente desde la interfaz.

## Requisitos:
- Para poder utilizar este programa, es necesario que su sistema operativo tenga instalado Java 11 o una versión más reciente. Si no cuenta con Java, puede descargar e instalar la versión correspondiente, por ejemplo desde la página de: https://adoptium.net/temurin/releases/.

- Es imprescindible contar con una base de datos PostgreSQL, en la carpeta **Base_Datos** esta el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/Base_Datos/schemaAPI.sql">schemaAPIl.sql</a> con los comandos para crear la tabla correspondiente. 

- Cambia el usuario y contraseña al igual que el nombre de la base de datos en el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/src/main/resources/application-dev.properties">application-dev.properties</a> que esta en la ruta **src/main/resources**.

- Modificar el archivo llamado <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/src/main/resources/service.properties">service.properties</a> de la carpeta **src/main/resources** donde tienes que poner los valores solicitados para la autenticacion. 

- Asegúrese de tener esta información correcta y actualizada antes de ejecutar el programa.

## Inicializacion
Para utilizar este proyecto 

### Descarga el proyecto comprimido en .zip
1. Descarga el proyecto en tu PC desde la opción **code** de este repositorio.
2. Modificar el archivo **service.properties** que esta en la ruta **src/main/resources** con los datos nesesarios para la autenticacion hacia las APIs, tienes que poner tus propios datos de autenticacion y los url necesarias  
3. Implementar la base de datos postgresql corespondiente 
   - La base de datos se debe de llamar canciones
   - En el directorio Base_Datos hay un archivo llamado **schemaAPI** con los comandos para crear la tabla necesaria
4. Ejecuta la aplicacion con 
   ``` 
      mvn spring-boot:run 
   ```
  

### Clona el repositorio
1. Clona el proyecto en tu PC. 
    ```  
       git clone git@github.com:EdwinC27/Microcontrolador.git  
    ```
2. Modificar el archivo **service.properties** que esta en la ruta **src/main/resources** con los datos nesesarios para la autenticacion hacia las APIs, tienes que poner tus propios datos de autenticacion y los url necesarias  
3. Implementar la base de datos postgresql corespondiente 
   - La base de datos se debe de llamar canciones
   - En el directorio Base_Datos hay un archivo llamado **schemaAPI** con los comandos para crear la tabla necesaria
4. Ejecuta la aplicacion con 
     ```  
       mvn spring-boot:run  
     ```

### Docker
1. Descarga el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/dockerDir/docker-compose.yml">docker-compose.yml </a> que esta en la carpeta dockerDir
2. Abre una terminal y ubicate en la ruta del archivo descargado anteriormente
3. Ejecuta el siguiente comando 
   ```  
      docker-compose up -d  
   ```
