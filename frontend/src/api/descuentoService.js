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

export const descuentoService = {
  getAll: () => request('/v1/descuentos'),
  getById: (id) => request(`/v1/descuentos/${id}`),
  search: (nombre) => request(`/v1/descuentos/buscar?nombre=${nombre}`),
  create: (data) => request('/v1/descuentos', { method: 'POST', body: JSON.stringify(data) }),
  update: (id, data) => request(`/v1/descuentos/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  delete: (id) => request(`/v1/descuentos/${id}`, { method: 'DELETE' }),
  addDisco: (nombre, discoId) => request(`/v1/descuentos/${nombre}/discos/${discoId}`, { method: 'POST' }),
  removeDisco: (nombre, discoId) => request(`/v1/descuentos/descuento/${nombre}`, { method: 'DELETE', body: JSON.stringify(discoId) }),
  addProducto: (nombre, productoId) => request(`/v1/descuentos/producto/${nombre}`, { method: 'POST', body: JSON.stringify(productoId) }),
  removeProducto: (nombre, productoId) => request(`/v1/descuentos/producto/${nombre}`, { method: 'DELETE', body: JSON.stringify(productoId) })
}
