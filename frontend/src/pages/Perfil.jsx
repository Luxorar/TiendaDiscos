import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import Navbar from '../components/Navbar'
import './Perfil.css'

export default function Perfil() {
  const { user, login, logout, toggleDarkMode } = useAuth()
  const navigate = useNavigate()
  const [form, setForm] = useState({
    userName: user?.userName || '',
    direccion: '',
    telefono: ''
  })

  if (!user) {
    navigate('/login')
    return null
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setForm({ ...form, [name]: value })
  }

  const handleSave = () => {
    login({ ...user, userName: form.userName })
    alert('Cambios guardados')
  }

  const handleToggleDarkMode = async () => {
    try {
      await toggleDarkMode()
    } catch {
      alert('Error al cambiar modo oscuro')
    }
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
            <label>Correo Electrónico</label>
            <input name="gmail" value={user?.gmail || ''} readOnly />
          </div>
          <div className="perfil-field">
            <label>Teléfono</label>
            <input name="telefono" value={form.telefono} onChange={handleChange} placeholder="Ingresa tu teléfono" />
          </div>
          <div className="perfil-toggle">
            <label className="toggle-label">
              <span>Modo Oscuro</span>
              <input type="checkbox" checked={user?.modoOscuro || false} onChange={handleToggleDarkMode} />
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
