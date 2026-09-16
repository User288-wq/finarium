\# FINARIUM — Architecture technique



\## Stack technique



| Couche | Technologie | Version |

|---|---|---|

| Back-end | Java Spring Boot | 3.x / Java 17 |

| Base de données | PostgreSQL | 15 |

| Front-end | React | 18 |

| Sécurité | Keycloak (OAuth2/OIDC) | 22 |

| CI/CD | GitLab CI | — |

| Conteneurisation | Docker / Kubernetes | — |



\## Modèle de données



\### Table instrument



| Colonne | Type | Contrainte |

|---|---|---|

| id | BIGSERIAL | PRIMARY KEY |

| isin | VARCHAR(12) | UNIQUE, NOT NULL |

| libelle | VARCHAR(255) | NOT NULL |

| type | VARCHAR(20) | NOT NULL |

| devise | VARCHAR(3) | NOT NULL |

| statut | VARCHAR(20) | NOT NULL |

| date\_creation | TIMESTAMP | NOT NULL |

| date\_maj | TIMESTAMP | NOT NULL |



\## API REST



| Méthode | Endpoint | Description | Scope |

|---|---|---|---|

| GET | /api/v1/instruments | Recherche | mdm.read |

| GET | /api/v1/instruments/{id} | Consultation | mdm.read |

| POST | /api/v1/instruments | Création | mdm.write |



\## Sécurité



\- Authentification : OAuth2 / OpenID Connect via Keycloak

\- Autorisation : scopes (mdm.read, mdm.write, mdm.admin)

\- Chiffrement : TLS 1.3 en transit

\- Audit : journalisation complète

