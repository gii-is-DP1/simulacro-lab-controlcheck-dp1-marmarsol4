package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecoveryRoomService {

	private RecoveryRoomRepository recoveryRoomRepository;
	
	@Autowired
	public RecoveryRoomService(RecoveryRoomRepository recoveryRoomRepository) {
		this.recoveryRoomRepository = recoveryRoomRepository;
	}
	
	@Transactional(readOnly = true)
	public List<RecoveryRoom> getAll() {
		return recoveryRoomRepository.findAll();
	}
	
	@Transactional
	public RecoveryRoomType getRecoveryRoomType(String name) {
		return recoveryRoomRepository.getRecoveryRoomType(name);
	}
	
	@Transactional(rollbackFor = DuplicatedRoomNameException.class)
	public RecoveryRoom save(RecoveryRoom r) throws DuplicatedRoomNameException {
		
		List<RecoveryRoom> roomsType = recoveryRoomRepository.findAll().stream().filter(ro -> ro.getRecoveryRoomType() == r.getRecoveryRoomType()).collect(Collectors.toList());
		Boolean nombreNoDuplicado = roomsType.stream().allMatch(room -> !(room.getName().equals(r.getName())));
		
		if (nombreNoDuplicado) {
			return recoveryRoomRepository.save(r); 
		} else {
			throw new DuplicatedRoomNameException();
		}
	}
	
	@Transactional
	public List<RecoveryRoomType> getAllRecoveryRoomTypes() {
		return recoveryRoomRepository.findAllRecoveryRoomTypes();
	}
}
