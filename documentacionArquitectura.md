# Documentación         
 

## Paquete “servicios”

Las tres clases describen diferentes implementaciones de servicios que utilizan APIs para obtener información de
OpenWeatherMap y Spotify. OpenWeatherMap es utilizado para obtener la temperatura actual de una ubicación especifica,
mientras que Spotify se utiliza para obtener canciones basadas en la temperatura y el género musical.

La implementación de OpenWeatherMap utiliza un servicio en Java que se conecta a la API para obtener la temperatura 
actual de una ubicación y la almacena en un mapa para evitar solicitudes repetidas a la API.

La implementación de Llave_Spotify utiliza una clase que obtiene un token de acceso mediante la autenticación de un 
cliente con sus credenciales y verifica si el token actual está vigente para obtener un nuevo token si es necesario.

Por último, la implementación de Conector_Spotify utiliza métodos para hacer peticiones a la API de Spotify y devuelve 
resultados basados en la temperatura y el género musical. También utiliza una instancia de la clase Llave_Spotify para 
obtener un token de acceso a la API.

En resumen, las dos clases anteriores describen diferentes implementaciones de servicios que utilizan APIs para obtener 
información de OpenWeatherMap y Spotify, y que están diseñados para mejorar el rendimiento y la eficiencia en el acceso
a la información requerida.


## Paquete “controladores”
Esta clase define un controlador REST API en Spring Boot que expone una ruta '/api/temperatura' a través del método GET.
Los parámetros de consulta son 'ciudad' o latitud' y 'longitud' se pueden pasar a esta ruta para recuperar la temperatura 
actual de la ubicación deseada. La anotación '@RestController' indica que este controlador procesa solicitudes HTTP y
devuelve una respuesta HTTP.

El método 'getTemperatura' es responsable de procesar los parámetros de consulta y recuperar la temperatura. Si se proporciona
el parámetro 'ciudad', se llama al método 'getURLCiudad' de la clase 'Conector_OpenWeatherMap' para recuperar la temperatura 
actual de la ciudad especificada o por otro lado si se proporciona los parámetros ‘latitud’ y ‘longitud’ se llama al método 
‘getURLCordenada’ de la clase 'Conector_OpenWeatherMap' para recuperar la temperatura actual de las coordenadas especificadas.

Una vez que se recupera la temperatura, se llama al método 'peticionGenero' de la clase 'Conector_Spotify'. Esta clase proporciona 
un cliente para acceder al servicio web de Spotify y buscar canciones basadas en el género de música que corresponde a la temperatura
recuperada. El género se determina mediante un algoritmo específico que utiliza la temperatura como entrada.

Este controlador utiliza el caché de respuestas de las API OpenWeatherMap y Spotify. Posteriormente guarda los datos generados en la base de datos.


## Paquete “configuraciones” 
Este código define tres clases, dos de ellas se encargan de mantener en caché los resultados de las consultas a diferentes APIs para evitar 
hacer solicitudes innecesarias.

La primera clase, llamada OpenWeatherMapCache, define un atributo 'cacheResultados' que es un Map que almacena las temperaturas para cada 
ciudad y la clave es el nombre de la ciudad. Además, la clase utiliza la anotación @Scheduled para ejecutar una tarea periódica cada 3 minutos.

La segunda clase, llamada SpotifyCache, también define un atributo 'cacheResultados'que es un Map que almacena los resultados de las consultas a
la API de Spotify para cada canción y la clave es el nombre de la canción. Al igual que en la clase anterior, se utiliza la anotación @Scheduled 
para ejecutar una tarea periódica cada 12 horas. 

En resumen, ambas clases implementan la técnica de caché para reducir el número de solicitudes a las APIs y mejorar el rendimiento de la aplicación.
La última clase llamada “ComprobarConexionJava” se encarga de verificar si el dispositivo tiene conexión a internet.



## Paquete “entidades”
La clase “ListaMusica” se encarga de de instanciar los campos que tiene la base de datos al igual que el nombre de la misma.
