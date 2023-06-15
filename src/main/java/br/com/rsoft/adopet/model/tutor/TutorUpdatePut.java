package com.br.r.adopet.model.tutor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TutorUpdatePut(
        @NotNull(message = "Must not be null")
        Long id,
        @NotBlank(message = "Field is required")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ]+(([',. -][A-Za-zÀ-ÿ ])?[A-Za-zÀ-ÿ]*)*$", message = "The name can only contain letters and accents.")
        @Size(min = 3, message = "The name must contain at least 3 letters.")
        String name,
        String profilePhoto,
        @NotBlank(message = "Field is required")
        @Pattern(regexp = "^(\\+55|55)?(\\d{2})9\\d{8}$", message = "The number must be in the format 55DD912345678 or DD912345678, only numbers are allowed.")
        String phoneNumber,
        @NotBlank(message = "Field is required")
        String city,
        String personalDescription) {
}
