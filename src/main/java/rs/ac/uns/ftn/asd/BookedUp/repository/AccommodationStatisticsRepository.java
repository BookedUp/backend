package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccommodationStatisticsRepository implements IAccommodationStatisticsRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, AccommodationStatistics> reports = new ConcurrentHashMap<>();

    @Override
    public Collection<AccommodationStatistics> getAll() {
        return this.reports.values();
    }

    @Override
    public AccommodationStatistics create(AccommodationStatistics report) {
        Long id = report.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            report.setId(id);
        }

        this.reports.put(id, report);
        return report;
    }

    @Override
    public AccommodationStatistics getById(Long id) {
        return this.reports.get(id);
    }

    @Override
    public AccommodationStatistics update(AccommodationStatistics report) {
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
