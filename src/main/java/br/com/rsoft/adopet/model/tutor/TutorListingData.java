package br.com.rsoft.adopet.model.tutor;

public record TutorListingData(Long id, String name, String phoneNumber, String city, String personalDescription, String profilePhoto) {
    public TutorListingData(Tutor tutor) {
        this(tutor.getId(), tutor.getName(), tutor.getPhoneNumber(), tutor.getCity(), tutor.getPersonalDescription(), tutor.getProfilePhoto());
    }
}
