package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;
import rs.ac.uns.ftn.asd.BookedUp.repository.IUserReportRepository;

import java.util.Collection;

@Service
public class UserReportService implements IUserReportService {

    @Autowired
    private IUserReportRepository userReportRepository;

    @Override
    public Collection<UserReport> getAll() {
        return userReportRepository.getAll();
    }

    @Override
    public UserReport getById(Long id) {
        return userReportRepository.getById(id);
    }

    @Override
    public UserReport create(UserReport userReport) throws Exception {
        if (userReport.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        UserReport savedUserReport = userReportRepository.create(userReport);
        return savedUserReport;
    }

    @Override
    public UserReport update(UserReport userReport) throws Exception {
        UserReport userReportToUpdate = getById(userReport.getId());
        if (userReportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        userReportToUpdate.setReason(userReport.getReason());
        userReportToUpdate.setReportedUser(userReport.getReportedUser());
        userReportToUpdate.setStatus(userReport.isStatus());

        UserReport updatedUserReport = userReportRepository.update(userReportToUpdate);
        return updatedUserReport;
    }

    @Override
    public void delete(Long id) {
        userReportRepository.delete(id);
    }
}