package br.com.rsoft.adopet.controller;

import br.com.rsoft.adopet.model.message.Message;
import br.com.rsoft.adopet.model.tutor.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class TutorController {

    private final TutorRepository tutorRepository;

    public TutorController(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @PostMapping("tutors")
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid TutorRegisterData data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        try {
            Tutor tutor = data.toTutor();
            tutorRepository.save(tutor);
            TutorListingData tutorResponse = new TutorListingData(tutor);
            return ResponseEntity.ok(tutorResponse);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro de violação de integridade " + e.getMessage());

        }
    }

    @GetMapping("tutors")
    public ResponseEntity<?> get() {
        List<Tutor> tutors = tutorRepository.findAll();
        if (tutors.isEmpty()) {
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }

        List<TutorListingData> tutorsListingData = tutors.stream()
                .map(TutorListingData::new)
                .toList();

        return ResponseEntity.ok(tutorsListingData);
    }

    @GetMapping("tutors/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Tutor tutor = tutorRepository.getReferenceById(id);
            return ResponseEntity.ok(new TutorListingData(tutor));
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }
    }

    @DeleteMapping("tutors/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!tutorRepository.existsById(id)) {
            return ResponseEntity.ok(Message.TUTOR_NOT_DELETED.getMessage());
        }
        tutorRepository.deleteById(id);

        return ResponseEntity.ok(Message.TUTOR_DELETED.getMessage());
    }

    @PutMapping("tutors")
    @Transactional
    public ResponseEntity<?> put(@RequestBody @Valid TutorUpdatePut data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        if (!tutorRepository.existsById(data.id())) {
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }

        Tutor existingTutor = tutorRepository.getReferenceById(data.id());
        existingTutor.setName(data.name());
        existingTutor.setProfilePhoto(data.profilePhoto());
        existingTutor.setPhoneNumber(data.phoneNumber());
        existingTutor.setCity(data.city());
        existingTutor.setPersonalDescription(data.personalDescription());
        tutorRepository.save(existingTutor);

        return ResponseEntity.ok(new TutorListingData(existingTutor));
    }


    @PatchMapping("tutors")
    @Transactional
    public ResponseEntity<?> patch(@RequestBody @Valid TutorUpdatePatch data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleValidationErrors(bindingResult);
        }
        if (!tutorRepository.existsById(data.id())) {
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }

        Tutor tutor = tutorRepository.getReferenceById(data.id());

        if (data.name() != null && !data.name().equals(tutor.getName())) {
            tutor.setName(data.name());
        }
        if (data.phoneNumber() != null && !data.phoneNumber().equals(tutor.getPhoneNumber())) {
            tutor.setPhoneNumber(data.phoneNumber());
        }
        if (data.city() != null && !data.city().equals(tutor.getCity())) {
            tutor.setCity(data.city());
        }
        if (data.personalDescription() != null && !data.personalDescription().equals(tutor.getPersonalDescription())) {
            tutor.setName(data.personalDescription());
        }

        tutorRepository.save(tutor);

        return ResponseEntity.ok(new TutorListingData(tutor));
    }

    private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
