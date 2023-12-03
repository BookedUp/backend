package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;

import java.util.Collection;
import java.util.Date;

public interface IHostService {
    Collection<HostDTO> getAll();
    HostDTO getById(Long id);
    HostDTO create(HostDTO hostDto) throws Exception;
    HostDTO update(HostDTO hostDto) throws Exception;
    void delete(Long id);
    boolean isWithinDateRange(Date date, Date fromDate, Date toDate);
}
