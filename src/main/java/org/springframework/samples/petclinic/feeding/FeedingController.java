package org.springframework.samples.petclinic.feeding;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {

	private static final String VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";
	
	private final FeedingService feedingService;
	private final PetService petService;
	
	@Autowired
	public FeedingController(FeedingService feedingService, PetService petService) {
		this.feedingService = feedingService;
		this.petService = petService;
	}
	
	@ModelAttribute("pets")
	public Collection<Pet> populatePetTypes() {
		return this.petService.getAllPets();
	}
	
	@ModelAttribute("feedingTypes")
	public Collection<FeedingType> populateFeedingTypes() {
		return this.feedingService.getAllFeedingTypes();
	}
	
	@GetMapping(value = "/create")
	public String initCreationForm(ModelMap model) {
		Feeding fed = new Feeding();
		model.put("feeding", fed);
		
//		Collection<PetType> lpt = this.petService.findPetTypes();
//		model.put("types", lpt);
//		
//		Collection<FeedingType> lft = this.feedingService.getAllFeedingTypes();
//		model.put("feedingTypes", lft);		
		
		return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("feeding", feeding);	
			
//			Collection<PetType> lpt = this.petService.findPetTypes();
//			model.put("types", lpt);
//			
//			Collection<FeedingType> lft = this.feedingService.getAllFeedingTypes();
//			model.put("feedingTypes", lft);	
			
			return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
		}
		else {
            try {
            	this.feedingService.save(feeding);
            } catch(UnfeasibleFeedingException ex){
                result.rejectValue("pet", "error", "La mascota seleccionada no se le puede asignar el plan especificado");
    			
//                Collection<PetType> lpt = this.petService.findPetTypes();
//    			model.put("types", lpt);
//    			
//    			Collection<FeedingType> lft = this.feedingService.getAllFeedingTypes();
//    			model.put("feedingTypes", lft);	
                
                return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
            }
            return "welcome"; //redirect:/ no furula
		}
	}
	
}