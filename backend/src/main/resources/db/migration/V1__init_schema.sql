CREATE TABLE instrument (
    id            BIGSERIAL PRIMARY KEY,
    isin          VARCHAR(12)  NOT NULL UNIQUE,
    libelle       VARCHAR(255) NOT NULL,
    type          VARCHAR(20)  NOT NULL,
    devise        VARCHAR(3)   NOT NULL,
    statut        VARCHAR(20)  NOT NULL DEFAULT ''BROUILLON'',
    date_creation TIMESTAMP    NOT NULL DEFAULT NOW(),
    date_maj      TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_instrument_isin   ON instrument(isin);
CREATE INDEX idx_instrument_statut ON instrument(statut);
CREATE INDEX idx_instrument_type   ON instrument(type);
CREATE INDEX idx_instrument_devise ON instrument(devise);

CREATE TABLE historique_statut (
    id             BIGSERIAL PRIMARY KEY,
    instrument_id  BIGINT       NOT NULL REFERENCES instrument(id),
    ancien_statut  VARCHAR(20),
    nouveau_statut VARCHAR(20)  NOT NULL,
    utilisateur    VARCHAR(100) NOT NULL,
    date_action    TIMESTAMP    NOT NULL DEFAULT NOW(),
    motif          TEXT
);

CREATE INDEX idx_historique_instrument ON historique_statut(instrument_id);
