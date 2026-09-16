package com.entreprise.finarium.dto;

import com.entreprise.finarium.entity.InstrumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateInstrumentDto(

    @NotBlank(message = "L''ISIN est obligatoire")
    @Pattern(regexp = "^[A-Z]{2}[A-Z0-9]{9}[0-9]$", message = "Format ISIN invalide (ISO 6166)")
    String isin,

    @NotBlank(message = "Le libellé est obligatoire")
    @Size(max = 255, message = "Le libellé ne doit pas dépasser 255 caractères")
    String libelle,

    @NotNull(message = "Le type est obligatoire")
    InstrumentType type,

    @NotBlank(message = "La devise est obligatoire")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Format devise invalide (ISO 4217)")
    String devise
) {}
