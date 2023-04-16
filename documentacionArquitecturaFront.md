# Ducumentation 
The documentation of this application includes a mockup, which can be viewed in the following image:
<img src="https://github.com/EdwinC27/Microcontrolador/blob/Frontend/Boceto%20de%20FrontEnd.png">

## package "buscar-canciones" 

### buscar-canciones.component.html
This code represents a section of the user interface of a web application that allows users to search for Spotify songs by city name. It contains an image of the Spotify logo, a text input field for the user to enter the city name, a search button that is enabled when the user enters text in the input field, and a custom application component to display the results of the Spotify song search in the specified city.

### buscar-canciones.component.ts
This code defines an Angular component called BuscarCancionesComponent, which is responsible for searching for Spotify songs by city name. The component has a property to store the search text, a property to enable or disable the search button, a decorator to obtain a reference to the MostrarCancionesComponent component, and a method that calls the imprimir() method of the MostrarCancionesComponent component to display the search results in the user interface.



## package "mostrar-canciones" 

### mostrar-canciones.component.html
This code represents a section of the user interface of a web application that displays information about the selected city and a list of songs found for that city. The *ngFor structural directive is used to iterate over the list of songs and display information about each one, such as the title, artist, and album. Additionally, the *ngIf directive is used to display a message if no song search has been performed for the selected city.

### mostrar-canciones.component.ts
This code represents an Angular component called MostrarCancionesComponent, which is used to display a list of songs and information about the selected city. An instance of the CancionesService service is used to obtain information about the city and the list of songs. If the call is successful, the data is assigned to the city and songs properties of the component. This component is used in the BuscarCancionesComponent component using the app-mostrar-canciones directive.

### mostrar-canciones.model.ts
This code defines a TypeScript interface called ApiResponse. The interface describes the structure of response data received from some API. The interface has two properties: city, which is an object containing the city's coordinates, name, temperature, and a Spotify playlist associated with the city; and songs, which is an array of objects containing information about the songs, such as the song name, artist name, album, and the song's URL on Spotify.

### mostrar-canciones.service.ts
This code defines a service called CancionesService, which is used to make HTTP requests to the server. The service has a method called getPeticionCanciones that makes an HTTP GET request to the server and returns the response. It also has a method called getInfo that calls the getPeticionCanciones method and uses the RxJS map operator to assign the response of the request to the songs and citys properties of the service. The class uses dependency injection to obtain an instance of HttpClient, which is used to make the HTTP request.
