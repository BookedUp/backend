package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.repository.HostRepository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class HostService implements IHostService{
    @Autowired
    private HostRepository hostRepository;
    @Override
    public Collection<Host> getAll() {
        Collection<Host> hosts = hostRepository.getAll();
        return hosts;
    }

    @Override
    public Host getById(Long id) {
        Host host = hostRepository.getById(id);
        return host;
    }

    @Override
    public Host create(Host host) throws Exception {
        if (host.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Host savedHost = hostRepository.create(host);
        return savedHost;
    }

    @Override
    public Host update(Host host) throws Exception {
        Host hostToUpdate = getById(host.getId());
        if (hostToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        hostToUpdate.setFirstName(host.getFirstName());
        hostToUpdate.setLastName(host.getLastName());
        hostToUpdate.setAddress(host.getAddress());
        hostToUpdate.setEmail(host.getEmail());
        hostToUpdate.setPassword(host.getPassword());
        hostToUpdate.setPhone(host.getPhone());
        hostToUpdate.setRole(host.getRole());
        hostToUpdate.setBlocked(host.isBlocked());
        hostToUpdate.setAverageRating(host.getAverageRating());
        hostToUpdate.setProperties(host.getProperties());
        hostToUpdate.setNotifications(host.getNotifications());
        hostToUpdate.setRequests(host.getRequests());

        Host updatedHost = hostRepository.create(hostToUpdate);
        return updatedHost;
    }

    @Override
    public void delete(Long id) {
        hostRepository.delete(id);
    }
}
