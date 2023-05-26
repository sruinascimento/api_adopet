package com.br.r.adopet.controller;

import com.br.r.adopet.model.tutor.Tutor;
import com.br.r.adopet.model.tutor.TutorListingData;
import com.br.r.adopet.model.tutor.TutorRegisterData;
import com.br.r.adopet.model.tutor.TutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

}
