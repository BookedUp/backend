package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.dto.PhotoDTO;

@Component
public class PhotoMapper implements MapperInterface<Photo, PhotoDTO> {

    @Override
    public Photo toEntity(PhotoDTO dto) {
        if (dto == null){
            return null;
        }

        return new Photo(dto.getId(), dto.getUrl(), dto.getCaption(), 0, 0);
    }

    @Override
    public PhotoDTO toDto(Photo entity) {
        if (entity == null){
            return null;
        }
        return new PhotoDTO(entity.getId(), entity.getUrl(), entity.getCaption());
    }
}
