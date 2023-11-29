package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.AccommodationRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccommodationService implements IAcommodationService{
    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationMapper accommodationMapper;
    @Override
    public Collection<AccommodationDTO> getAll() {
        Collection<Accommodation> accommodations = (accommodationRepository.getAll());
        Collection<AccommodationDTO> accommodationDTOS = new ArrayList<>();

        for (Accommodation accommodation : accommodations) {
            System.out.println("STATUS: " + accommodation.getStatus());
            AccommodationDTO accommodationDTO = accommodationMapper.toDto(accommodation);
            accommodationDTOS.add(accommodationDTO);
        }

        return accommodationDTOS;
    }

    @Override
    public AccommodationDTO getById(Long id) {
        Accommodation accommodation = accommodationRepository.getById(id);
        return accommodationMapper.toDto(accommodation);
    }

    @Override
    public AccommodationDTO create(AccommodationDTO accommodationDto) throws Exception {
        if (accommodationDto.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Accommodation accommodation = accommodationMapper.toEntity(accommodationDto);
        Accommodation savedAccommodation = accommodationRepository.create(accommodation);
        return accommodationMapper.toDto(savedAccommodation);
    }

    @Override
    public AccommodationDTO update(AccommodationDTO accommodationDto) throws Exception {
        Accommodation accommodation = accommodationMapper.toEntity(accommodationDto);
        Accommodation accommodationToUpdate = accommodationRepository.getById(accommodation.getId());
        if (accommodationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        accommodationToUpdate.setName(accommodation.getName());
        accommodationToUpdate.setDescription(accommodation.getDescription());
        accommodationToUpdate.setAddress(accommodation.getAddress());
        accommodationToUpdate.setAmenities(accommodation.getAmenities());
        accommodationToUpdate.setPhotos(accommodation.getPhotos());
        accommodationToUpdate.setMinGuests(accommodation.getMinGuests());
        accommodationToUpdate.setMaxGuests(accommodation.getMaxGuests());
        accommodationToUpdate.setType(accommodation.getType());
        accommodationToUpdate.setPriceType(accommodation.getPriceType());
        accommodationToUpdate.setAvailability(accommodation.getAvailability());
        accommodationToUpdate.setPrice(accommodation.getPrice());
        accommodationToUpdate.setStatus(accommodation.getStatus());
        accommodationToUpdate.setCancellationDeadline(accommodation.getCancellationDeadline());
        accommodationToUpdate.setAutomaticReservationAcceptance(accommodation.isAutomaticReservationAcceptance());
        accommodationToUpdate.setReservations(accommodation.getReservations());
        accommodationToUpdate.setReviews(accommodation.getReviews());
        accommodationToUpdate.setAverageRating(accommodation.getAverageRating());
        accommodationToUpdate.setPriceChanges(accommodation.getPriceChanges());

        Accommodation updatedAcommodation = accommodationRepository.create(accommodationToUpdate);
        return accommodationMapper.toDto(updatedAcommodation);
    }

    @Override
    public void delete(Long id) {
        accommodationRepository.delete(id);
    }

    @Override
    public AccommodationDTO approve(AccommodationDTO accommodationDTO) throws Exception {
        if (accommodationDTO == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        Accommodation accommodation = accommodationMapper.toEntity(accommodationDTO);
        Accommodation accommodationToUpdate = accommodationRepository.getById(accommodation.getId());
        accommodationToUpdate.setStatus(AccommodationStatus.ACTIVE);
        Accommodation updatedAcommodation = accommodationRepository.create(accommodationToUpdate);
        System.out.println(updatedAcommodation.getStatus());
        return accommodationMapper.toDto(updatedAcommodation);
    }

    @Override
    public AccommodationDTO reject(AccommodationDTO accommodationDTO) throws  Exception{
        if (accommodationDTO == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        Accommodation accommodation = accommodationMapper.toEntity(accommodationDTO);
        Accommodation accommodationToUpdate = accommodationRepository.getById(accommodation.getId());
        accommodationToUpdate.setStatus(AccommodationStatus.REJECTED);
        Accommodation updatedAcommodation = accommodationRepository.create(accommodationToUpdate);
        System.out.println(updatedAcommodation.getStatus());
        return accommodationMapper.toDto(updatedAcommodation);
    }


}
