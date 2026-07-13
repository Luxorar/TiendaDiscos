import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import Navbar from '../components/Navbar'
import { useCart } from '../context/CartContext'
import './DiscoDetail.css'

const DISCO_IMAGE = 'data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="300" height="300"><rect fill="%23333" width="300" height="300" rx="12"/><circle fill="%23111" cx="150" cy="150" r="100" stroke="%23555" stroke-width="3"/><circle fill="%23333" cx="150" cy="150" r="20"/><circle fill="%23222" cx="150" cy="150" r="6"/></svg>'

export default function DiscoDetail() {
  const { id } = useParams()
  const navigate = useNavigate()
  const { addItem } = useCart()
  const [disco, setDisco] = useState(null)
  const [loading, setLoading] = useState(true)
  const [added, setAdded] = useState(false)

  useEffect(() => {
    fetch(`/api/v1/discos/${id}`)
      .then(r => r.ok ? r.json() : null)
      .then(setDisco)
      .catch(() => setDisco(null))
      .finally(() => setLoading(false))
  }, [id])

  const handleAdd = async () => {
    if (!disco) return
    await addItem(disco)
    setAdded(true)
    setTimeout(() => setAdded(false), 1500)
  }

  if (loading) return <><Navbar /><div className="loading">Cargando...</div></>
  if (!disco) return <><Navbar /><div className="loading">Disco no encontrado</div></>

  return (
    <div className="disco-detail-page">
      <Navbar />
      <div className="disco-detail-content">
        <div className="disco-detail-main">
          <div className="disco-detail-img">
            <img src={disco.imagen || DISCO_IMAGE} alt={disco.nombreDisco || 'Disco'} />
          </div>
          <div className="disco-detail-info">
            <h1>{disco.nombreDisco || 'Sin nombre'}</h1>
            <p className="detail-artist">{disco.artista || 'Artista desconocido'}</p>
            <p className="detail-sku">SKU: {disco.id}</p>
            <p className="detail-price">${disco.precio?.toLocaleString() || '0'}</p>
            <p className="detail-stock">Stock Disponible</p>
            <div className="detail-desc">
              <h3>Descripción</h3>
              <p>Disco de {disco.artista || 'artista'} - {disco.nombreDisco || 'título'}</p>
            </div>
            <button className="btn-add-cart" onClick={handleAdd}>
              {added ? 'Agregado!' : 'Agregar al Carro'}
            </button>
          </div>
        </div>
        <div className="disco-detail-recs">
          <h2>Recomendaciones</h2>
          <div className="recs-grid">
            {[1,2,3,4].map(i => (
              <div key={i} className="rec-card">
                <img src={DISCO_IMAGE} alt="Disco" />
                <p>Artista</p>
                <p className="rec-price">$9.990</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}
