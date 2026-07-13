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

export const productoService = {
  getAll: () => request('/v1/productos'),
  getById: (id) => request(`/v1/productos/${id}`),
  getByName: (name) => request(`/v1/productos/nombre/${name}`),
  getByBrand: (brand) => request(`/v1/productos/marca/${brand}`),
  create: (data) => request('/v1/productos', { method: 'POST', body: JSON.stringify(data) }),
  delete: (id) => request(`/v1/productos/${id}`, { method: 'DELETE' })
}
