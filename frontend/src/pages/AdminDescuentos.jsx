import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import { descuentoService } from '../api/descuentoService'
import './CrudPage.css'

export default function AdminDescuentos() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState({ nombre: '', descuento: '', estado: 'ACTIVO' })

  const load = () => {
    setLoading(true)
    descuentoService.getAll().then(setItems).catch(() => setItems([])).finally(() => setLoading(false))
  }
  useEffect(() => { load() }, [])

  const handleCreate = async (e) => {
    e.preventDefault()
    await descuentoService.create({ ...form, descuento: Number(form.descuento), discoIds: [], productoIds: [] })
    setForm({ nombre: '', descuento: '', estado: 'ACTIVO' })
    setShowForm(false)
    load()
  }

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar este descuento?')) return
    await descuentoService.delete(id)
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Descuentos</h1>
          <button className="btn-new" onClick={() => setShowForm(!showForm)}>{showForm ? 'Cancelar' : '+ Nuevo'}</button>
        </div>
        {showForm && (
          <form className="crud-form" onSubmit={handleCreate}>
            <input placeholder="Nombre" value={form.nombre} onChange={e => setForm({...form, nombre: e.target.value})} required />
            <input placeholder="% Descuento" type="number" value={form.descuento} onChange={e => setForm({...form, descuento: e.target.value})} required />
            <select value={form.estado} onChange={e => setForm({...form, estado: e.target.value})}>
              <option value="ACTIVO">Activo</option>
              <option value="INACTIVO">Inactivo</option>
            </select>
            <button type="submit" className="btn-save">Crear</button>
          </form>
        )}
        {loading ? <p className="loading-text">Cargando...</p> : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead><tr><th>ID</th><th>Nombre</th><th>Descuento</th><th>Estado</th><th>Acciones</th></tr></thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td><td>{item.nombre}</td><td>{item.descuento}%</td>
                    <td><span className={`badge badge-${item.estado?.toLowerCase()}`}>{item.estado}</span></td>
                    <td><button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button></td>
                  </tr>
                ))}
                {items.length === 0 && <tr><td colSpan="5" className="no-data">No hay descuentos</td></tr>}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
