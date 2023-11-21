package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;

import java.util.Collection;

public interface IUserReportRepository {
    Collection<UserReport> getAll();
    UserReport create(UserReport userReport);
    UserReport getById(Long id);
    UserReport update(UserReport userReport);
    void delete(Long id);
}
