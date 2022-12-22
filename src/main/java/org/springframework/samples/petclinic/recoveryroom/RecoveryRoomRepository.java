package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecoveryRoomRepository extends CrudRepository<RecoveryRoom, Integer>{
	
	@Query("SELECT rrt FROM RecoveryRoomType rrt")
	List<RecoveryRoomType> findAllRecoveryRoomTypes();
	
	RecoveryRoom save(RecoveryRoom rr);
	
	List<RecoveryRoom> findAll();
	
	@Query("SELECT rrt FROM RecoveryRoomType rrt WHERE rrt.name = :name")
	RecoveryRoomType getRecoveryRoomType(@Param("name") String name);
	
}
