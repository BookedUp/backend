package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserReportDTO;

import java.util.Collection;

public interface IUserReportService {

    Collection<UserReportDTO> getAll();
    UserReportDTO getById(Long id);
    UserReportDTO create(UserReportDTO userReportDTO) throws Exception;
    UserReportDTO update(UserReportDTO userReportDTO) throws Exception;
    void delete(Long id);

}

