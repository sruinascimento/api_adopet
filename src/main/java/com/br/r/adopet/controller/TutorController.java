package com.br.r.adopet.controller;

import com.br.r.adopet.model.message.Message;
import com.br.r.adopet.model.tutor.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tutors")
public class TutorController {
    @Autowired
    private TutorRepository tutorRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TutorListingData> save(@RequestBody TutorRegisterData data) {
        Tutor tutor = new Tutor(data);
        tutorRepository.save(tutor);
        return ResponseEntity.ok(new TutorListingData(tutor));
    }

    @GetMapping
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Tutor tutor = tutorRepository.getReferenceById(id);
            return ResponseEntity.ok(new TutorListingData(tutor));
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!tutorRepository.existsById(id)) {
            return ResponseEntity.ok(Message.TUTOR_NOT_DELETED.getMessage());
        }
        tutorRepository.deleteById(id);
        return ResponseEntity.ok(Message.TUTOR_DELETED.getMessage());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> put(@RequestBody @Valid TutorUpdate data) {
        if (!tutorRepository.existsById(data.id())) {
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }

        Tutor tutor = tutorRepository.getReferenceById(data.id());
        tutor.updateTutorDataCompletely(data);
        tutorRepository.save(tutor);

        return ResponseEntity.ok(new TutorListingData(tutor));
    }


    @PatchMapping
    @Transactional
    public ResponseEntity<?> patch(@RequestBody TutorUpdate data) {
        System.out.println(data);
        if (data.id() == null || !tutorRepository.existsById(data.id())) {
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }

        Tutor tutor = tutorRepository.getReferenceById(data.id());
        //Strategy ou Builder????
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
}
