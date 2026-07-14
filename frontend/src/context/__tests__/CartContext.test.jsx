import { describe, it, expect, vi, beforeEach } from 'vitest'
import { renderHook, act, waitFor } from '@testing-library/react'
import { CartProvider, useCart } from '../CartContext'
import { carritoService } from '../../api/carritoService'
import { AuthProvider } from '../AuthContext'

vi.mock('../../api/carritoService', () => ({
  carritoService: {
    getDiscos: vi.fn(),
    addDisco: vi.fn(),
    updateDiscoQty: vi.fn(),
    clearDiscos: vi.fn()
  }
}))

const mockUser = { id: 1, nombre: 'Test User' }

function wrapper({ children }) {
  return (
    <AuthProvider>
      <CartProvider>{children}</CartProvider>
    </AuthProvider>
  )
}

function wrapperWithUser({ children }) {
  localStorage.setItem('user', JSON.stringify(mockUser))
  return (
    <AuthProvider>
      <CartProvider>{children}</CartProvider>
    </AuthProvider>
  )
}

describe('CartContext', () => {
  beforeEach(() => {
    vi.clearAllMocks()
    localStorage.clear()
  })

  describe('estado inicial', () => {
    it('inicia con items vacíos y loading false', () => {
      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      expect(result.current.items).toEqual([])
      expect(result.current.loading).toBe(false)
      expect(result.current.count).toBe(0)
      expect(result.current.total).toBe(0)
    })
  })

  describe('loadCart', () => {
    it('carga los discos del carrito', async () => {
      const mockItems = [
        { discoId: 1, nombreDisco: 'Disco A', precio: 15000, qty: 2 },
        { discoId: 2, nombreDisco: 'Disco B', precio: 20000, qty: 1 }
      ]
      carritoService.getDiscos.mockResolvedValue(mockItems)

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.loadCart()
      })

      expect(result.current.items).toEqual(mockItems)
      expect(result.current.count).toBe(3)
      expect(result.current.total).toBe(50000)
    })

    it('maneja respuesta null como array vacío', async () => {
      carritoService.getDiscos.mockResolvedValue(null)

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.loadCart()
      })

      expect(result.current.items).toEqual([])
    })

    it('no carga si no hay usuario', async () => {
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.loadCart()
      })

      expect(carritoService.getDiscos).not.toHaveBeenCalled()
    })

    it('maneja error de carga', async () => {
      carritoService.getDiscos.mockRejectedValue(new Error('Network error'))

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.loadCart()
      })

      expect(result.current.items).toEqual([])
    })
  })

  describe('addItem', () => {
    it('agrega un disco al carrito y recarga', async () => {
      carritoService.addDisco.mockResolvedValue()
      carritoService.getDiscos.mockResolvedValue([
        { discoId: 1, nombreDisco: 'Disco A', precio: 15000, qty: 1 }
      ])

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.addItem({ id: 1 })
      })

      expect(carritoService.addDisco).toHaveBeenCalledWith(1, 1)
      expect(result.current.items).toHaveLength(1)
    })

    it('no agrega si no hay usuario', async () => {
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.addItem({ id: 1 })
      })

      expect(carritoService.addDisco).not.toHaveBeenCalled()
    })
  })

  describe('updateQty', () => {
    it('actualiza la cantidad de un disco', async () => {
      carritoService.updateDiscoQty.mockResolvedValue()
      carritoService.getDiscos.mockResolvedValue([
        { discoId: 1, nombreDisco: 'Disco A', precio: 15000, qty: 3 }
      ])

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.updateQty(1, 3)
      })

      expect(carritoService.updateDiscoQty).toHaveBeenCalledWith(1, 1, 3)
    })

    it('elimina el item si la cantidad es menor a 1', async () => {
      carritoService.updateDiscoQty.mockResolvedValue()

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.updateQty(1, 0)
      })

      expect(carritoService.updateDiscoQty).toHaveBeenCalledWith(1, 1, 0)
    })
  })

  describe('removeItem', () => {
    it('elimina un disco del carrito', async () => {
      carritoService.updateDiscoQty.mockResolvedValue()

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        result.current.items = [{ discoId: 1, qty: 1, precio: 15000 }]
      })

      await act(async () => {
        await result.current.removeItem(1)
      })

      expect(carritoService.updateDiscoQty).toHaveBeenCalledWith(1, 1, 0)
    })
  })

  describe('clearCart', () => {
    it('vacía el carrito', async () => {
      carritoService.clearDiscos.mockResolvedValue()

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.clearCart()
      })

      expect(carritoService.clearDiscos).toHaveBeenCalledWith(1)
      expect(result.current.items).toEqual([])
    })
  })

  describe('count y total', () => {
    it('calcula el total correctamente', async () => {
      const mockItems = [
        { discoId: 1, precio: 10000, qty: 2 },
        { discoId: 2, precio: 25000, qty: 1 }
      ]
      carritoService.getDiscos.mockResolvedValue(mockItems)

      localStorage.setItem('user', JSON.stringify(mockUser))
      const { result } = renderHook(() => useCart(), { wrapper })

      await act(async () => {
        await result.current.loadCart()
      })

      expect(result.current.count).toBe(3)
      expect(result.current.total).toBe(45000)
    })
  })
})
