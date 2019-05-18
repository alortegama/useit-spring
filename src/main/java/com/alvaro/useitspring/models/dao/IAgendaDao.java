package com.alvaro.useitspring.models.dao;

import com.alvaro.useitspring.models.entity.Agenda;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAgendaDao extends PagingAndSortingRepository<Agenda, Integer> {
	List<Agenda> findAgendaByDescriptionIsContainingOrderByDataAsc(@Param("text") String text);

}
