package org.launchcode.hiitworkoutbuilder.models.data;

import org.launchcode.hiitworkoutbuilder.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
