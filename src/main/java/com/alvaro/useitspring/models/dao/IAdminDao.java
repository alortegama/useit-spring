package com.alvaro.useitspring.models.dao;

import com.alvaro.useitspring.models.entity.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAdminDao extends PagingAndSortingRepository<Admin, String> {
	Admin findByUsername(String username);

	Boolean existsByUsername(String username);
}
