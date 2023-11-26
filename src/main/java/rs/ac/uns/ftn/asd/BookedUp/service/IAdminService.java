package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Admin;

import java.util.Collection;

public interface IAdminService {
    Collection<Admin> getAll();
    Admin getById(Long id);
    Admin create(Admin admin) throws Exception;
    Admin update(Admin admin) throws Exception;
    void delete(Long id);

}
