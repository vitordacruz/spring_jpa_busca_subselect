package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.Autor;

public interface AutorRepository extends PagingAndSortingRepository<Autor, Integer>,
JpaRepository<Autor, Integer>, AutorRepositoryCustom {

}
