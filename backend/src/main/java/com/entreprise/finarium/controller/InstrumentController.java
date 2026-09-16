package com.entreprise.finarium.controller;

import com.entreprise.finarium.dto.CreateInstrumentDto;
import com.entreprise.finarium.dto.InstrumentDto;
import com.entreprise.finarium.entity.InstrumentStatus;
import com.entreprise.finarium.entity.InstrumentType;
import com.entreprise.finarium.service.InstrumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instruments")
@Tag(name = "Instruments", description = "API de gestion des instruments financiers")
public class InstrumentController {

    private final InstrumentService service;

    public InstrumentController(InstrumentService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority(''SCOPE_mdm.read'')")
    @Operation(summary = "Recherche multi-critères d''instruments")
    public Page<InstrumentDto> search(
        @RequestParam(required = false) String isin,
        @RequestParam(required = false) InstrumentType type,
        @RequestParam(required = false) InstrumentStatus statut,
        @RequestParam(required = false) String devise,
        Pageable pageable
    ) {
        return service.search(isin, type, statut, devise, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(''SCOPE_mdm.read'')")
    @Operation(summary = "Consultation d''un instrument par ID")
    public InstrumentDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority(''SCOPE_mdm.write'')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Création d''un nouvel instrument")
    public InstrumentDto create(@RequestBody @Valid CreateInstrumentDto dto) {
        return service.create(dto);
    }
}
