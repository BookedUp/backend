package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReportDetail;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReportDetailRepository implements IReportDetailRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, ReportDetail> reportDetails = new ConcurrentHashMap<>();

    @Override
    public Collection<ReportDetail> getAll() {
        return this.reportDetails.values();
    }

    @Override
    public ReportDetail create(ReportDetail reportDetail) {
        Long id = reportDetail.getAccommodationId();

        if (id == null) {
            id = counter.incrementAndGet();
            reportDetail.setAccommodationId(id);
        }

        this.reportDetails.put(id, reportDetail);
        return reportDetail;
    }

    @Override
    public ReportDetail getById(Long id) {
        return this.reportDetails.get(id);
    }

    @Override
    public ReportDetail update(ReportDetail reportDetail) {
        Long id = reportDetail.getAccommodationId();

        if (id != null) {
            this.reportDetails.put(id, reportDetail);
        }

        return reportDetail;
    }

    @Override
    public void delete(Long id) {
        this.reportDetails.remove(id);
    }
}

