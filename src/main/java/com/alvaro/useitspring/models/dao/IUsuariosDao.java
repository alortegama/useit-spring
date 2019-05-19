package com.alvaro.useitspring.models.dao;

import com.alvaro.useitspring.models.entity.Usuarios;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUsuariosDao extends PagingAndSortingRepository<Usuarios, String> {
	Usuarios findByUsername(String username);

	Boolean existsByUsername(String username);
}
