import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import { envioService } from '../api/envioService'
import './CrudPage.css'

export default function AdminEnvios() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)

  const load = () => {
    setLoading(true)
    envioService.getAll().then(setItems).catch(() => setItems([])).finally(() => setLoading(false))
  }
  useEffect(() => { load() }, [])

  const handleStatus = async (id, status) => {
    await envioService.updateStatus(id, status)
    load()
  }

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar este envío?')) return
    await envioService.delete(id)
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Envíos</h1>
        </div>
        {loading ? <p className="loading-text">Cargando...</p> : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead><tr><th>ID</th><th>Venta</th><th>Dirección</th><th>Estado</th><th>Empresa</th><th>Acciones</th></tr></thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.ventaId}</td>
                    <td>{item.direccionDestino}</td>
                    <td>
                      <select value={item.estadoEnvio} onChange={e => handleStatus(item.id, e.target.value)} className="status-select">
                        <option value="EN_CAMINO">En Camino</option>
                        <option value="ENTREGADO">Entregado</option>
                        <option value="CANCELADO">Cancelado</option>
                      </select>
                    </td>
                    <td>{item.empresaReparto}</td>
                    <td><button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button></td>
                  </tr>
                ))}
                {items.length === 0 && <tr><td colSpan="6" className="no-data">No hay envíos</td></tr>}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
