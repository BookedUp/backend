package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Report;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReportRepository;

import java.util.Collection;

@Service
public class ReportService implements IReportService{
    @Autowired
    private IReportRepository reportRepository;

    @Override
    public Collection<Report> getAll() {
        return reportRepository.getAll();
    }

    @Override
    public Report getById(Long id) {
        return reportRepository.getById(id);
    }

    @Override
    public Report create(Report report) throws Exception {
        if (report.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        Report savedReport = reportRepository.create(report);
        return savedReport;
    }

    @Override
    public Report update(Report reportDetail) throws Exception {
        Report reportToUpdate = getById(reportDetail.getId());
        if (reportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        reportToUpdate.setFromDate(reportDetail.getFromDate());
        reportToUpdate.setToDate(reportDetail.getToDate());
        reportToUpdate.setDetails(reportDetail.getDetails());
        reportToUpdate.setProfit(reportDetail.getProfit());
        reportToUpdate.setNumberOfReservations(reportDetail.getNumberOfReservations());

        Report updatedReport = reportRepository.update(reportToUpdate);
        return updatedReport;
    }

    @Override
    public void delete(Long id) {
        reportRepository.delete(id);
    }
}
