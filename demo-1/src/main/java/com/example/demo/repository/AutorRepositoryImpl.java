package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Autor;
import com.example.demo.model.Livro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class AutorRepositoryImpl implements AutorRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Autor> buscarAutorPorNomeLivro(String nomeLivro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Autor> criteriaQuery = 
				  criteriaBuilder.createQuery(Autor.class);
		Root<Autor> rootAutor = criteriaQuery.from(Autor.class);
		
		Subquery<Integer> subquery = criteriaQuery.subquery(Integer.class);
		Root<Livro> rootLivro = subquery.from(Livro.class);
		
		Predicate predicate = criteriaBuilder.like(
				criteriaBuilder.upper(rootLivro.get("nome")), 
				"%" + nomeLivro.toUpperCase() + "%"
				);
		
		subquery.select(rootLivro.get("id")).distinct(true)
			.where(predicate);
		
		criteriaQuery.select(rootAutor).distinct(true).where(criteriaBuilder.in(rootAutor.get("livros")
				.get("id")).value(subquery));
		
		TypedQuery<Autor> query = entityManager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}

}
