import axios from ''axios'';

const api = axios.create({
  baseURL: ''/api/v1'',
  headers: { ''Content-Type'': ''application/json'' },
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem(''access_token'');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const instrumentsApi = {
  search: async (filters = {}) => {
    const { data } = await api.get(''/instruments'', { params: filters });
    return data;
  },

  getById: async (id) => {
    const { data } = await api.get(`/instruments/${id}`);
    return data;
  },

  create: async (instrument) => {
    const { data } = await api.post(''/instruments'', instrument);
    return data;
  },
};

export default api;
