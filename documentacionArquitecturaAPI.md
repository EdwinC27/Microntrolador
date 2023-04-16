# Documentation         
 
This web service implements a RESTful API that accepts requests containing either the name of a city or longitude and latitude coordinates as parameters, and returns a Spotify playlist with songs that match the temperature of the location.


## Using the application:
This service uses the OpenWeatherMap and Spotify APIs to obtain the current temperature of the specified location and the recommended songs based on the temperature. Data is cached to reduce requests to the APIs. OpenWeatherMap data is updated every 3 minutes and cached during that period, while Spotify data is updated every 12 hours and cached during that time. After obtaining data from both APIs, relevant information such as request time, musical genre, city, and recommended songs is stored in a PostgreSQL database.

<img src="https://github.com/EdwinC27/Microcontrolador/blob/main/diagramaSecuencia.png">


### To use the service, requests can be sent through two URLs:

> http://localhost:8080/api/temperatura/?ciudad=city-name: to get the temperature of a particular city.

> http://localhost:8080/api/temperatura/?latitud=latitude-value&longitud=longitude-value: to get the temperature of a specific location based on its longitude and latitude coordinates.

### To explore the API documentation, the following URLs can be used:

> http://localhost:8080/v3/api-docs: to access the OpenAPI specification in JSON format.

> http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config: to access the Swagger UI user interface.

# Details of each package
 
## Package "servicios"

* The implementation of Conector_OpenWeatherMap is used to obtain the current temperature of a city or geographic coordinates by connecting to the OpenWeatherMap API. It has two main methods, "getURLCiudad" and "getURLCordenada," which are attempted to be executed up to a maximum of 3 times. It also uses caching to avoid repetitive requests and two instance variables to hold the API key and base URL for making requests.


* The implementation of Llave_Spotify obtains an access token by authenticating a client with their credentials. It verifies if the current token is valid to obtain a new one if necessary. The main method of the class is "getToken()". It also uses RestTemplate to make an HTTP POST request to the Spotify authorization URL.


* The implementation of Conector_Spotify uses methods to make requests to the Spotify API and returns results based on temperature and musical genre. It also uses an instance of the Llave_Spotify class to obtain an access token to the API. It contains methods to process the API response and extract song information.

* The implementation of General_Json has two methods. The first method, infoRegresada, creates a JSON object and adds information about the city and its temperature, coordinates, and playlist name to a JSON object cityLista. It also adds information about the songs to a JSON array songsListas. The second method, erroGenerado, creates a JSON object that contains information about the error that occurred.



## "controllers" package
* The implementation of "Consulta" is code that defines a REST controller to get the temperature and recommended songs for a city or geographic coordinates. The "Consulta" controller is annotated with "@RestController" and has three autowired fields: "Conector_OpenWeatherMap", "RepositorioListaMusica", and "Conector_Spotify". The "getTemperatura" method is a REST controller annotated with "@GetMapping" and accessible at the "/api/temperatura" path. It takes three optional parameters ("ciudad", "latitud", and "longitud") and returns a JSON object with information about the temperature and recommended songs. The method checks the internet connection, and if it's successful, determines if a city or geographic coordinates are provided. If a city is provided, it obtains the temperature of the city using "getURLCiudad" from the "Conector_OpenWeatherMap" object, and then generates a list of recommended songs for the temperature with "peticionGenero" from the "Conector_Spotify" object. The method also saves the city information in a database. If geographic coordinates are provided, it performs the same steps. The "guardarMiEntidad" method saves the temperature, city, and recommended songs information in a "ListaMusica" object that is saved in the database using the "RepositorioListaMusica" object.



## "configurations" package
* The implementation of "ComprobarConexionJava" has a method called "conexion()" that returns a boolean indicating whether there is an internet connection or not. This method creates a Socket object to attempt to connect to a server at the specified web address and port. If the connection is successful, it returns "true", otherwise, it returns "false".

* The implementation of "OpenWeatherMapCache" is a Spring component that uses the "@Component" and "@EnableScheduling" annotations to schedule tasks in the application. This class handles a cache of results obtained from the OpenWeatherMap API. The "limpiarCache" method runs every 3 minutes to clear old results and ensure that the most recent results from the API are obtained.

* The implementation of "SpotifyCache" is a Spring component that uses the "@EnableScheduling" annotation to schedule tasks in the application. This class handles a cache of results obtained from Spotify. The "limpiarCache" method runs every 12 hours to clear old results and ensure that cached results don't occupy unnecessary space in memory.

* The implementation of "ConfigCors" is the Spring configuration class that enables Cross-Origin Resource Sharing (CORS) policy in a Spring Boot web application. The CORS policy is a security measure implemented by web browsers to prevent malicious attacks on browsers, such as session cookie theft.


## "entities" package
* The implementation of "ListaMusica" is responsible for instantiating the fields of the "informacion_generada" table.


## "repositories" package
* The implementation of "RepositorioListaMusica" defines an interface that extends the Spring Data JPA "JpaRepository" interface and is used to perform CRUD (Create, Read, Update, Delete) operations on the database table corresponding to the "ListaMusica" entity.
