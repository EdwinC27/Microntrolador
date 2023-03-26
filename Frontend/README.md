<<<<<<< HEAD
# Fronted

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 15.2.4.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
=======
# Servicio de sugerencias de canciones por temperatura
Este servicio web implementa una API RESTful que acepta solicitudes que contienen el nombre de la ciudad o las coordenadas de longitud y latitud como parámetros, y devuelve una lista de reproducción de Spotify con canciones que se ajustan a la temperatura de la ubicación. Para acceder a este servicio, es necesario loguearse y obtener un token de acceso.

## Uso de la aplicación: 
Este servicio utiliza las APIs de OpenWeatherMap y Spotify para obtener la temperatura actual de la ubicación especificada y las canciones recomendadas basadas en la temperatura. Los datos se almacenan en caché para reducir las solicitudes a las APIs. Los datos de OpenWeatherMap se actualizan cada 3 minutos y se almacenan en caché durante ese período, mientras que los datos de Spotify se actualizan cada 12 horas y se almacenan en caché durante ese tiempo. Después de obtener los datos de ambas APIs, se guarda la información relevante, como la hora de la solicitud, el género musical, la ciudad y las canciones recomendadas en una base de datos PostgreSQL.

<img src="https://github.com/EdwinC27/Microcontrolador/blob/main/diagramaSecuencia.png">

Para mas informacion de arquitectura acceda a este documento <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/documentacionArquitectura.md">documentacionArquitectura.md</a> 


### Token
Para poder utilizar este servicio, es necesario contar con un token de acceso. Para obtenerlo, debes enviar tus credenciales de usuario y contraseña a la siguiente dirección URL:
> http://localhost:8080/user

El token generado debe ser incluido en la cabezera de cada una de las peticiones que realices a las URLs correspondientes. Para ello, debes enviar el token junto con la solicitud correspondiente. Es importante mencionar que el token tiene una duración limitada y deberás obtener uno nuevo una vez que expire.

Para enviar tus credenciales, debes incluir un objeto JSON con el nombre de usuario y la contraseña en el cuerpo de la solicitud, como se muestra a continuación:
``` 
{
    "userName" : "user",
    "password" : "password"
}
```

### Para utilizar el servicio, se pueden enviar solicitudes a través de dos URLs:

> http://localhost:8080/api/temperatura/?ciudad=nombre-de-ciudad : para obtener la temperatura de una ciudad en particular.

> http://localhost:8080/api/temperatura/?latitud=valor-latitud&longitud=valor-longitud : para obtener la temperatura de una ubicación específica según sus coordenadas de longitud y latitud.

### Para explorar la documentación de la API, se pueden utilizar las siguientes URLs:

> http://localhost:8080/v3/api-docs: para acceder a la especificación OpenAPI en formato JSON.

> http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config: para acceder a la interfaz de usuario Swagger UI.

## Requisitos:
- Para poder utilizar este programa, es necesario que su sistema operativo tenga instalado Java 11 o una versión más reciente. Si no cuenta con Java, puede descargar e instalar la versión correspondiente, por ejemplo desde la página de: https://adoptium.net/temurin/releases/.

- Es imprescindible contar con una base de datos PostgreSQL, en la carpeta **Base_Datos** esta el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/Base_Datos/schemaAPI.sql">schemaAPIl.sql</a> con los comandos para crear las tablas correspondientes. 

- Cambia el usuario y contraseña al igual que el nombre de la base de datos en el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/src/main/resources/application-dev.properties">application-dev.properties</a> que esta en la ruta **src/main/resources**.

- Modificar el archivo llamado <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/src/main/resources/service.properties">service.properties</a> de la carpeta **src/main/resources** donde tienes que poner los valores solicitados para la autenticacion. 

- Asegúrese de tener esta información correcta y actualizada antes de ejecutar el programa.

## Inicializacion
Para utilizar este proyecto, se pueden seguir una las siguientes instrucciones:


### Descarga el proyecto comprimido en .zip
1. Descarga el proyecto en formato .zip desde la opción "Code" de este repositorio.
2. Una vez descargado, descomprime el archivo y abre la carpeta del proyecto.
3. Modifica el archivo **service.properties** que se encuentra en la ruta **src/main/resources**, agregando los datos necesarios para la autenticación hacia las APIs que vayas a utilizar. Debes poner tus propios datos de autenticación y las URLs necesarias. Al igual que el valor **keySecret** con un valor para encriptar.
4. Implementar la base de datos postgresql corespondiente 
   - Puedes cambiar el nombre de la base de datos.
   - En el directorio **Base_Datos** hay un archivo llamado **schemaAPI.sql** con los comandos para crear la tabla necesaria, (esta tabla se debe de llamar igual)
5. Finalmente, para ejecutar la aplicación, utiliza el comando:
     ```  
       mvn spring-boot:run  
     ```
     Este comando iniciará la aplicación utilizando Maven y Spring Boot. Una vez que la aplicación esté corriendo, podrás comenzar a utilizarla.
     

### Clona el repositorio
1. Clona el repositorio de Git en tu PC con el siguiente comando:
    ```  
       git clone git@github.com:EdwinC27/Microcontrolador.git  
    ```
2. Una vez descargado, abre la carpeta del proyecto
3. Modifica el archivo **service.properties** que se encuentra en la ruta **src/main/resources**, agregando los datos necesarios para la autenticación hacia las APIs que vayas a utilizar. Debes poner tus propios datos de autenticación y las URLs necesarias. Al igual que el valor **keySecret** con un valor para encriptar.
4. Implementar la base de datos postgresql corespondiente 
   - Puedes cambiar el nombre de la base de datos.
   - En el directorio **Base_Datos** hay un archivo llamado **schemaAPI.sql** con los comandos para crear la tabla necesaria, (esta tabla se debe de llamar igual)
5. Finalmente, para ejecutar la aplicación, utiliza el comando:
     ```  
       mvn spring-boot:run  
     ```
     Este comando iniciará la aplicación utilizando Maven y Spring Boot. Una vez que la aplicación esté corriendo, podrás comenzar a utilizarla.
     
### Docker
1. Descarga el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/main/dockerDir/docker-compose.yml">docker-compose.yml </a> que se encuentra en la carpeta dockerDir
2. Una vez descargado, abre el archivo
3. Modifica los valores de los siguientes campos según tu entorno:
   ```  
      - spring.datasource.url=jdbc:postgresql://<nombre-de-tu-host>:<puerto-de-PostgreSQL>/<nombre-de-tu-base-de-datos>
      - spring.datasource.username=<tu-usuario-de-PostgreSQL>
      - spring.datasource.password=<tu-contraseña-de-PostgreSQL>
   ```
4. Abre una terminal y ubícate en la ruta donde se encuentra el archivo modificado anteriormente.
5. Ejecuta el siguiente comando en la terminal:
   ```  
      docker-compose up -d
   ```
   Este comando levantará los contenedores de Docker definidos en el archivo docker-compose.yml en segundo plano. Espera unos segundos hasta que los contenedores estén listos para ser utilizados.
>>>>>>> b46dd7a48c7c5705232e7704b822ba41f78f7d38
