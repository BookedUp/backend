package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.ReportDetail;

import java.util.Collection;

public interface IReportDetailRepository {

    Collection<ReportDetail> getAll();
    ReportDetail create(ReportDetail reportDetail);
    ReportDetail getById(Long id);
    ReportDetail update(ReportDetail reportDetail);
    void delete(Long id);

}
