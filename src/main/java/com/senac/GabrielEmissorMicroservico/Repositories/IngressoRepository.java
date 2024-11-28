package com.senac.GabrielEmissorMicroservico.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senac.GabrielEmissorMicroservico.Entities.Ingresso;

import jakarta.transaction.Transactional;

@Repository
public interface IngressoRepository extends JpaRepository <Ingresso, Integer>{
	
	@Query("SELECT a from Ingresso a WHERE a.status != -1")
	List<Ingresso> buscarIngressos();
	
	@Query("SELECT a from Ingresso a WHERE a.id = :id AND a.status != -1")
	Optional<Ingresso> buscarIngressosPorId(int id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Ingresso a SET a.status = -1 WHERE a.id = :id")
	void apagarIngressoPorId(@Param("id") Integer id);

}
