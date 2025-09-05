package repositry;

import model.Users;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users,String> {
Optional<Users> findByUsername(String UserName);
boolean existByUsername(String UserName);

}
