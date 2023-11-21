package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReportDetail;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReportDetailRepository;

import java.util.Collection;

@Service
public class ReportDetailService implements IReportDetailService{
    @Autowired
    private IReportDetailRepository reportDetailRepository;

    @Override
    public Collection<ReportDetail> getAll() {
        return reportDetailRepository.getAll();
    }

    @Override
    public ReportDetail getById(Long id) {
        return reportDetailRepository.getById(id);
    }

    @Override
    public ReportDetail create(ReportDetail reportDetail) throws Exception {
        if (reportDetail.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        ReportDetail savedReportDetail = reportDetailRepository.create(reportDetail);
        return savedReportDetail;
    }

    @Override
    public ReportDetail update(ReportDetail reportDetail) throws Exception {
        ReportDetail ReportDetailToUpdate = getById(reportDetail.getId());
        if (ReportDetailToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        ReportDetailToUpdate.setAccommodationId(reportDetail.getAccommodationId());
        ReportDetailToUpdate.setProfit(reportDetail.getProfit());
        ReportDetailToUpdate.setNumberOfReservations(reportDetail.getNumberOfReservations());

        ReportDetail updatedReportDetail = reportDetailRepository.update(ReportDetailToUpdate);
        return updatedReportDetail;
    }

    @Override
    public void delete(Long id) {
        reportDetailRepository.delete(id);
    }
}
