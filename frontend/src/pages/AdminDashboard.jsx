import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { usuarioService } from '../api/usuarioService'
import Navbar from '../components/Navbar'
import './AdminDashboard.css'

const MENUS = [
  { label: 'Usuarios', path: '/admin/usuarios', color: '#3498DB' },
  { label: 'Productos', path: '/admin/productos', color: '#2ECC71' },
  { label: 'Discos', path: '/admin/discos', color: '#9B59B6' },
  { label: 'Descuentos', path: '/admin/descuentos', color: '#E67E22' },
  { label: 'Envíos', path: '/admin/envios', color: '#1ABC9C' },
  { label: 'Stock', path: '/admin/stock', color: '#E74C3C' },
  { label: 'Sedes', path: '/admin/sedes', color: '#34495E' },
  { label: 'Ventas', path: '/admin/ventas', color: '#F39C12' }
]

export default function AdminDashboard() {
  const { user } = useAuth()
  const navigate = useNavigate()

  useEffect(() => {
    if (!user) navigate('/login')
  }, [user])

  return (
    <div className="admin-page">
      <Navbar />
      <div className="admin-content">
        <h1>Panel de Administración</h1>
        <p className="admin-subtitle">Gestiona los recursos de la tienda</p>
        <div className="admin-grid">
          {MENUS.map((menu) => (
            <Link to={menu.path} key={menu.path} className="admin-card" style={{ borderTopColor: menu.color }}>
              <span className="admin-card-icon" style={{ background: menu.color }}>
                {menu.label[0]}
              </span>
              <span className="admin-card-label">{menu.label}</span>
            </Link>
          ))}
        </div>
      </div>
    </div>
  )
}
