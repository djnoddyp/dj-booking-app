package pnodder.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import pnodder.model.Booking;

import java.util.List;

@Component
public interface BookingMapper {

    List<Booking> findAll();

    Booking findById(Integer id);

    List<Booking> findByName(String name);

    void save(@Param("booking") Booking booking, @Param("artistId") Integer artistId);

    List<String> findDistinctName();

    void deleteByName(String name);

}
