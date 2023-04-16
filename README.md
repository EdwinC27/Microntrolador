# Song suggestion service by temperature
It is an extension of the main project that includes a user interface to use the API. It is important to mention that this version of the API has some differences with respect to the original, including the inclusion of the URLs of the recommended songs. Additionally, like the main API, this extension does not have access control.

You can see more detailed information in these files:

You can see the API documentation: <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/documentacionArquitecturaAPI.md">here</a>

You can see the FrontEnd Documentation: <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/documentacionArquitecturaFront.md">here</a>


 ## Requirements:
- To use this program, it is necessary for your operating system to have Java 11 or a newer version installed. If you do not have Java, you can download and install the corresponding version, for example from the following website: https://adoptium.net/temurin/releases/.

- It is essential to have a PostgreSQL database, in the folder **Base_Datos** is the file <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/Base_Datos/schemaAPI.sql">schemaAPIl.sql</a> con los comandos para crear las tablas correspondientes. 

- Change the username and password as well as the database name in the file <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/src/main/resources/application-dev.properties">application-dev.properties</a> which is in the path **src/main/resources**.

- Modify the named file <a href="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/src/main/resources/service.properties">service.properties</a> in the **src/main/resources** folder where you have to put the requested values for authentication.

-  Make sure to have this information correct and updated before running the program.

## Initialization
To use this project, you can follow one of the following instructions:

### Download the project in .zip format
1. Download the project in .zip format from the "Code" option of this repository.
2. Once downloaded, unzip the file and open the project folder.
3. Modify the **service.properties** file located in **src/main/resources**, adding the necessary data for authentication to the APIs that you are going to use. You must put your own authentication data and the necessary URLs.
4. Implement the corresponding PostgreSQL database:
   - You can change the name of the database.
   - In the **Base_Datos** directory, there is a file called **schemaAPI.sql** with the commands to create the necessary table (that table must have the same name).

5. Open a terminal and execute this command to change to the Frontend branch:
     ```  
       git checkout Frontend 
     ```
6. Run the Java application (API) using the command:
     ```  
       mvn spring-boot:run  
     ```
   This command will start the application using Maven and Spring Boot. 
   
 7. Move to the **Frontend** folder and once there use the command:
     ```  
       npm install  
     ```
8. Finally, run the command:
     ```  
       ng serve   
     ```
   Now you can start using it at http://localhost:4200/.


### Clone the repository
1. Clone the Git repository on your PC with the following command:
    ```  
       git clone git@github.com:EdwinC27/Microcontrolador.git  
    ```
2. Once downloaded, open the project folder.
3. Modify the **service.properties** file located in **src/main/resources**, adding the necessary data for authentication to the APIs that you are going to use. You must put your own authentication data and the necessary URLs.
4. Implement the corresponding PostgreSQL database:
   - You can change the name of the database.
   - In the **Base_Datos** directory, there is a file called **schemaAPI.sql** with the commands to create the necessary table (that table must have the same name).

5. Open a terminal and execute this command to change to the Frontend branch:
     ```  
       git checkout Frontend 
     ```
6. Run the Java application (API) using the command:
     ```  
       mvn spring-boot:run  
     ```
   This command will start the application using Maven and Spring Boot. 
   
 7. Move to the **Frontend** folder and once there use the command:
     ```  
       npm install  
     ```
8. Finally, run the command:
     ```  
       ng serve   
     ```
   Now you can start using it at http://localhost:4200/.
