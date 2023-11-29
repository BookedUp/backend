package rs.ac.uns.ftn.asd.BookedUp.repository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReportRepository implements IReportRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Statistics> reports = new ConcurrentHashMap<>();

    @Override
    public Collection<Statistics> getAll() {
        return this.reports.values();
    }

    @Override
    public Statistics create(Statistics report) {
        Long id = report.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            report.setId(id);
        }

        this.reports.put(id, report);
        return report;
    }

    @Override
    public Statistics getById(Long id) {
        return this.reports.get(id);
    }

    @Override
    public Statistics update(Statistics report) {
        Long id = report.getId();

        if (id != null) {
            this.reports.put(id, report);
        }

        return report;
    }

    @Override
    public void delete(Long id) {
        this.reports.remove(id);
    }
}


