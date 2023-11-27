package rs.ac.uns.ftn.asd.BookedUp.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.dto.CreateAccommodationDTO;
@Component
public class AccommodationMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public AccommodationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Accommodation fromDTOToEntity(CreateAccommodationDTO dto){
        return modelMapper.map(dto, Accommodation.class);
    }

    public static CreateAccommodationDTO fromEntityToDTO(Accommodation accommodation){
        return new CreateAccommodationDTO(accommodation);
    }


}
