package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Admin;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.dto.AdminDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.GuestDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AdminMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.AdminRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AdminService implements IAdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Collection<AdminDTO> getAll() {
        Collection<Admin> admins = adminRepository.getAll();
        Collection<AdminDTO> adminDTOS = new ArrayList<>();

        for (Admin admin : admins) {
            AdminDTO adminDTO = adminMapper.toDto(admin);
            adminDTOS.add(adminDTO);
        }

        return adminDTOS;
    }

    @Override
    public AdminDTO getById(Long id) {
        Admin admin = adminRepository.getById(id);
        return adminMapper.toDto(admin);
    }

    @Override
    public AdminDTO create(AdminDTO adminDTO) throws Exception {
        if (adminDTO.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        Admin admin = adminMapper.toEntity(adminDTO);
        Admin savedAdmin = adminRepository.create(admin);
        return adminMapper.toDto(savedAdmin);
    }

    @Override
    public AdminDTO update(AdminDTO adminDTO) throws Exception {
        Admin admin = adminMapper.toEntity(adminDTO);
        Admin adminToUpdate = adminRepository.getById(admin.getId());
        if (adminToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }

        adminToUpdate.setFirstName(admin.getFirstName());
        adminToUpdate.setLastName(admin.getLastName());
        adminToUpdate.setAddress(admin.getAddress());
        adminToUpdate.setEmail(admin.getEmail());
        adminToUpdate.setPassword(admin.getPassword());
        adminToUpdate.setPhone(admin.getPhone());
        //adminToUpdate.setRole(admin.getRole());
        adminToUpdate.setUserReports(admin.getUserReports());
        adminToUpdate.setReviewReports(admin.getReviewReports());
        adminToUpdate.setRequests(admin.getRequests());

        adminToUpdate.setAuthority(admin.getAuthority());
        adminToUpdate.setProfilePicture(admin.getProfilePicture());
        adminToUpdate.setVerified(admin.isVerified());
        adminToUpdate.setLastPasswordResetDate(admin.getLastPasswordResetDate());

        Admin updatedAdmin = adminRepository.create(adminToUpdate);
        return adminMapper.toDto(updatedAdmin);
    }

    @Override
    public void delete(Long id) {
        adminRepository.delete(id);

    }

    public void updateAdminInformation(AdminDTO adminDTO, UserDTO userDTO) throws Exception {
        if (adminDTO != null && userDTO != null) {
            // Check and update specific fields based on your logic
            if (userDTO.getFirstName() != null) {
                adminDTO.setFirstName(userDTO.getFirstName());
            }
            if (userDTO.getLastName() != null) {
                adminDTO.setLastName(userDTO.getLastName());
            }
            Admin admin = adminMapper.toEntity(adminDTO);
            adminRepository.update(admin);
        }
    }

}
