import { describe, it, expect, vi, beforeEach } from 'vitest'
import { render, screen, waitFor } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import { MemoryRouter } from 'react-router-dom'
import Carrito from '../Carrito'
import { CartProvider } from '../../context/CartContext'
import { AuthProvider } from '../../context/AuthContext'
import { carritoService } from '../../api/carritoService'

vi.mock('../../api/carritoService', () => ({
  carritoService: {
    getDiscos: vi.fn(),
    addDisco: vi.fn(),
    updateDiscoQty: vi.fn(),
    clearDiscos: vi.fn()
  }
}))

const mockNavigate = vi.fn()
vi.mock('react-router-dom', async () => {
  const actual = await vi.importActual('react-router-dom')
  return { ...actual, useNavigate: () => mockNavigate }
})

const mockItems = [
  { discoId: 1, nombreDisco: 'Dark Side of the Moon', artista: 'Pink Floyd', precio: 25000, qty: 1, imagen: null },
  { discoId: 2, nombreDisco: 'Abbey Road', artista: 'The Beatles', precio: 30000, qty: 2, imagen: null }
]

function renderCarrito() {
  localStorage.setItem('user', JSON.stringify({ id: 1, nombre: 'Test' }))
  return render(
    <MemoryRouter>
      <AuthProvider>
        <CartProvider>
          <Carrito />
        </CartProvider>
      </AuthProvider>
    </MemoryRouter>
  )
}

describe('Carrito', () => {
  beforeEach(() => {
    vi.clearAllMocks()
    localStorage.clear()
    mockNavigate.mockClear()
  })

  describe('carrito vacío', () => {
    it('muestra mensaje de carrito vacío', async () => {
      carritoService.getDiscos.mockResolvedValue([])

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Tu carrito está vacío')).toBeInTheDocument()
      })
    })

    it('muestra enlace para seguir comprando', async () => {
      carritoService.getDiscos.mockResolvedValue([])

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Seguir comprando')).toHaveAttribute('href', '/')
      })
    })
  })

  describe('carrito con items', () => {
    it('renderiza los items del carrito', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
        expect(screen.getByText('Abbey Road')).toBeInTheDocument()
      })
    })

    it('muestra el nombre del artista', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Pink Floyd')).toBeInTheDocument()
        expect(screen.getByText('The Beatles')).toBeInTheDocument()
      })
    })

    it('muestra las cantidades', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('x1')).toBeInTheDocument()
        expect(screen.getByText('x2')).toBeInTheDocument()
      })
    })

    it('calcula el subtotal correctamente', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)

      renderCarrito()

      await waitFor(() => {
        const totals = screen.getAllByText(/\$85[\.,]000/)
        expect(totals.length).toBeGreaterThanOrEqual(1)
      })
    })
  })

  describe('interacciones', () => {
    it('incrementa la cantidad al presionar +', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)
      carritoService.updateDiscoQty.mockResolvedValue()
      carritoService.getDiscos.mockResolvedValueOnce(mockItems)
        .mockResolvedValueOnce([{ ...mockItems[0], qty: 2 }, mockItems[1]])

      const user = userEvent.setup()
      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
      })

      const plusButtons = screen.getAllByText('+')
      await user.click(plusButtons[0])

      expect(carritoService.updateDiscoQty).toHaveBeenCalledWith(1, 1, 2)
    })

    it('decrementa la cantidad al presionar -', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)
      carritoService.updateDiscoQty.mockResolvedValue()

      const user = userEvent.setup()
      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
      })

      const minusButtons = screen.getAllByText('-')
      await user.click(minusButtons[0])

      expect(carritoService.updateDiscoQty).toHaveBeenCalledWith(1, 1, 0)
    })

    it('elimina un item al presionar la X', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)
      carritoService.updateDiscoQty.mockResolvedValue()

      const user = userEvent.setup()
      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
      })

      const removeButtons = screen.getAllByText('✕')
      await user.click(removeButtons[0])

      expect(carritoService.updateDiscoQty).toHaveBeenCalledWith(1, 1, 0)
    })
  })

  describe('navegación', () => {
    it('redirige a login si no hay usuario', () => {
      render(
        <MemoryRouter>
          <AuthProvider>
            <CartProvider>
              <Carrito />
            </CartProvider>
          </AuthProvider>
        </MemoryRouter>
      )

      expect(mockNavigate).toHaveBeenCalledWith('/login')
    })

    it('navega a checkout al presionar PAGAR', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)
      const user = userEvent.setup()

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
      })

      await user.click(screen.getByText('PAGAR'))

      expect(mockNavigate).toHaveBeenCalledWith('/checkout')
    })
  })

  describe('descuentos', () => {
    it('abre modal de descuentos', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)
      const user = userEvent.setup()

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
      })

      await user.click(screen.getByText('VER DESCUENTOS'))

      expect(screen.getByText('Descuentos')).toBeInTheDocument()
    })

    it('cierra modal de descuentos al hacer click fuera', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)
      const user = userEvent.setup()

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
      })

      await user.click(screen.getByText('VER DESCUENTOS'))
      expect(screen.getByText('Descuentos')).toBeInTheDocument()

      await user.click(screen.getByText('Cerrar'))

      await waitFor(() => {
        expect(screen.queryByText('Descuentos')).not.toBeInTheDocument()
      })
    })

    it('aplica descuento por puntos y actualiza el total', async () => {
      carritoService.getDiscos.mockResolvedValue(mockItems)
      const user = userEvent.setup()

      renderCarrito()

      await waitFor(() => {
        expect(screen.getByText('Dark Side of the Moon')).toBeInTheDocument()
      })

      await user.click(screen.getByText('VER DESCUENTOS'))

      const input = screen.getByPlaceholderText('Mínimo 500 puntos')
      await user.type(input, '10')

      await user.click(screen.getByText('Cerrar'))

      await waitFor(() => {
        expect(screen.getByText('Descuento (10%)')).toBeInTheDocument()
        expect(screen.getByText(/-\$8[\.,]500/)).toBeInTheDocument()
        expect(screen.getByText(/\$76[\.,]500/)).toBeInTheDocument()
      })
    })
  })
})
