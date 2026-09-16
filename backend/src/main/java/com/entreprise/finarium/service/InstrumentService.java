package com.entreprise.finarium.service;

import com.entreprise.finarium.dto.CreateInstrumentDto;
import com.entreprise.finarium.dto.InstrumentDto;
import com.entreprise.finarium.entity.Instrument;
import com.entreprise.finarium.entity.InstrumentStatus;
import com.entreprise.finarium.entity.InstrumentType;
import com.entreprise.finarium.repository.InstrumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InstrumentService {

    private final InstrumentRepository repository;

    public InstrumentService(InstrumentRepository repository) {
        this.repository = repository;
    }

    public Page<InstrumentDto> search(
        String isin,
        InstrumentType type,
        InstrumentStatus statut,
        String devise,
        Pageable pageable
    ) {
        if (isin != null && !isin.isBlank()) {
            return repository.findByIsin(isin)
                .map(i -> Page.of(toDto(i)))
                .orElse(Page.empty());
        }
        return repository.findByTypeAndStatutAndDevise(type, statut, devise, pageable)
            .map(this::toDto);
    }

    public InstrumentDto getById(Long id) {
        return repository.findById(id)
            .map(this::toDto)
            .orElseThrow(() -> new RuntimeException("Instrument introuvable : " + id));
    }

    @Transactional
    public InstrumentDto create(CreateInstrumentDto dto) {
        Instrument instrument = new Instrument();
        instrument.setIsin(dto.isin());
        instrument.setLibelle(dto.libelle());
        instrument.setType(dto.type());
        instrument.setDevise(dto.devise());
        instrument.setStatut(InstrumentStatus.BROUILLON);

        Instrument saved = repository.save(instrument);
        return toDto(saved);
    }

    private InstrumentDto toDto(Instrument i) {
        return new InstrumentDto(
            i.getId(),
            i.getIsin(),
            i.getLibelle(),
            i.getType(),
            i.getDevise(),
            i.getStatut(),
            i.getDateCreation(),
            i.getDateMaj()
        );
    }
}
