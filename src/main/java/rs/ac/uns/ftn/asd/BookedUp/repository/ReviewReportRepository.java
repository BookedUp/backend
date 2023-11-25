package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReviewReportRepository implements IReviewReportRepository{
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, ReviewReport> reviewReports = new ConcurrentHashMap<>();

    @Override
    public Collection<ReviewReport> getAll() {
        return this.reviewReports.values();
    }

    @Override
    public ReviewReport create(ReviewReport reviewReport) {
        Long id = reviewReport.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            reviewReport.setId(id);
        }

        this.reviewReports.put(id, reviewReport);
        return reviewReport;
    }

    @Override
    public ReviewReport getById(Long id) {
        return this.reviewReports.get(id);
    }

    @Override
    public ReviewReport update(ReviewReport reviewReport) {
        Long id = reviewReport.getId();

        if (id != null) {
            this.reviewReports.put(id, reviewReport);
        }

        return reviewReport;
    }

    @Override
    public void delete(Long id) {
        this.reviewReports.remove(id);
    }
}
