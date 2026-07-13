import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import { sedeService } from '../api/sedeService'
import './CrudPage.css'

export default function AdminDiscos() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState({ nombreDisco: '', artista: '', precio: '' })
  const [imagenFile, setImagenFile] = useState(null)
  const [uploadingId, setUploadingId] = useState(null)

  const load = () => {
    setLoading(true)
    fetch('/api/v1/discos').then(r => r.ok ? r.json() : []).then(setItems).catch(() => setItems([])).finally(() => setLoading(false))
  }
  useEffect(() => { load() }, [])

  const handleCreate = async (e) => {
    e.preventDefault()
    const res = await fetch('/api/v1/discos', { method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify({ ...form, precio: Number(form.precio) }) })
    const nuevoDisco = await res.json()
    if (imagenFile && nuevoDisco.id) {
      const fd = new FormData()
      fd.append('imagen', imagenFile)
      await fetch(`/api/v1/discos/${nuevoDisco.id}/imagen`, { method: 'POST', body: fd })
    }
    setForm({ nombreDisco: '', artista: '', precio: '' })
    setImagenFile(null)
    setShowForm(false)
    load()
  }

  const handleUploadImagen = async (id, file) => {
    setUploadingId(id)
    const fd = new FormData()
    fd.append('imagen', file)
    await fetch(`/api/v1/discos/${id}/imagen`, { method: 'POST', body: fd })
    setUploadingId(null)
    load()
  }

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar este disco?')) return
    await fetch(`/api/v1/discos/${id}`, { method: 'DELETE' })
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Discos</h1>
          <button className="btn-new" onClick={() => setShowForm(!showForm)}>{showForm ? 'Cancelar' : '+ Nuevo'}</button>
        </div>
        {showForm && (
          <form className="crud-form" onSubmit={handleCreate}>
            <input placeholder="Nombre del disco" value={form.nombreDisco} onChange={e => setForm({...form, nombreDisco: e.target.value})} required />
            <input placeholder="Artista" value={form.artista} onChange={e => setForm({...form, artista: e.target.value})} required />
            <input placeholder="Precio" type="number" value={form.precio} onChange={e => setForm({...form, precio: e.target.value})} required />
            <label className="btn-upload-label">
              Portada (opcional)
              <input type="file" accept="image/*" onChange={e => setImagenFile(e.target.files[0])} hidden />
            </label>
            {imagenFile && <span className="upload-filename">{imagenFile.name}</span>}
            <button type="submit" className="btn-save">Crear</button>
          </form>
        )}
        {loading ? <p className="loading-text">Cargando...</p> : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead><tr><th>ID</th><th>Portada</th><th>Nombre</th><th>Artista</th><th>Precio</th><th>Acciones</th></tr></thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>
                      {item.imagen ? (
                        <img src={item.imagen} alt={item.nombreDisco} style={{width:50,height:50,objectFit:'cover',borderRadius:4}} />
                      ) : (
                        <label className="btn-upload-sm">
                          {uploadingId === item.id ? '...' : 'Subir'}
                          <input type="file" accept="image/*" hidden onChange={e => handleUploadImagen(item.id, e.target.files[0])} disabled={uploadingId === item.id} />
                        </label>
                      )}
                    </td>
                    <td>{item.nombreDisco}</td><td>{item.artista}</td><td>${item.precio?.toLocaleString()}</td>
                    <td><button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button></td>
                  </tr>
                ))}
                {items.length === 0 && <tr><td colSpan="6" className="no-data">No hay discos</td></tr>}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
