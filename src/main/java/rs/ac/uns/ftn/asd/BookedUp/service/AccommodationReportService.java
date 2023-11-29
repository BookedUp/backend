package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationReportRepository;

import java.util.Collection;

@Service
public class AccommodationReportService implements IAccommodationReportService{
    @Autowired
    private IAccommodationReportRepository accommodationReportRepository;

    @Override
    public Collection<AccommodationStatistics> getAll() {
        return accommodationReportRepository.getAll();
    }

    @Override
    public AccommodationStatistics getById(Long id) {
        return accommodationReportRepository.getById(id);
    }

    @Override
    public AccommodationStatistics create(AccommodationStatistics report) throws Exception {
        if (report.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        AccommodationStatistics savedReport = accommodationReportRepository.create(report);
        return savedReport;
    }

    @Override
    public AccommodationStatistics update(AccommodationStatistics reportDetail) throws Exception {
        AccommodationStatistics reportToUpdate = getById(reportDetail.getId());
        if (reportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        reportToUpdate.setAccommodation(reportDetail.getAccommodation());
        reportToUpdate.setYear(reportDetail.getYear());
        reportToUpdate.setDetails(reportDetail.getDetails());
        reportToUpdate.setProfit(reportDetail.getProfit());
        reportToUpdate.setNumberOfReservations(reportDetail.getNumberOfReservations());

        AccommodationStatistics updatedReport = accommodationReportRepository.update(reportToUpdate);
        return updatedReport;
    }

    @Override
    public void delete(Long id) {
        accommodationReportRepository.delete(id);
    }
}
