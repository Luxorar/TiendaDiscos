import { createContext, useContext, useState, useCallback } from 'react'
import { useAuth } from './AuthContext'
import { carritoService } from '../api/carritoService'

const CartContext = createContext(null)

export function CartProvider({ children }) {
  const { user } = useAuth()
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(false)

  const loadCart = useCallback(async () => {
    if (!user) return
    setLoading(true)
    try {
      const data = await carritoService.getDiscos(user.id)
      setItems(data || [])
    } catch {
      setItems([])
    } finally {
      setLoading(false)
    }
  }, [user])

  const addItem = useCallback(async (disco) => {
    if (!user) return
    try {
      await carritoService.addDisco(user.id, disco.id)
      await loadCart()
    } catch (e) {
      console.error('Error agregando al carrito:', e)
    }
  }, [user, loadCart])

  const updateQty = useCallback(async (discoId, qty) => {
    if (!user) return
    if (qty < 1) return removeItem(discoId)
    try {
      await carritoService.updateDiscoQty(user.id, discoId, qty)
      await loadCart()
    } catch (e) {
      console.error('Error actualizando cantidad:', e)
    }
  }, [user, loadCart])

  const removeItem = useCallback(async (discoId) => {
    if (!user) return
    try {
      await carritoService.updateDiscoQty(user.id, discoId, 0)
      setItems(prev => prev.filter(item => item.discoId !== discoId))
    } catch (e) {
      console.error('Error eliminando del carrito:', e)
    }
  }, [user])

  const clearCart = useCallback(async () => {
    if (!user) return
    try {
      await carritoService.clearDiscos(user.id)
      setItems([])
    } catch (e) {
      console.error('Error vaciando carrito:', e)
    }
  }, [user])

  const count = items.reduce((sum, item) => sum + item.qty, 0)
  const total = items.reduce((sum, item) => sum + (item.precio * item.qty), 0)

  return (
    <CartContext.Provider value={{ items, loading, addItem, updateQty, removeItem, clearCart, loadCart, count, total }}>
      {children}
    </CartContext.Provider>
  )
}

export function useCart() {
  return useContext(CartContext)
}
