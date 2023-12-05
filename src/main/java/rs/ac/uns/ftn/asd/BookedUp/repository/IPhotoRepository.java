package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;

import java.util.Collection;

public interface IPhotoRepository extends JpaRepository<Photo, Long> {


}
