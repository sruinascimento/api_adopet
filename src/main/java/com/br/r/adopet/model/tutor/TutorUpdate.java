package com.br.r.adopet.model.tutor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TutorUpdate(
        @NotNull
        Long id,
        @NotBlank @Pattern(regexp = "[a-zA-Zçãẽĩõũáéíóúâêîôûà]{3,}")
        String name,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String city,
        @NotBlank
        String personalDescription) {
}
