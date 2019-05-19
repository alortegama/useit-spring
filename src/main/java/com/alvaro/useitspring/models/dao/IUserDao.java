package com.alvaro.useitspring.models.dao;

import com.alvaro.useitspring.models.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserDao extends PagingAndSortingRepository<User, String> {
	User findByUsername(String username);

	Boolean existsByUsername(String username);
}
