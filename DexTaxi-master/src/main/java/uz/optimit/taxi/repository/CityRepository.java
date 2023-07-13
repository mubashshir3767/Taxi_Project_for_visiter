package uz.optimit.taxi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.optimit.taxi.entity.AutoModel;
import uz.optimit.taxi.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

     Optional<City> findByNameAndRegionId(String name,Integer id);

     List<City> findAllByRegionId(Integer region_id);

    Page<City> findAllByRegionId(int id, Pageable pageable);
}
