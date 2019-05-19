package com.alvaro.useitspring;

import com.alvaro.useitspring.models.dao.IAgendaDao;
import com.alvaro.useitspring.models.entity.Agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class UseitSpringApplication implements CommandLineRunner {
	@Autowired
	IAgendaDao agendaDao;

	public static void main(String[] args) {
		SpringApplication.run(UseitSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Agenda agenda = new Agenda();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "25-11-1993 10:20:56";
		try {
			agenda.setDate(sdf.parse(dateInString));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		agenda.setDescription("Cumple Alvaro");
		agenda.setLocation("Andújar (Jaén)");

		agendaDao.save(agenda);

	}
}
