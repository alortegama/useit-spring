package com.alvaro.useitspring.models.service;

import com.alvaro.useitspring.models.dao.IAgendaDao;
import com.alvaro.useitspring.models.entity.Agenda;
import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IAgendaServiceImpl implements IAgendaService {
	@Autowired
	private IAgendaDao agendaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Agenda> findAll() {
		return (List<Agenda>) agendaDao.findAll(sortByDateAsc());
	}

	private Sort sortByDateAsc() {
		return new Sort(Sort.Direction.ASC, "date");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Agenda> findByDescription(String text) {
		return agendaDao.findAgendaByDescriptionIsContainingIgnoreCaseOrderByDateAsc(text);
	}

	@Override
	public Agenda save(Agenda agenda) {
		return agendaDao.save(agenda);
	}

	@Override
	@Transactional(readOnly = true)
	public Agenda findOne(Integer id) {
		return agendaDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		agendaDao.deleteById(id);
	}
}
