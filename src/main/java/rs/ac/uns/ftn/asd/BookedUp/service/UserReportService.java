package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReviewReportMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserReportMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IUserReportRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserReportService implements IUserReportService {

    @Autowired
    private IUserReportRepository userReportRepository;
    @Autowired
    private UserReportMapper userReportMapper;


    @Override
    public Collection<UserReportDTO> getAll() {
        Collection<UserReport> userReports = (userReportRepository.getAll());
        Collection<UserReportDTO> userReportDTOS = new ArrayList<>();

        for (UserReport userReport : userReports) {
            UserReportDTO userReportDTO = userReportMapper.toDto(userReport);
            userReportDTOS.add(userReportDTO);
        }

        return userReportDTOS;
    }
    @Override
    public UserReportDTO getById(Long id) {
        UserReport userReport =  userReportRepository.getById(id);
        return userReportMapper.toDto(userReport);
    }

    @Override
    public UserReportDTO create(UserReportDTO userReportDTO) throws Exception {
        if (userReportDTO.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        UserReport userReport = userReportMapper.toEntity(userReportDTO);
        UserReport createdUserReport =  userReportRepository.create(userReport);
        return userReportMapper.toDto(createdUserReport);
    }

    @Override
    public UserReportDTO update(UserReportDTO userReportDTO) throws Exception {
        UserReport userReport = userReportMapper.toEntity(userReportDTO);
        UserReport userReportToUpdate= userReportRepository.getById(userReport.getId());
        if (userReportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        userReportToUpdate.setReason(userReport.getReason());
        userReportToUpdate.setReportedUser(userReport.getReportedUser());
        userReportToUpdate.setStatus(userReport.isStatus());

        UserReport updatedUserreport = userReportRepository.update(userReportToUpdate);
        return userReportMapper.toDto(updatedUserreport);
    }

    @Override
    public void delete(Long id) {
        userReportRepository.delete(id);
    }
}