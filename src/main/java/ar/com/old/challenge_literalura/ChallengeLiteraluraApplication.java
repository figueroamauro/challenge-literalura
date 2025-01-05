package ar.com.old.challenge_literalura;

import ar.com.old.challenge_literalura.models.dto.BookDTO;
import ar.com.old.challenge_literalura.serviceAPI.GutendexService;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication {

	public static void main(String[] args)  {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
		GutendexService service = new GutendexService();
		Gson gson = new Gson();
		service.getByTitle("don quijote de la mancha").forEach(jsonElement -> {
			System.out.println(gson.fromJson(jsonElement, BookDTO.class));
		});

	}

}
