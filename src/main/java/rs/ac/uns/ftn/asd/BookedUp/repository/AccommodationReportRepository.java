package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationReport;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccommodationReportRepository implements IAccommodationReportRepository{
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, AccommodationReport> reports = new ConcurrentHashMap<>();

    @Override
    public Collection<AccommodationReport> getAll() {
        return this.reports.values();
    }

    @Override
    public AccommodationReport create(AccommodationReport report) {
        Long id = report.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            report.setId(id);
        }

        this.reports.put(id, report);
        return report;
    }

    @Override
    public AccommodationReport getById(Long id) {
        return this.reports.get(id);
    }

    @Override
    public AccommodationReport update(AccommodationReport report) {
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
