# Servicio de sugerencias de canciones por temperatura
Es una extensión del proyecto principal que incluye una interfaz de usuario para utilizar la API. Es importante mencionar que esta versión de la API tiene algunas diferencias con respecto a la original, entre ellas, la inclusión de las URL de las canciones recomendadas. Además, al igual que la API principal, esta extensión no cuenta con un control de acceso.

## Requisitos:
- Para poder utilizar este programa, es necesario que su sistema operativo tenga instalado Java 11 o una versión más reciente. Si no cuenta con Java, puede descargar e instalar la versión correspondiente, por ejemplo desde la página de: https://adoptium.net/temurin/releases/.

- Es imprescindible contar con una base de datos PostgreSQL, en la carpeta **Base_Datos** esta el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/Base_Datos/schemaAPI.sql">schemaAPIl.sql</a> con los comandos para crear las tablas correspondientes. 

- Cambia el usuario y contraseña al igual que el nombre de la base de datos en el archivo <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/src/main/resources/application-dev.properties">application-dev.properties</a> que esta en la ruta **src/main/resources**.

- Modificar el archivo llamado <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/src/main/resources/service.properties">service.properties</a> de la carpeta **src/main/resources** donde tienes que poner los valores solicitados para la autenticacion. 

- Asegúrese de tener esta información correcta y actualizada antes de ejecutar el programa.

## Inicializacion
Para utilizar este proyecto, se pueden seguir una las siguientes instrucciones:


### Descarga el proyecto comprimido en .zip
1. Descarga el proyecto en formato .zip desde la opción "Code" de este repositorio.
2. Una vez descargado, descomprime el archivo y abre la carpeta del proyecto.
3. Modifica el archivo **service.properties** que se encuentra en la ruta **src/main/resources**, agregando los datos necesarios para la autenticación hacia las APIs que vayas a utilizar. Debes poner tus propios datos de autenticación y las URLs necesarias. 
4. Implementar la base de datos postgresql corespondiente 
   - Puedes cambiar el nombre de la base de datos.
   - En el directorio **Base_Datos** hay un archivo llamado **schemaAPI.sql** con los comandos para crear la tabla necesaria, (esa tabla se debe de llamar igual)
5. Abra una terminal y ejecute este comando para cambiarte de rama:
     ```  
       git checkout Frontend 
     ```
6. Ejecute la aplicación de java (API), utilizando el comando:
     ```  
       mvn spring-boot:run  
     ```
     Este comando iniciará la aplicación utilizando Maven y Spring Boot. Una vez que la aplicación esté corriendo
 7. Muevace a la carpeta **Frontend** una vez ahi utilizando el comando:
     ```  
       npm install  
     ```
8. Finalmente, ejecute el comando 
     ```  
       ng serve   
     ```
     Ahora ya podrás comenzar a utilizarla en la url: **http://localhost:4200/**.

### Clona el repositorio
1. Clona el repositorio de Git en tu PC con el siguiente comando:
    ```  
       git clone git@github.com:EdwinC27/Microcontrolador.git  
    ```
2. Una vez descargado, abre la carpeta del proyecto
3. Modifica el archivo **service.properties** que se encuentra en la ruta **src/main/resources**, agregando los datos necesarios para la autenticación hacia las APIs que vayas a utilizar. Debes poner tus propios datos de autenticación y las URLs necesarias. 
5. Abra una terminal y ejecute este comando para cambiarte de rama:
     ```  
       git checkout Frontend 
     ```
6. Ejecute la aplicación de java (API), utilizando el comando:
     ```  
       mvn spring-boot:run  
     ```
     Este comando iniciará la aplicación utilizando Maven y Spring Boot. Una vez que la aplicación esté corriendo
 7. Muevace a la carpeta **Frontend** una vez ahi utilizando el comando:
     ```  
       npm install  
     ```
8. Finalmente, ejecute el comando 
     ```  
       ng serve   
     ```
     Ahora ya podrás comenzar a utilizarla en la url: **http://localhost:4200/**.
