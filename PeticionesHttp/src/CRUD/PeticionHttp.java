package CRUD;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PeticionHttp {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		HttpClient peticion = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
		
		URI url = URI.create("http://localhost:8090/api/v1/student/");
		URI urlUPDATE = URI.create("http://localhost:8090/api/v1/student/3?name=Roberto&email=bb@mgail.com");
		URI urldelete = URI.create("http://localhost:8090/api/v1/student/3?");
	    
	    String json = "{\"name\":\"Robert\",\"email\":\"Robert@gmail.com\",\"age\":33,\"date\":\"2020-10-08\"}";   

	    //Este método no es idempotente por tanto funciona bien la primera vez que se ejecuta el programa pero debe esta comentado la segunda. 
	    //De lo contrario hará un nuevo insert y fallará ya que el mail del json es el mismo
		HttpResponse<String> post = peticion.send(HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json)).uri(url)
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build(), HttpResponse.BodyHandlers.ofString()); 
		  
		HttpResponse<String> put = peticion.send(HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.noBody()).uri(urlUPDATE)
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build(), HttpResponse.BodyHandlers.ofString());
		
		HttpResponse<String> delete = peticion.send(HttpRequest.newBuilder().DELETE().uri(urldelete)
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build(), HttpResponse.BodyHandlers.ofString());
		
		HttpResponse<String> get = peticion.send(HttpRequest.newBuilder().GET().uri(url).build(), HttpResponse.BodyHandlers.ofString());
	   
	   
		System.out.println("Estado del código get: " +get.statusCode());
		
		System.out.println("Respuesta:");

		System.out.println(get.body());
		}
}
