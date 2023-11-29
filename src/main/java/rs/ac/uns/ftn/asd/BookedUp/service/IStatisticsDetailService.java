package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;

import java.util.Collection;

public interface IStatisticsDetailService {
    Collection<StatisticsDetailDTO> getAll();
    StatisticsDetailDTO getById(Long id);
    StatisticsDetailDTO create(StatisticsDetailDTO reportDetailDto) throws Exception;
    StatisticsDetailDTO update(StatisticsDetailDTO reportDetailDto) throws Exception;
    void delete(Long id);
}
