package com.senac.GabrielEmissorMicroservico.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.GabrielEmissorMicroservico.Entities.Ingresso;
import com.senac.GabrielEmissorMicroservico.Services.IngressoService;

@RestController
@RequestMapping(value="/ingresso")
public class IngressoController {
	
	private final IngressoService ingressoService;

	public IngressoController(IngressoService ingressoService) {
		this.ingressoService = ingressoService;
	}
	
	@GetMapping
	public ResponseEntity<List<Ingresso>> obterIngresso(){
		List<Ingresso> ingresso = ingressoService.buscarIngressos();
		return new ResponseEntity<>(ingresso, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")    
    public ResponseEntity<Optional<Ingresso>> getIngressoporId(@PathVariable int id){
        Optional<Ingresso> ingresso = ingressoService.buscarIngressosPorId(id);
        if(ingresso.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ingresso);
    }
	
	@PostMapping("")
	public ResponseEntity<Ingresso> adicionarIngresso(@RequestBody Ingresso ingresso){
		Ingresso novoIngresso = ingressoService.adicionarIngresso(ingresso);
		return new ResponseEntity<>(novoIngresso, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ingresso> atualizarIngresso(@RequestBody Ingresso ingresso, @PathVariable int id){
		Ingresso atualizarIngresso = ingressoService.atualizarIngresso(ingresso, id);
		if (atualizarIngresso != null) {
			return new ResponseEntity<>(atualizarIngresso, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirIngresso(@PathVariable("id") Integer id){
		ingressoService.apagarIngressoPorId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

