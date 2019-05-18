package com.alvaro.useitspring.models.service;

		import com.alvaro.useitspring.models.entity.Agenda;
		import org.springframework.data.repository.query.Param;

		import java.util.List;

public interface IAgendaService {
	List<Agenda> findAll();

	Agenda save(Agenda cliente);

	Agenda findOne(Integer id);


	void delete(Integer id);

	List<Agenda> findByDescription(String text);
}
