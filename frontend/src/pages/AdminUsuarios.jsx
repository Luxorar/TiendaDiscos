import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../components/Navbar'
import { usuarioService } from '../api/usuarioService'
import './CrudPage.css'

export default function AdminUsuarios() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState({ userName: '', gmail: '', contrasena: '' })

  const load = () => {
    setLoading(true)
    usuarioService.getAll()
      .then(setItems)
      .catch(() => setItems([]))
      .finally(() => setLoading(false))
  }

  useEffect(() => { load() }, [])

  const handleCreate = async (e) => {
    e.preventDefault()
    await usuarioService.create(form)
    setForm({ userName: '', gmail: '', contrasena: '' })
    setShowForm(false)
    load()
  }

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar este usuario?')) return
    await usuarioService.delete(id)
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Usuarios</h1>
          <button className="btn-new" onClick={() => setShowForm(!showForm)}>
            {showForm ? 'Cancelar' : '+ Nuevo'}
          </button>
        </div>

        {showForm && (
          <form className="crud-form" onSubmit={handleCreate}>
            <input placeholder="Nombre" value={form.userName} onChange={e => setForm({...form, userName: e.target.value})} required />
            <input placeholder="Gmail" type="email" value={form.gmail} onChange={e => setForm({...form, gmail: e.target.value})} required />
            <input placeholder="Contraseña" type="password" value={form.contrasena} onChange={e => setForm({...form, contrasena: e.target.value})} required />
            <button type="submit" className="btn-save">Crear</button>
          </form>
        )}

        {loading ? (
          <p className="loading-text">Cargando...</p>
        ) : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Nombre</th>
                  <th>Gmail</th>
                  <th>Puntos</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.userName}</td>
                    <td>{item.gmail}</td>
                    <td>{item.puntos || 0}</td>
                    <td>
                      <button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button>
                    </td>
                  </tr>
                ))}
                {items.length === 0 && (
                  <tr><td colSpan="5" className="no-data">No hay usuarios</td></tr>
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
