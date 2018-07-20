package com.cg.springsercuritydemo.repositories;

import com.cg.springsercuritydemo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User , String> {




    @Override
    void delete(User deleted);
}
