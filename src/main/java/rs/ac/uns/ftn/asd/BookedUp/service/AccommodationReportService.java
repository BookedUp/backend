package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationReport;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationReportRepository;

import java.util.Collection;

@Service
public class AccommodationReportService implements IAccommodationReportService{
    @Autowired
    private IAccommodationReportRepository accommodationReportRepository;

    @Override
    public Collection<AccommodationReport> getAll() {
        return accommodationReportRepository.getAll();
    }

    @Override
    public AccommodationReport getById(Long id) {
        return accommodationReportRepository.getById(id);
    }

    @Override
    public AccommodationReport create(AccommodationReport report) throws Exception {
        if (report.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        AccommodationReport savedReport = accommodationReportRepository.create(report);
        return savedReport;
    }

    @Override
    public AccommodationReport update(AccommodationReport reportDetail) throws Exception {
        AccommodationReport reportToUpdate = getById(reportDetail.getId());
        if (reportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        reportToUpdate.setAccommodation(reportDetail.getAccommodation());
        reportToUpdate.setYear(reportDetail.getYear());
        reportToUpdate.setDetails(reportDetail.getDetails());
        reportToUpdate.setProfit(reportDetail.getProfit());
        reportToUpdate.setNumberOfReservations(reportDetail.getNumberOfReservations());

        AccommodationReport updatedReport = accommodationReportRepository.update(reportToUpdate);
        return updatedReport;
    }

    @Override
    public void delete(Long id) {
        accommodationReportRepository.delete(id);
    }
}
