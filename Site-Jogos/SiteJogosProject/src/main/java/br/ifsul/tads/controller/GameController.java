package br.ifsul.tads.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import br.ifsul.tads.domain.Game;

@Controller
@RequestMapping("/games")
public class GameController {

	private static ArrayList<Game> games = new ArrayList<>();
	static {
		Game g1 = new Game();
		g1.setTitle("GTA V");
		g1.setYear(2013);
		g1.setGenre("Mundo Aberto");
		g1.setCoverUrl("https://upload.wikimedia.org/wikipedia/pt/8/80/Grand_Theft_Auto_V_capa.png");
		games.add(g1);

		Game g2 = new Game();
		g2.setTitle("Farcry V");
		g2.setYear(2014);
		g2.setGenre("Mundo Aberto");
		g2.setCoverUrl("https://upload.wikimedia.org/wikipedia/pt/9/94/Far_cry_5_cover.jpg");
		games.add(g2);

		Game g3 = new Game();
		g3.setTitle("Bom Esponja");
		g3.setYear(2006);
		g3.setGenre("Infantil");
		g3.setCoverUrl("https://image.api.playstation.com/vulcan/ap/rnd/202007/3122/W5DMW31LjfCHMdHUzbzXqA3H.jpg");
		games.add(g3);

		Game g4 = new Game();
		g4.setTitle("Crash");
		g4.setYear(2005);
		g4.setGenre("Aventura");
		g4.setCoverUrl("https://image.api.playstation.com/cdn/UP0002/CUSA07402_00/03ZtrPdjasIxzi8QrzOb2zCIHLMydFbh.png");
		games.add(g4);

		Game g5 = new Game();
		g5.setTitle("Shrek Smash n' Crash");
		g5.setYear(2004);
		g5.setGenre("Corrida");
		g5.setCoverUrl("https://upload.wikimedia.org/wikipedia/en/a/ae/Shrek_Smash_n_Crash_Racing.jpg");
		games.add(g5);

		Game g6 = new Game();
		g6.setTitle("Forza V");
		g6.setYear(2014);
		g6.setGenre("Corrida");
		g6.setCoverUrl("https://store-images.s-microsoft.com/image/apps.49800.13718773309227929.bebdcc0e-1ed5-4778-8732-f4ef65a2f445.9ac09d39-064d-466c-81ca-2f1b6f0b95c5");
		games.add(g6);

		Game g7 = new Game();
		g7.setTitle("FIFA 25");
		g7.setYear(2025);
		g7.setGenre("Futebol");
		g7.setCoverUrl("https://image.api.playstation.com/vulcan/ap/rnd/202503/2520/f3c135f8ebdc50b782c6f5f02b27130b499e22847f05aee6.png");
		games.add(g7);

		Game g8 = new Game();
		g8.setTitle("Dragon Ball Z: Budokai Tenkaichi 3");
		g8.setYear(2003);
		g8.setGenre("Luta");
		g8.setCoverUrl("https://static.wikia.nocookie.net/dragonball/images/5/5a/Capa-dbz-sparking%21-meteor.webp/revision/latest?cb=20240612001520&path-prefix=pt-br");
		games.add(g8);

		Game g9 = new Game();
		g9.setTitle("Batman Lego");
		g9.setYear(2006);
		g9.setGenre("Lego game");
		g9.setCoverUrl("https://cdn2.unrealengine.com/Diesel%2Fproductv2%2Flego-batman%2FEGS_WB_LEGO_Batman_G1_1920x1080_19_0911-1920x1080-e166b698acbbbcdae1ff306198684d143828467c.jpg");
		games.add(g9);
	}

	

	@GetMapping()
	public String getMethodName(Model model) {

		model.addAttribute("game", new Game());

		return "game_add";
	}

	@PostMapping()
	public String insert(@ModelAttribute Game game) {

		games.add(game);
		
		return "game_status";
	}
	
	// comentado pq foi adicionado o delete mapping
	// @GetMapping("/delete")
	// public String remove2(@RequestParam("title") String titulo, Model model) {
		
	// 	System.out.println("removendo " + titulo);

	// 	for(int i=0;i<games.size();i++) {
	// 		games.get(i).getTitle();
	// 		if(games.get(i).getTitle().equals(titulo)) {
	// 			games.remove(i);			
	// 			break;
	// 		} else {
	// 			System.out.println("Jogo não encontrado");
	// 		}
	// 	}
	// 	model.addAttribute("games", games);

		

		
	// 	return "game_list";
	// }
	// ai help
	@GetMapping("/detail")
	public String detail(@RequestParam("title") String titulo, Model model) {
		for (Game g : games) {
			if (g.getTitle().equals(titulo)) {
				model.addAttribute("game", g);
				break;
			}
		}
		return "game_detail";
	}

	@GetMapping("/edit")
	public String editForm(@RequestParam("title") String titulo, Model model) {
		for (Game g : games) {
			if (g.getTitle().equals(titulo)) {
				model.addAttribute("game", g);
				model.addAttribute("originalTitle", titulo);
				break;
			}
		}
		return "game_edit";
	}

	@PostMapping("/edit")
	public String editSave(@RequestParam("originalTitle") String originalTitle,
						   @ModelAttribute Game updated) {
		for (Game g : games) {
			if (g.getTitle().equals(originalTitle)) {
				g.setTitle(updated.getTitle());
				g.setYear(updated.getYear());
				g.setGenre(updated.getGenre());
				g.setCoverUrl(updated.getCoverUrl());
				break;
			}
		}
		return "redirect:/games/detail?title=" + updated.getTitle();
	}

	@PutMapping("/{title}")
	public ResponseEntity<String> putGame(@PathVariable String title, @ModelAttribute Game updated) {
		for (Game g : games) {
			if (g.getTitle().equals(title)) {
				g.setTitle(updated.getTitle());
				g.setYear(updated.getYear());
				g.setGenre(updated.getGenre());
				g.setCoverUrl(updated.getCoverUrl());
				return ResponseEntity.ok("Jogo atualizado com sucesso.");
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado.");
	}

	@PostMapping("/delete")
	public String deleteForm(@RequestParam("title") String titulo) {
		games.removeIf(game -> game.getTitle().equals(titulo));
		return "redirect:/games/list";
	}

	@GetMapping("list")
	public String list(Model model) {

		model.addAttribute("games", games);

		return "game_list";
	}

	@DeleteMapping("/{title}")
public ResponseEntity<String> deleteGame(@PathVariable String title) {
    boolean removed = games.removeIf(game -> game.getTitle().equals(title));
    if (removed) {
        return ResponseEntity.ok("Jogo removido com sucesso.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogo não encontrado.");
    }
}
}
