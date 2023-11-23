package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Host;

import java.util.Collection;

public interface IHostRepository {
    Collection<Host> getAll();
    Host getById(Long id);
    Host create(Host host) throws Exception;
    Host update(Host host) throws Exception;
    void delete(Long id);
}
