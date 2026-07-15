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

export const usuarioService = {
  getAll: () => request('/v1/admin'),
  getById: (id) => request(`/v1/admin/id/${id}`),
  getByName: (name) => request(`/v1/admin/name/${name}`),
  create: (data) => request('/v1/admin', { method: 'POST', body: JSON.stringify(data) }),
  update: (id, data) => request(`/v1/admin/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  delete: (id) => request(`/v1/admin/${id}`, { method: 'DELETE' }),
  getAllAdmins: () => request('/v1/admin/admins'),
  createAdmin: (data) => request('/v1/admin/admins', { method: 'POST', body: JSON.stringify(data) }),
  getAdminById: (id) => request(`/v1/admin/admins/${id}`),
  updateAdmin: (id, data) => request(`/v1/admin/admins/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  deleteAdmin: (id) => request(`/v1/admin/admins/${id}`, { method: 'DELETE' }),
  putModoOscuro: (id, modoOscuro) => request(`/v1/admin/modo-oscuro/${id}`, { method: 'PUT', body: JSON.stringify(modoOscuro) }),
  getUserInfo: (id) => request(`/v1/admin/info-user/${id}`)
}
