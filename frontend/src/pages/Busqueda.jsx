import { useState, useEffect } from 'react'
import { useSearchParams, Link } from 'react-router-dom'
import Navbar from '../components/Navbar'
import { useCart } from '../context/CartContext'
import './Busqueda.css'

const DISCO_IMAGE = 'data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="120" height="120"><rect fill="%23333" width="120" height="120" rx="8"/><circle fill="%23111" cx="60" cy="60" r="40" stroke="%23555" stroke-width="2"/><circle fill="%23333" cx="60" cy="60" r="8"/><circle fill="%23222" cx="60" cy="60" r="3"/></svg>'

export default function Busqueda() {
  const [searchParams] = useSearchParams()
  const query = searchParams.get('q') || ''
  const [results, setResults] = useState([])
  const [loading, setLoading] = useState(true)
  const { addItem } = useCart()
  const [addedId, setAddedId] = useState(null)

  useEffect(() => {
    if (!query) { setResults([]); setLoading(false); return }
    setLoading(true)
    fetch(`/api/v1/discos/nombre/${encodeURIComponent(query)}`)
      .then(r => r.ok ? r.json() : [])
      .then(setResults)
      .catch(() => setResults([]))
      .finally(() => setLoading(false))
  }, [query])

  const handleAdd = async (item) => {
    await addItem(item)
    setAddedId(item.id)
    setTimeout(() => setAddedId(null), 1500)
  }

  return (
    <div className="busqueda-page">
      <Navbar />
      <div className="busqueda-content">
        <div className="busqueda-banner">
          <span>Busqueda: {query}</span>
        </div>
        {loading ? (
          <div className="loading">Buscando...</div>
        ) : results.length === 0 ? (
          <p className="no-results">No se encontraron resultados para "{query}"</p>
        ) : (
          <div className="busqueda-list">
            {results.map((item, i) => (
              <div key={i} className="busqueda-item">
                <img src={item.imagen || DISCO_IMAGE} alt={item.nombreDisco || item.nombreProducto || ''} className="busqueda-img" onError={(e) => { e.target.src = DISCO_IMAGE }} />
                <div className="busqueda-info">
                  <h3>{item.nombreDisco || item.nombreProducto}</h3>
                  <p className="busqueda-artist">{item.artista || item.marca || ''}</p>
                  <p className="busqueda-price">${item.precio?.toLocaleString() || '0'}</p>
                </div>
                <button className="btn-agregar" onClick={() => handleAdd(item)}>
                  {addedId === item.id ? 'Agregado!' : 'Agregar al Carro'}
                </button>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  )
}
