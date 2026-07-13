import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { useCart } from '../context/CartContext'
import Navbar from '../components/Navbar'
import './Checkout.css'

export default function Checkout() {
  const navigate = useNavigate()
  const { user } = useAuth()
  const { items, total, clearCart } = useCart()
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const [form, setForm] = useState({
    destinatario: '',
    direccion: '',
    codigoPostal: '',
    metodoPago: 'tarjeta',
    numeroTarjeta: '',
    fechaVencimiento: '',
    codigoSeguridad: ''
  })

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
    setError('')
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    setError('')

    if (!items || items.length === 0) {
      setError('Parámetros incorrectos')
      return
    }

    if (!form.destinatario.trim() || !form.direccion.trim() || !form.codigoPostal.trim()) {
      setError('Parámetros incorrectos')
      return
    }

    if (!form.numeroTarjeta.trim() || !form.fechaVencimiento.trim() || !form.codigoSeguridad.trim()) {
      setError('Parámetros incorrectos')
      return
    }

    if (total <= 0) {
      setError('Parámetros incorrectos')
      return
    }

    setLoading(true)
    setTimeout(() => {
      setLoading(false)
      clearCart()
      alert('¡Compra realizada con éxito!')
      navigate('/')
    }, 1500)
  }

  return (
    <div className="checkout-page">
      <Navbar />
      <div className="checkout-content">
        <form className="checkout-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Destinatario</label>
            <input name="destinatario" value={form.destinatario} onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label>Dirección</label>
            <input name="direccion" value={form.direccion} onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label>Código postal</label>
            <input name="codigoPostal" value={form.codigoPostal} onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label>Método de pago</label>
            <select name="metodoPago" value={form.metodoPago} onChange={handleChange}>
              <option value="tarjeta">Tarjeta de crédito</option>
              <option value="debito">Tarjeta de débito</option>
              <option value="transferencia">Transferencia</option>
            </select>
          </div>
          <div className="form-group">
            <label>Número de tarjeta</label>
            <input name="numeroTarjeta" value={form.numeroTarjeta} onChange={handleChange} placeholder="XXXX-XXXX-XXXX-XXXX" required />
          </div>
          <div className="form-row">
            <div className="form-group">
              <label>Fecha de vencimiento</label>
              <input name="fechaVencimiento" value={form.fechaVencimiento} onChange={handleChange} placeholder="MM/AA" required />
            </div>
            <div className="form-group">
              <label>Código de seguridad</label>
              <input name="codigoSeguridad" value={form.codigoSeguridad} onChange={handleChange} placeholder="CVV" required />
            </div>
          </div>
          <div className="checkout-summary">
            <div className="summary-row">
              <span>SUB TOTAL</span>
              <span>${total.toLocaleString()}</span>
            </div>
            <div className="summary-row summary-total">
              <span>TOTAL</span>
              <span>${total.toLocaleString()}</span>
            </div>
          </div>
          {error && <p className="checkout-error">{error}</p>}
          <button type="submit" className="btn-comprar" disabled={loading}>
            {loading ? 'Procesando...' : 'Realizar compra'}
          </button>
        </form>
      </div>
    </div>
  )
}
