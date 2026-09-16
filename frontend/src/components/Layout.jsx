import { AppBar, Toolbar, Typography, Container, Box } from ''@mui/material'';
import { Link } from ''react-router-dom'';

export default function Layout({ children }) {
  return (
    <Box sx={{ display: ''flex'', flexDirection: ''column'', minHeight: ''100vh'' }}>
      <AppBar position="static">
        <Toolbar>
          <Typography
            variant="h6"
            component={Link}
            to="/"
            sx={{ color: ''white'', textDecoration: ''none'', flexGrow: 1 }}
          >
            🏦 FINARIUM
          </Typography>
          <Typography variant="body2" sx={{ color: ''white'' }}>
            Référentiel des instruments financiers
          </Typography>
        </Toolbar>
      </AppBar>

      <Container maxWidth="lg" sx={{ flexGrow: 1, py: 3 }}>
        {children}
      </Container>

      <Box component="footer" sx={{ py: 2, textAlign: ''center'', bgcolor: ''#f5f5f5'' }}>
        <Typography variant="caption">
          © 2026 FINARIUM — Squad Applicative
        </Typography>
      </Box>
    </Box>
  );
}
