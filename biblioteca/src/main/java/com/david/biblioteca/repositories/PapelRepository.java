package com.david.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.biblioteca.models.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    
    Papel findByPapel (String papel);
}
