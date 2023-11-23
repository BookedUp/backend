package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;

import java.util.Collection;

public interface IUserReportService {

    Collection<UserReport> getAll();
    UserReport getById(Long id);
    UserReport create(UserReport userReport) throws Exception;
    UserReport update(UserReport userReport) throws Exception;
    void delete(Long id);

}

