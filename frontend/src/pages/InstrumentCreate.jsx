import { useState } from 'react';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';
import { Box, TextField, Button, MenuItem, Alert, Typography, Paper } from '@mui/material';
import { instrumentsApi } from '../api/instruments';

const TYPES = ['ACTION', 'OBLIGATION', 'OPCVM', 'ETF', 'DERIVE'];
const DEVISES = ['EUR', 'USD', 'GBP', 'CHF', 'JPY'];

export default function InstrumentCreate() {
  const navigate = useNavigate();
  const qc = useQueryClient();
  const [form, setForm] = useState({ isin: '', libelle: '', type: 'ACTION', devise: 'EUR' });
  const [error, setError] = useState(null);

  const mutation = useMutation({
    mutationFn: instrumentsApi.create,
    onSuccess: () => { qc.invalidateQueries({ queryKey: ['instruments'] }); navigate('/instruments'); },
    onError: (e) => setError(e.response?.data?.message || 'Erreur'),
  });

  const handleSubmit = (e) => { e.preventDefault(); setError(null); mutation.mutate(form); };

  return (
    <Paper sx={{ p: 4, maxWidth: 600 }}>
      <Typography variant="h4" sx={{ mb: 3 }}>Nouvel instrument</Typography>
      {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
      <Box component="form" onSubmit={handleSubmit}>
        <TextField fullWidth label="ISIN" value={form.isin}
          onChange={(e) => setForm({ ...form, isin: e.target.value.toUpperCase() })}
          inputProps={{ maxLength: 12 }} required sx={{ mb: 2 }} />
        <TextField fullWidth label="Libellé" value={form.libelle}
          onChange={(e) => setForm({ ...form, libelle: e.target.value })}
          required sx={{ mb: 2 }} />
        <TextField select fullWidth label="Type" value={form.type}
          onChange={(e) => setForm({ ...form, type: e.target.value })} sx={{ mb: 2 }}>
          {TYPES.map((t) => <MenuItem key={t} value={t}>{t}</MenuItem>)}
        </TextField>
        <TextField select fullWidth label="Devise" value={form.devise}
          onChange={(e) => setForm({ ...form, devise: e.target.value })} sx={{ mb: 3 }}>
          {DEVISES.map((d) => <MenuItem key={d} value={d}>{d}</MenuItem>)}
        </TextField>
        <Button type="submit" variant="contained" disabled={mutation.isLoading}>
          {mutation.isLoading ? 'Création...' : 'Créer'}
        </Button>
      </Box>
    </Paper>
  );
}