package com.alvaro.useitspring.controller;

import com.alvaro.useitspring.models.entity.Agenda;
import com.alvaro.useitspring.models.service.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AgendaRestController {
	@Autowired
	private IAgendaService agendaService;

	@GetMapping("/agenda")
	public List<Agenda> index() {
		return agendaService.findAll();
	}

	@GetMapping("/agenda/{id}")
	public Agenda show(@PathVariable Integer id) {
		return agendaService.findOne(id);
	}

	@PostMapping("/crear-agenda")
	@ResponseStatus(HttpStatus.CREATED)
	public Agenda create(@RequestBody Agenda agenda) {
		System.out.println(agenda);
		return agendaService.save(agenda);
	}

	@GetMapping("agenda/search/findByDescriptionContaining")
	public List<Agenda> buscar(@RequestParam("text") String text) {
		return agendaService.findByDescription(text);
	}
}
