package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.ReportDetail;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;

import java.util.Collection;

public interface IReportDetailService {
    Collection<ReportDetail> getAll();
    ReportDetail getById(Long id);
    ReportDetail create(ReportDetail userReport) throws Exception;
    ReportDetail update(ReportDetail userReport) throws Exception;
    void delete(Long id);
}
