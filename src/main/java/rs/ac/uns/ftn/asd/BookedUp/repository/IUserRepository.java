package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;

import java.util.Collection;

public interface IUserRepository extends JpaRepository<User, Long> {



}
