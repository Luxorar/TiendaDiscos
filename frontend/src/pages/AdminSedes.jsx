import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import { sedeService } from '../api/sedeService'
import './CrudPage.css'

export default function AdminSedes() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState({ nombreSede: '', direccionSede: '', numberSedeTelefono: '' })

  const load = () => {
    setLoading(true)
    sedeService.getAll().then(setItems).catch(() => setItems([])).finally(() => setLoading(false))
  }
  useEffect(() => { load() }, [])

  const handleCreate = async (e) => {
    e.preventDefault()
    await sedeService.create(form)
    setForm({ nombreSede: '', direccionSede: '', numberSedeTelefono: '' })
    setShowForm(false)
    load()
  }

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar esta sede?')) return
    await sedeService.delete(id)
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Sedes</h1>
          <button className="btn-new" onClick={() => setShowForm(!showForm)}>{showForm ? 'Cancelar' : '+ Nuevo'}</button>
        </div>
        {showForm && (
          <form className="crud-form" onSubmit={handleCreate}>
            <input placeholder="Nombre" value={form.nombreSede} onChange={e => setForm({...form, nombreSede: e.target.value})} required />
            <input placeholder="Dirección" value={form.direccionSede} onChange={e => setForm({...form, direccionSede: e.target.value})} required />
            <input placeholder="Teléfono" value={form.numberSedeTelefono} onChange={e => setForm({...form, numberSedeTelefono: e.target.value})} required />
            <button type="submit" className="btn-save">Crear</button>
          </form>
        )}
        {loading ? <p className="loading-text">Cargando...</p> : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead><tr><th>ID</th><th>Nombre</th><th>Dirección</th><th>Teléfono</th><th>Acciones</th></tr></thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td><td>{item.nombreSede}</td><td>{item.direccionSede}</td><td>{item.numberSedeTelefono}</td>
                    <td><button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button></td>
                  </tr>
                ))}
                {items.length === 0 && <tr><td colSpan="5" className="no-data">No hay sedes</td></tr>}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
