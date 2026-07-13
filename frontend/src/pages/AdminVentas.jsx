import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import { ventaService } from '../api/ventaService'
import './CrudPage.css'

export default function AdminVentas() {
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)

  const load = () => {
    setLoading(true)
    ventaService.getAll().then(setItems).catch(() => setItems([])).finally(() => setLoading(false))
  }
  useEffect(() => { load() }, [])

  const handleDelete = async (id) => {
    if (!confirm('¿Eliminar esta venta?')) return
    await ventaService.delete(id)
    load()
  }

  return (
    <div className="crud-page">
      <Navbar />
      <div className="crud-content">
        <div className="crud-header">
          <h1>Ventas</h1>
        </div>
        {loading ? <p className="loading-text">Cargando...</p> : (
          <div className="crud-table-wrap">
            <table className="crud-table">
              <thead><tr><th>ID</th><th>Usuario</th><th>Fecha</th><th>Puntos Usados</th><th>Puntos Ganados</th><th>Descuento</th><th>Acciones</th></tr></thead>
              <tbody>
                {items.map(item => (
                  <tr key={item.id}>
                    <td>{item.id}</td><td>{item.usuario}</td><td>{item.fechaVenta}</td>
                    <td>{item.puntosUsados}</td><td>{item.puntosGanados}</td><td>{item.descuento}%</td>
                    <td><button className="btn-delete" onClick={() => handleDelete(item.id)}>Eliminar</button></td>
                  </tr>
                ))}
                {items.length === 0 && <tr><td colSpan="7" className="no-data">No hay ventas</td></tr>}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  )
}
