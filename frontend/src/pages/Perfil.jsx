import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import Navbar from '../components/Navbar'
import './Perfil.css'

export default function Perfil() {
  const { user, login, logout } = useAuth()
  const navigate = useNavigate()
  const [form, setForm] = useState({
    userName: user?.userName || '',
    gmail: user?.gmail || '',
    direccion: '',
    telefono: '',
    oscuro: false
  })

  if (!user) {
    navigate('/login')
    return null
  }

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value })
  }

  const handleSave = () => {
    login({ ...user, userName: form.userName })
    alert('Cambios guardados')
  }

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <div className="perfil-page">
      <Navbar />
      <div className="perfil-content">
        <div className="perfil-avatar">
          <div className="avatar-circle">
            <svg width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="#999" strokeWidth="1.5"><path d="M20 21v-2a4 4 0 00-4-4H8a4 4 0 00-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
          </div>
        </div>
        <div className="perfil-form">
          <div className="perfil-field">
            <label>Nombre</label>
            <input name="userName" value={form.userName} onChange={handleChange} />
          </div>
          <div className="perfil-field">
            <label>Dirección predeterminada</label>
            <input name="direccion" value={form.direccion} onChange={handleChange} placeholder="Ingresa tu dirección" />
          </div>
          <div className="perfil-field">
            <label>Dirección de Correo Electrónico</label>
            <input name="gmail" value={form.gmail} onChange={handleChange} />
          </div>
          <div className="perfil-field">
            <label>Teléfono</label>
            <input name="telefono" value={form.telefono} onChange={handleChange} placeholder="Ingresa tu teléfono" />
          </div>
          <div className="perfil-toggle">
            <label className="toggle-label">
              <span>Modo Oscuro</span>
              <input type="checkbox" name="oscuro" checked={form.oscuro} onChange={handleChange} />
              <span className="toggle-slider"></span>
            </label>
          </div>
          <div className="perfil-actions">
            <button className="btn-cerrar" onClick={handleLogout}>CERRAR SESIÓN</button>
            <button className="btn-guardar" onClick={handleSave}>Guardar Cambios</button>
          </div>
        </div>
      </div>
    </div>
  )
}
