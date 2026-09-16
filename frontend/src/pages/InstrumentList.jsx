import { useState } from ''react'';
import { useQuery } from ''@tanstack/react-query'';
import { Link } from ''react-router-dom'';
import {
  Box, TextField, Table, TableHead, TableBody, TableRow, TableCell,
  CircularProgress, Alert, Button, Typography, Paper
} from ''@mui/material'';
import { instrumentsApi } from ''../api/instruments'';

export default function InstrumentList() {
  const [filters, setFilters] = useState({ isin: '' });

  const { data, isLoading, error } = useQuery({
    queryKey: [''instruments'', filters],
    queryFn: () => instrumentsApi.search(filters),
  });

  return (
    <Box>
      <Box sx={{ display: ''flex'', justifyContent: ''space-between'', mb: 3 }}>
        <Typography variant="h4">Instruments financiers</Typography>
        <Button component={Link} to="/instruments/new" variant="contained">
          Nouvel instrument
        </Button>
      </Box>

      <TextField
        fullWidth
        label="Rechercher par ISIN"
        value={filters.isin}
        onChange={(e) => setFilters({ ...filters, isin: e.target.value.toUpperCase() })}
        sx={{ mb: 3 }}
      />

      {isLoading && <CircularProgress />}
      {error && <Alert severity="error">Erreur de chargement</Alert>}

      {data && (
        <Paper>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ISIN</TableCell>
                <TableCell>Libellé</TableCell>
                <TableCell>Type</TableCell>
                <TableCell>Devise</TableCell>
                <TableCell>Statut</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {(data.content || []).map((instrument) => (
                <TableRow key={instrument.id} hover>
                  <TableCell>{instrument.isin}</TableCell>
                  <TableCell>{instrument.libelle}</TableCell>
                  <TableCell>{instrument.type}</TableCell>
                  <TableCell>{instrument.devise}</TableCell>
                  <TableCell>{instrument.statut}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </Paper>
      )}
    </Box>
  );
}
