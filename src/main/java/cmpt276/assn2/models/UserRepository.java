package cmpt276.assn2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByGPA(double gpa);
    List<User> findByNameAndHeight(String name, String height);
}
