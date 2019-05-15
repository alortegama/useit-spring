package com.alvaro.useitspring.models.dao;

import com.alvaro.useitspring.models.entity.Agenda;
import org.springframework.data.repository.CrudRepository;

public interface IAgendaDao extends CrudRepository<Agenda, Integer> {
}
