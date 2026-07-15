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

export const carritoService = {
  getCarrito: (userId) => request(`/v1/carrito/${userId}`),
  createCarrito: (userId) => request('/v1/carrito', { method: 'POST', body: JSON.stringify({ userId, productosAgregados: [], discosAgregados: [] }) }),

  addDisco: (userId, discoId) => request(`/v1/carrito/${userId}/discos/${discoId}`, { method: 'POST' }),
  getDiscos: (userId) => request(`/v1/carrito/${userId}/discos`),
  updateDiscoQty: (userId, discoId, qty) => request(`/v1/carrito/${userId}/discos/${discoId}?qty=${qty}`, { method: 'PUT' }),
  removeDisco: (userId, discoId) => request(`/v1/carrito/${userId}/discos/${discoId}`, { method: 'DELETE' }),
  clearDiscos: (userId) => request(`/v1/carrito/${userId}/discos`, { method: 'DELETE' }),

  addProducto: (userId, productoId) => request(`/v1/carrito/${userId}/productos/${productoId}`, { method: 'POST' }),
  getProductos: (userId) => request(`/v1/carrito/${userId}/productos`),
  removeProducto: (userId, productoId) => request(`/v1/carrito/${userId}/productos/${productoId}`, { method: 'DELETE' }),

  deleteCarrito: (userId) => request(`/v1/carrito/${userId}`, { method: 'DELETE' })
}
