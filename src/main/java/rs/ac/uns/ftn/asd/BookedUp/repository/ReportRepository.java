package rs.ac.uns.ftn.asd.BookedUp.repository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Report;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReportRepository implements IReportRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Report> reports = new ConcurrentHashMap<>();

    @Override
    public Collection<Report> getAll() {
        return this.reports.values();
    }

    @Override
    public Report create(Report report) {
        Long id = report.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            report.setId(id);
        }

        this.reports.put(id, report);
        return report;
    }

    @Override
    public Report getById(Long id) {
        return this.reports.get(id);
    }

    @Override
    public Report update(Report report) {
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


