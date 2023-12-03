package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Admin;
import rs.ac.uns.ftn.asd.BookedUp.dto.AdminDTO;

import java.util.Collection;

public interface IAdminService {
    Collection<AdminDTO> getAll();
    AdminDTO getById(Long id);
    AdminDTO create(AdminDTO adminDTO) throws Exception;
    AdminDTO update(AdminDTO adminDTO) throws Exception;
    void delete(Long id);

}
