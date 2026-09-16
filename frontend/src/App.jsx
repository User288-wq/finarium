import { BrowserRouter, Routes, Route } from ''react-router-dom'';
import { QueryClient, QueryClientProvider } from ''@tanstack/react-query'';
import { ThemeProvider, CssBaseline } from ''@mui/material'';
import Layout from ''./components/Layout'';
import InstrumentList from ''./pages/InstrumentList'';
import InstrumentCreate from ''./pages/InstrumentCreate'';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: { refetchOnWindowFocus: false, retry: 1 },
  },
});

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={{}}>
        <CssBaseline />
        <BrowserRouter>
          <Layout>
            <Routes>
              <Route path="/" element={<InstrumentList />} />
              <Route path="/instruments" element={<InstrumentList />} />
              <Route path="/instruments/new" element={<InstrumentCreate />} />
            </Routes>
          </Layout>
        </BrowserRouter>
      </ThemeProvider>
    </QueryClientProvider>
  );
}

export default App;
