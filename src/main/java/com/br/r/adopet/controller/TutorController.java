package com.br.r.adopet.controller;

import com.br.r.adopet.model.message.Message;
import com.br.r.adopet.model.tutor.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> put(@RequestBody @Valid TutorUpdatePut data) {
        if (!tutorRepository.existsById(data.id())) {
            return ResponseEntity.ok(Message.TUTOR_NOT_FOUND.getMessage());
        }

        Tutor tutor = tutorRepository.getReferenceById(data.id());
        tutor.updateTutorDataCompletely(data);
        tutorRepository.save(tutor);

        return ResponseEntity.ok(new TutorListingData(tutor));
    }

}
