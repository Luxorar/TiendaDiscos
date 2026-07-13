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

export const ventaService = {
  getAll: (params = {}) => {
    const query = new URLSearchParams(params).toString()
    return request(`/v1/ventas${query ? '?' + query : ''}`)
  },
  create: (data) => request('/v1/ventas', { method: 'POST', body: JSON.stringify(data) }),
  getById: (id) => request(`/v1/ventas/id/${id}`),
  getByUser: (userId) => request(`/v1/ventas/user/${userId}`),
  getProducts: (id) => request(`/v1/ventas/productos/${id}`),
  delete: (id) => request(`/v1/ventas/${id}`, { method: 'DELETE' })
}
