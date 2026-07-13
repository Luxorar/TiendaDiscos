import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { useCart } from '../context/CartContext'
import Navbar from '../components/Navbar'
import './Carrito.css'

const DISCO_IMAGE = 'data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="80" height="80"><rect fill="%23333" width="80" height="80" rx="6"/><circle fill="%23111" cx="40" cy="40" r="25" stroke="%23555" stroke-width="1.5"/><circle fill="%23333" cx="40" cy="40" r="5"/></svg>'

export default function Carrito() {
  const { user } = useAuth()
  const navigate = useNavigate()
  const { items, loading, loadCart, updateQty, removeItem, total } = useCart()
  const [showDescuentos, setShowDescuentos] = useState(false)
  const [descuentoPuntos, setDescuentoPuntos] = useState(0)
  const [descuentos, setDescuentos] = useState([])

  useEffect(() => {
    if (!user) { navigate('/login'); return }
    loadCart()
  }, [user])

  const subtotal = total
  const descuentoMonto = subtotal * (descuentoPuntos / 100)
  const totalFinal = subtotal - descuentoMonto

  if (loading) return <><Navbar /><div className="loading">Cargando carrito...</div></>

  return (
    <div className="carrito-page">
      <Navbar />
      <div className="carrito-content">
        <h1>Productos seleccionados</h1>
        {items.length === 0 ? (
          <div className="carrito-empty">
            <p>Tu carrito está vacío</p>
            <Link to="/" className="btn-seguir">Seguir comprando</Link>
          </div>
        ) : (
          <>
            <div className="carrito-list">
              {items.map((item) => (
                <div key={item.discoId} className="carrito-item">
                  <img src={item.imagen || DISCO_IMAGE} alt={item.nombreDisco || ''} className="carrito-img" />
                  <div className="carrito-info">
                    <h3>{item.nombreDisco}</h3>
                    <p className="carrito-desc">{item.artista || ''}</p>
                    <p className="carrito-price">${item.precio?.toLocaleString()}</p>
                  </div>
                  <div className="carrito-qty">
                    <button onClick={() => updateQty(item.discoId, item.qty - 1)}>-</button>
                    <span>x{item.qty}</span>
                    <button onClick={() => updateQty(item.discoId, item.qty + 1)}>+</button>
                  </div>
                  <button className="carrito-remove" onClick={() => removeItem(item.discoId)}>✕</button>
                </div>
              ))}
            </div>
            <div className="carrito-summary">
              <div className="summary-row">
                <span>SUB TOTAL</span>
                <span>${subtotal.toLocaleString()}</span>
              </div>
              {descuentoPuntos > 0 && (
                <div className="summary-row summary-descuento">
                  <span>Descuento ({descuentoPuntos}%)</span>
                  <span>-${descuentoMonto.toLocaleString()}</span>
                </div>
              )}
              <div className="summary-row summary-total">
                <span>TOTAL</span>
                <span>${totalFinal.toLocaleString()}</span>
              </div>
              <div className="carrito-actions">
                <button className="btn-descuentos" onClick={() => setShowDescuentos(true)}>
                  VER DESCUENTOS
                </button>
                <button className="btn-pagar" onClick={() => navigate('/checkout')}>
                  PAGAR
                </button>
              </div>
            </div>
          </>
        )}

        {showDescuentos && (
          <div className="modal-overlay" onClick={() => setShowDescuentos(false)}>
            <div className="modal" onClick={e => e.stopPropagation()}>
              <h2>Descuentos</h2>
              <div className="modal-field">
                <label>Descuento por puntos</label>
                <input
                  type="number"
                  placeholder="Mínimo 500 puntos"
                  value={descuentoPuntos || ''}
                  onChange={(e) => setDescuentoPuntos(Number(e.target.value))}
                />
              </div>
              <div className="modal-discounts">
                {descuentos.length > 0 ? descuentos.map((d, i) => (
                  <div key={i} className="discount-item">
                    <span>{d.nombre} - {d.descuento}%</span>
                    <button onClick={() => setDescuentoPuntos(d.descuento)}>Aplicar</button>
                  </div>
                )) : (
                  <p className="no-desc">No hay descuentos disponibles</p>
                )}
              </div>
              <button className="modal-close" onClick={() => setShowDescuentos(false)}>Cerrar</button>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
