package com.entreprise.finarium.dto;

import com.entreprise.finarium.entity.InstrumentStatus;
import com.entreprise.finarium.entity.InstrumentType;
import java.time.LocalDateTime;

public record InstrumentDto(
    Long id,
    String isin,
    String libelle,
    InstrumentType type,
    String devise,
    InstrumentStatus statut,
    LocalDateTime dateCreation,
    LocalDateTime dateMaj
) {}
