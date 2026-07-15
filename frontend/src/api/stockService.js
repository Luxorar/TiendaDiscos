const BASE_URL = '/api'

async function request(url, options = {}) {
  const res = await fetch(`${BASE_URL}${url}`, {
    headers: { 'Content-Type': 'application/json', ...options.headers },
    ...options
  })
  if (!res.ok) {
    const text = await res.text().catch(() => '')
    throw new Error(text || `Error ${res.status}`)
  }
  if (res.status === 204 || res.headers.get('content-length') === '0') return null
  const ct = res.headers.get('content-type') || ''
  if (!ct.includes('application/json')) return await res.text()
  return res.json()
}

export const stockService = {
  getAll: () => request('/v1/stock'),
  getById: (id) => request(`/v1/stock/${id}`),
  getByProduct: (name) => request(`/v1/stock/producto/${name}`),
  getByBranch: (name) => request(`/v1/stock/sede/${name}`),
  getByDisco: (discoId) => request(`/v1/stock/disco/${discoId}`),
  create: (data) => request('/v1/stock', { method: 'POST', body: JSON.stringify(data) }),
  updateQuantity: (id, qty) => request(`/v1/stock/${id}/cantidad?nuevoStock=${qty}`, { method: 'PUT' }),
  delete: (id) => request(`/v1/stock/${id}`, { method: 'DELETE' })
}
