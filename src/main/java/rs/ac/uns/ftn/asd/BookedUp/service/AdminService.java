package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Admin;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.repository.AdminRepository;

import java.util.Collection;

@Service
public class AdminService implements IAdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Collection<Admin> getAll() {
        Collection<Admin> admins = adminRepository.getAll();
        return admins;
    }

    @Override
    public Admin getById(Long id) {
        Admin admin = adminRepository.getById(id);
        return admin;
    }

    @Override
    public Admin create(Admin admin) throws Exception {
        if (admin.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        Admin savedAdmin = adminRepository.create(admin);
        return savedAdmin;
    }

    @Override
    public Admin update(Admin admin) throws Exception {
        Admin adminToUpdate = getById(admin.getId());
        if (adminToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }

        adminToUpdate.setFirstName(admin.getFirstName());
        adminToUpdate.setLastName(admin.getLastName());
        adminToUpdate.setAddress(admin.getAddress());
        adminToUpdate.setEmail(admin.getEmail());
        adminToUpdate.setPassword(admin.getPassword());
        adminToUpdate.setPhone(admin.getPhone());
        adminToUpdate.setRole(admin.getRole());
        adminToUpdate.setUserReports(admin.getUserReports());
        adminToUpdate.setReviewReports(admin.getReviewReports());
        adminToUpdate.setRequests(admin.getRequests());

        Admin updatedAdmin = adminRepository.create(adminToUpdate);
        return updatedAdmin;
    }

    @Override
    public void delete(Long id) {
        adminRepository.delete(id);

    }

    public void updateAdminInformation(Admin admin, UserDTO userDTO) throws Exception {
        if (admin != null && userDTO != null) {
            // Check and update specific fields based on your logic
            if (userDTO.getFirstName() != null) {
                admin.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                admin.setLastName(userDTO.getLastName());
            }
            adminRepository.update(admin);
        }
    }

}
