package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReportDTO {
    private Long id;
    private String reason;
    private UserDTO reportedUserDTO;
    private boolean status;

    public void copyValues(UserReportDTO dto) {
        this.reason = dto.getReason();
        this.reportedUserDTO = dto.getReportedUserDTO();
        this.status = dto.isStatus();

    }
}
