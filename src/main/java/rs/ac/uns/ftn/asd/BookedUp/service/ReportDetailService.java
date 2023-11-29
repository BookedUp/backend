package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReportDetailRepository;

import java.util.Collection;

@Service
public class ReportDetailService implements IReportDetailService{
    @Autowired
    private IReportDetailRepository reportDetailRepository;

    @Override
    public Collection<StatisticsDetail> getAll() {
        return reportDetailRepository.getAll();
    }

    @Override
    public StatisticsDetail getById(Long id) {
        return reportDetailRepository.getById(id);
    }

    @Override
    public StatisticsDetail create(StatisticsDetail reportDetail) throws Exception {
        if (reportDetail.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        StatisticsDetail savedReportDetail = reportDetailRepository.create(reportDetail);
        return savedReportDetail;
    }

    @Override
    public StatisticsDetail update(StatisticsDetail reportDetail) throws Exception {
        StatisticsDetail ReportDetailToUpdate = getById(reportDetail.getId());
        if (ReportDetailToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        ReportDetailToUpdate.setProfit(reportDetail.getProfit());
        ReportDetailToUpdate.setNumberOfReservations(reportDetail.getNumberOfReservations());

        StatisticsDetail updatedReportDetail = reportDetailRepository.update(ReportDetailToUpdate);
        return updatedReportDetail;
    }

    @Override
    public void delete(Long id) {
        reportDetailRepository.delete(id);
    }
}
