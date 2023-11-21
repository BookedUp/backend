package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Host;

import java.util.Collection;

public interface IHostService {
    Collection<Host> getAll();
    Host getById(Long id);
    Host create(Host host) throws Exception;
    Host update(Host host) throws Exception;
    void delete(Long id);
}
