package com.dgha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dgha.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {

}
