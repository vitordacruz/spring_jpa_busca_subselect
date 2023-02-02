package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Autor;

public interface AutorRepositoryCustom {
	List<Autor> buscarAutorPorNomeLivro(String nomeLivro);
	List<Autor> buscarAutorPorNomeLivroComJoin(String nomeLivro);
}
