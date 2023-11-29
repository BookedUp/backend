package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReportRepository;

import java.util.Collection;

@Service
public class ReportService implements IReportService{
    @Autowired
    private IReportRepository reportRepository;

    @Override
    public Collection<Statistics> getAll() {
        return reportRepository.getAll();
    }

    @Override
    public Statistics getById(Long id) {
        return reportRepository.getById(id);
    }

    @Override
    public Statistics create(Statistics report) throws Exception {
        if (report.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        Statistics savedReport = reportRepository.create(report);
        return savedReport;
    }

    @Override
    public Statistics update(Statistics reportDetail) throws Exception {
        Statistics reportToUpdate = getById(reportDetail.getId());
        if (reportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        reportToUpdate.setFromDate(reportDetail.getFromDate());
        reportToUpdate.setToDate(reportDetail.getToDate());
        reportToUpdate.setDetails(reportDetail.getDetails());
        reportToUpdate.setProfit(reportDetail.getProfit());
        reportToUpdate.setNumberOfReservations(reportDetail.getNumberOfReservations());

        Statistics updatedReport = reportRepository.update(reportToUpdate);
        return updatedReport;
    }

    @Override
    public void delete(Long id) {
        reportRepository.delete(id);
    }
}
