package ar.com.old.challenge_literalura;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.models.dto.BookDTO;
import ar.com.old.challenge_literalura.models.mapers.BookMapper;
import ar.com.old.challenge_literalura.api.GutendexService;
import ar.com.old.challenge_literalura.view.Menu;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication {

	public static void main(String[] args)  {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
//		GutendexService service = new GutendexService();
//		Gson gson = new Gson();
//		service.getByTitle("don quijote de la mancha").forEach(jsonElement -> {
//			BookDTO dto = gson.fromJson(jsonElement, BookDTO.class);
//			Book book = BookMapper.map(dto);
//			System.out.println(book);
//		});
		Menu.printBanner();

	}

}
