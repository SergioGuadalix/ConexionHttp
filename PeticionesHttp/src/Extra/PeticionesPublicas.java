package Extra;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PeticionesPublicas {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		URI url = URI.create("https://www.tomorrow.io/weather-api/");
		
		HttpClient peticion1 = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
		HttpClient peticion2 = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
		  
		//Peticion con HTTP 1_1
		HttpResponse<String> get1 = peticion1.send(HttpRequest.newBuilder().GET().uri(url).build(), HttpResponse.BodyHandlers.ofString());
		
		//Peticion con HTTP 2
		HttpResponse<String> get2 = peticion2.send(HttpRequest.newBuilder().GET().uri(url).build(), HttpResponse.BodyHandlers.ofString());
	   
	   
		System.out.println("Estado del código get: " +get1.statusCode());
		
		System.out.println("Respuesta:");

		System.out.println(get1.body());
		
		
		System.out.println("Estado del código get: " +get2.statusCode());
		
		System.out.println("Respuesta:");

		System.out.println(get2.body());
		}
}
