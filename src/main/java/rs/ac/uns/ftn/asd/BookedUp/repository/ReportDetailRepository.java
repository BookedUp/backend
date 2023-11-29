package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReportDetailRepository implements IReportDetailRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, StatisticsDetail> reportDetails = new ConcurrentHashMap<>();

    @Override
    public Collection<StatisticsDetail> getAll() {
        return this.reportDetails.values();
    }

    @Override
    public StatisticsDetail create(StatisticsDetail reportDetail) {
        Long id = reportDetail.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            reportDetail.setId(id);
        }

        this.reportDetails.put(id, reportDetail);
        return reportDetail;
    }

    @Override
    public StatisticsDetail getById(Long id) {
        return this.reportDetails.get(id);
    }

    @Override
    public StatisticsDetail update(StatisticsDetail reportDetail) {
        Long id = reportDetail.getId();

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

