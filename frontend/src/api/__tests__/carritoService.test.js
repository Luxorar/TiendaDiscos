import { describe, it, expect, vi, beforeEach } from 'vitest'
import { carritoService } from '../carritoService'

function mockFetch(response) {
  vi.stubGlobal('fetch', vi.fn().mockResolvedValue(response))
}

function jsonResponse(data, status = 200) {
  return {
    ok: status >= 200 && status < 300,
    status,
    headers: new Headers({ 'content-type': 'application/json' }),
    text: vi.fn().mockResolvedValue(JSON.stringify(data)),
    json: vi.fn().mockResolvedValue(data)
  }
}

function emptyResponse(status = 200) {
  return {
    ok: status >= 200 && status < 300,
    status,
    headers: new Headers({ 'content-length': '0' }),
    text: vi.fn().mockResolvedValue('')
  }
}

describe('carritoService', () => {
  beforeEach(() => {
    vi.restoreAllMocks()
  })

  describe('getCarrito', () => {
    it('obtiene el carrito del usuario', async () => {
      const mockData = { id: 1, userId: 1, discosAgregados: [] }
      mockFetch(jsonResponse(mockData))

      const result = await carritoService.getCarrito(1)
      expect(result).toEqual(mockData)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito/1', expect.anything())
    })
  })

  describe('createCarrito', () => {
    it('crea un nuevo carrito', async () => {
      const mockData = { id: 1, userId: 1 }
      mockFetch(jsonResponse(mockData))

      const result = await carritoService.createCarrito(1)
      expect(result).toEqual(mockData)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito', expect.objectContaining({
        method: 'POST',
        body: JSON.stringify({ userId: 1, productosAgregados: [], discosAgregados: [] })
      }))
    })
  })

  describe('addDisco', () => {
    it('agrega un disco al carrito', async () => {
      mockFetch(emptyResponse())

      await carritoService.addDisco(1, 42)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito/1/discos/42', expect.objectContaining({
        method: 'POST'
      }))
    })
  })

  describe('getDiscos', () => {
    it('obtiene los discos del carrito', async () => {
      const mockData = [{ discoId: 42, qty: 2, precio: 15000 }]
      mockFetch(jsonResponse(mockData))

      const result = await carritoService.getDiscos(1)
      expect(result).toEqual(mockData)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito/1/discos', expect.anything())
    })

    it('retorna array vacío cuando no hay discos', async () => {
      mockFetch(jsonResponse([]))

      const result = await carritoService.getDiscos(1)
      expect(result).toEqual([])
    })
  })

  describe('updateDiscoQty', () => {
    it('actualiza la cantidad de un disco', async () => {
      mockFetch(emptyResponse())

      await carritoService.updateDiscoQty(1, 42, 3)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito/1/discos/42?qty=3', expect.objectContaining({
        method: 'PUT'
      }))
    })
  })

  describe('removeDisco', () => {
    it('elimina un disco del carrito', async () => {
      mockFetch(emptyResponse())

      await carritoService.removeDisco(1, 42)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito/1/discos/42', expect.objectContaining({
        method: 'DELETE'
      }))
    })
  })

  describe('clearDiscos', () => {
    it('vacía todos los discos del carrito', async () => {
      mockFetch(emptyResponse())

      await carritoService.clearDiscos(1)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito/1/discos', expect.objectContaining({
        method: 'DELETE'
      }))
    })
  })

  describe('deleteCarrito', () => {
    it('elimina el carrito completo', async () => {
      mockFetch(emptyResponse())

      await carritoService.deleteCarrito(1)
      expect(fetch).toHaveBeenCalledWith('/api/v1/carrito/1', expect.objectContaining({
        method: 'DELETE'
      }))
    })
  })

  describe('manejo de errores', () => {
    it('lanza error cuando la respuesta no es ok', async () => {
      mockFetch({
        ok: false,
        status: 500,
        headers: new Headers({ 'content-type': 'text/plain' }),
        text: vi.fn().mockResolvedValue('Internal Server Error')
      })

      await expect(carritoService.getCarrito(1)).rejects.toThrow('Internal Server Error')
    })

    it('usa mensaje genérico cuando no hay texto de error', async () => {
      mockFetch({
        ok: false,
        status: 404,
        headers: new Headers({}),
        text: vi.fn().mockRejectedValue(new Error())
      })

      await expect(carritoService.getCarrito(1)).rejects.toThrow('Error 404')
    })
  })
})
