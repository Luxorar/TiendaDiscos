import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import { stockService } from '../api/stockService'
import './CrudPage.css'

export default function AdminStock() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState({ producto: '', sede: '', stockActual: '' })

  const load = () => {
    setLoading(true)
    stockService.getAll().then(setItems).catch(() => setItems([])).finally(() => setLoading(false))
  }
  useEffect(() => { load() }, [])

  const handleCreate = async (e) => {
    e.preventDefault()
    await stockService.create({ ...form, stockActual: Number(form.stockActual) })
    setForm({ producto: '', sede: '', stockActual: '' })
    setShowForm(false)
    load()
  }

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar este registro?')) return
    await stockService.delete(id)
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Stock</h1>
          <button className="btn-new" onClick={() => setShowForm(!showForm)}>{showForm ? 'Cancelar' : '+ Nuevo'}</button>
        </div>
        {showForm && (
          <form className="crud-form" onSubmit={handleCreate}>
            <input placeholder="ID Producto" value={form.producto} onChange={e => setForm({...form, producto: e.target.value})} required />
            <input placeholder="ID Sede" value={form.sede} onChange={e => setForm({...form, sede: e.target.value})} required />
            <input placeholder="Cantidad" type="number" value={form.stockActual} onChange={e => setForm({...form, stockActual: e.target.value})} required />
            <button type="submit" className="btn-save">Crear</button>
          </form>
        )}
        {loading ? <p className="loading-text">Cargando...</p> : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead><tr><th>ID</th><th>Producto</th><th>Sede</th><th>Stock</th><th>Acciones</th></tr></thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td><td>{item.nombreProducto || item.producto}</td><td>{item.nombreSede || item.sede}</td><td>{item.stockActual}</td>
                    <td><button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button></td>
                  </tr>
                ))}
                {items.length === 0 && <tr><td colSpan="5" className="no-data">No hay registros de stock</td></tr>}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
