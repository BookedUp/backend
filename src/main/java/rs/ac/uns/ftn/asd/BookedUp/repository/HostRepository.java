package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class HostRepository implements IHostRepository{
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Host> hosts = new ConcurrentHashMap<Long, Host>();
    @Override
    public Collection<Host> getAll() {
        return this.hosts.values();
    }

    @Override
    public Host getById(Long id) {
        return this.hosts.get(id);
    }

    @Override
    public Host create(Host host) throws Exception {
        Long id = host.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            host.setId(id);
        }

        this.hosts.put(id, host);
        return host;
    }

    @Override
    public Host update(Host host) throws Exception {
        Long id = host.getId();

        if (id != null) {
            this.hosts.put(id, host);
        }

        return host;
    }

    @Override
    public void delete(Long id) {
        this.hosts.remove(id);
    }
}