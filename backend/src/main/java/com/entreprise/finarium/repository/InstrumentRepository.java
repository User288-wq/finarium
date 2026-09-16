package com.entreprise.finarium.repository;

import com.entreprise.finarium.entity.Instrument;
import com.entreprise.finarium.entity.InstrumentStatus;
import com.entreprise.finarium.entity.InstrumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    Optional<Instrument> findByIsin(String isin);

    Page<Instrument> findByTypeAndStatutAndDevise(
        InstrumentType type,
        InstrumentStatus statut,
        String devise,
        Pageable pageable
    );
}
