package com.br.r.adopet.model.tutor;

public record TutorListingData(Long id, String name, String phoneNumber, String city, String personalDescription) {
    public TutorListingData(Tutor tutor) {
        this(tutor.getId(), tutor.getName(), tutor.getPhoneNumber(), tutor.getCity(), tutor.getPersonalDescription());
    }
}
