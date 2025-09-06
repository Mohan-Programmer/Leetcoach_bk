package leetcoach_BK.leetcoach.repositry;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import leetcoach_BK.leetcoach.model.Users;

public interface UserRepository extends MongoRepository<Users,String> {
Optional<Users> findByUserName(String UserName);
boolean existsByUserName(String UserName);

}
