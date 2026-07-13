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

export const discoService = {
  getAll: () => request('/v1/titulos'),
  getById: (id) => request(`/v1/titulos/${id}`),
  create: (data) => request('/v1/titulos', { method: 'POST', body: JSON.stringify(data) }),
  update: (id, data) => request(`/v1/titulos/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
  delete: (id) => request(`/v1/titulos/${id}`, { method: 'DELETE' })
}
