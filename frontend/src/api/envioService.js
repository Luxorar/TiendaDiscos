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
  return res.json()
}

export const envioService = {
  getAll: () => request('/v1/envios'),
  create: (data) => request('/v1/envios', { method: 'POST', body: JSON.stringify(data) }),
  updateStatus: (id, status) => request(`/v1/envios/${id}`, { method: 'PUT', body: JSON.stringify(status) }),
  updateAddress: (id, address) => request(`/v1/envios/dir/${id}`, { method: 'PUT', body: JSON.stringify(address) }),
  delete: (id) => request(`/v1/envios/${id}`, { method: 'DELETE' })
}
