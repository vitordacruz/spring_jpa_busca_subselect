package com.example.demo.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Autor;
import com.example.demo.repository.AutorRepository;

@RestController
@RequestMapping("/autor")
public class AutorController {

	private final AutorRepository repository;

	public AutorController(AutorRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping()
	public ResponseEntity<List<Autor>> findAll() {
		return ResponseEntity.ok(this.repository.findAll());
	}
	
	@GetMapping("/livro")
	public ResponseEntity<List<Autor>> buscaPorNomeLivro(@RequestParam String nomeLivro) {
		return ResponseEntity.ok(this.repository.buscarAutorPorNomeLivro(nomeLivro));
	}
	
	@GetMapping("/livro2")
	public ResponseEntity<List<Autor>> buscaPorNomeLivro2(@RequestParam String nomeLivro) {
		return ResponseEntity.ok(this.repository.buscarAutorPorNomeLivroComJoin(nomeLivro));
	}
}
