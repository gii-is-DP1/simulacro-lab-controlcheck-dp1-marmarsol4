package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FeedingRepository extends CrudRepository<Feeding, Integer>{
	
	@Query("SELECT ft FROM FeedingType ft")
	List<FeedingType> findAllFeedingTypes();
	
	@Query("SELECT f FROM Feeding f")
	List<Feeding> findAll();
	
	@Query("SELECT ft FROM FeedingType ft WHERE ft.name = :name") // = or LIKE
	FeedingType getFeedingType(@Param("name") String name);
	
	Feeding save(Feeding f) throws DataAccessException;

}
