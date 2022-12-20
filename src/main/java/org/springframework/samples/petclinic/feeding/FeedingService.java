package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FeedingService {
	
	private FeedingRepository feedingRepository;
	
	@Autowired
	public FeedingService (FeedingRepository feedingRepository) {
		this.feedingRepository = feedingRepository;
	}
	
	@Transactional
	public List<FeedingType> getAllFeedingTypes() {
		return feedingRepository.findAllFeedingTypes();
	}
	
	@Transactional
	public List<Feeding> getAll() {
		return feedingRepository.findAll();
	}
	
	@Transactional
	public FeedingType getFeedingType(String name) {
		return feedingRepository.getFeedingType(name);
	}
	
	@Transactional(rollbackFor = UnfeasibleFeedingException.class) // Excepcion personalizada
	public void save(Feeding f) throws UnfeasibleFeedingException {
		
		// Si tipo de mascota no es tipo de mascota asociada a una alimentacion
		if (f.getPet().getType() != f.getFeedingType().getPetType()) {
			throw new UnfeasibleFeedingException();
		} else {
			this.feedingRepository.save(f); // Poner this
		}
	}

}
