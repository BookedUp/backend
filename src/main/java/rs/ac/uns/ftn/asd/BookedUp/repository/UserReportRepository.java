package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserReportRepository implements IUserReportRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, UserReport> userReports = new ConcurrentHashMap<>();

    @Override
    public Collection<UserReport> getAll() {
        return this.userReports.values();
    }

    @Override
    public UserReport create(UserReport userReport) {
        Long id = userReport.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            userReport.setId(id);
        }

        this.userReports.put(id, userReport);
        return userReport;
    }

    @Override
    public UserReport getById(Long id) {
        return this.userReports.get(id);
    }

    @Override
    public UserReport update(UserReport userReport) {
        Long id = userReport.getId();

        if (id != null) {
            this.userReports.put(id, userReport);
        }

        return userReport;
    }

    @Override
    public void delete(Long id) {
        this.userReports.remove(id);
    }
}