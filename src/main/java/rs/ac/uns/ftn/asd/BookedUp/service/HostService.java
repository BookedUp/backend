package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.HostMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.HostRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class HostService implements IHostService{
    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private HostMapper hostMapper;
    @Override
    public Collection<HostDTO> getAll() {
        Collection<Host> hosts = hostRepository.getAll();
        Collection<HostDTO> hostDTOS = new ArrayList<>();

        for (Host host : hosts) {
            HostDTO hostDTO = hostMapper.toDto(host);
            hostDTOS.add(hostDTO);
        }

        return hostDTOS;
    }

    @Override
    public HostDTO getById(Long id) {
        Host host = hostRepository.getById(id);
        return hostMapper.toDto(host);
    }

    @Override
    public HostDTO create(HostDTO hostDto) throws Exception {
        if (hostDto.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Host host = hostMapper.toEntity(hostDto);
        Host savedHost = hostRepository.create(host);
        return hostMapper.toDto(savedHost);
    }

    @Override
    public HostDTO update(HostDTO hostDto) throws Exception {
        Host host = hostMapper.toEntity(hostDto);
        Host hostToUpdate = hostRepository.getById(host.getId());
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
        return hostMapper.toDto(updatedHost);
    }

    @Override
    public void delete(Long id) {
        hostRepository.delete(id);
    }



}
