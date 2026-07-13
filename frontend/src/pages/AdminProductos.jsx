import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import { productoService } from '../api/productoService'
import './CrudPage.css'

export default function AdminProductos() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState({ nombreProducto: '', marca: '', precio: '' })

  const load = () => {
    setLoading(true)
    productoService.getAll().then(setItems).catch(() => setItems([])).finally(() => setLoading(false))
  }
  useEffect(() => { load() }, [])

  const handleCreate = async (e) => {
    e.preventDefault()
    await productoService.create({ ...form, precio: Number(form.precio) })
    setForm({ nombreProducto: '', marca: '', precio: '' })
    setShowForm(false)
    load()
  }

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar este producto?')) return
    await productoService.delete(id)
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Productos</h1>
          <button className="btn-new" onClick={() => setShowForm(!showForm)}>{showForm ? 'Cancelar' : '+ Nuevo'}</button>
        </div>
        {showForm && (
          <form className="crud-form" onSubmit={handleCreate}>
            <input placeholder="Nombre" value={form.nombreProducto} onChange={e => setForm({...form, nombreProducto: e.target.value})} required />
            <input placeholder="Marca" value={form.marca} onChange={e => setForm({...form, marca: e.target.value})} required />
            <input placeholder="Precio" type="number" value={form.precio} onChange={e => setForm({...form, precio: e.target.value})} required />
            <button type="submit" className="btn-save">Crear</button>
          </form>
        )}
        {loading ? <p className="loading-text">Cargando...</p> : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead><tr><th>ID</th><th>Nombre</th><th>Marca</th><th>Precio</th><th>Acciones</th></tr></thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td><td>{item.nombreProducto}</td><td>{item.marca}</td><td>${item.precio?.toLocaleString()}</td>
                    <td><button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button></td>
                  </tr>
                ))}
                {items.length === 0 && <tr><td colSpan="5" className="no-data">No hay productos</td></tr>}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
