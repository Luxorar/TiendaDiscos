import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import Navbar from '../components/Navbar'
import './Home.css'

const DISCO_IMAGE = 'data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="180" height="180"><rect fill="%23333" width="180" height="180" rx="8"/><circle fill="%23111" cx="90" cy="90" r="60" stroke="%23555" stroke-width="2"/><circle fill="%23333" cx="90" cy="90" r="12"/><circle fill="%23222" cx="90" cy="90" r="4"/></svg>'

const CATEGORIES = ['Éxitos', 'Solistas', 'Éxitos en Español']

export default function Home() {
  const [discos, setDiscos] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    fetch('/api/v1/discos')
      .then(r => r.json())
      .then(setDiscos)
      .catch(() => setDiscos([]))
      .finally(() => setLoading(false))
  }, [])

  const grouped = CATEGORIES.map(cat => ({
    name: cat,
    items: discos.slice(0, 4)
  }))

  if (loading) return <><Navbar /><div className="loading">Cargando...</div></>

  return (
    <div className="home">
      <Navbar />
      <div className="home-content">
        {grouped.map((group, i) => (
          <section key={i} className="home-category">
            <div className="category-header">
              <h2>{group.name}</h2>
            </div>
            <div className="disco-grid">
              {group.items.map((disco, j) => (
                <Link to={`/disco/${disco.id || j}`} key={j} className="disco-card">
                  <div className="disco-img">
                    <img src={disco.imagen || DISCO_IMAGE} alt={disco.nombreDisco || 'Disco'} />
                  </div>
                  <p className="disco-artist">{disco.artista || 'Artista'}</p>
                  <p className="disco-price">${disco.precio?.toLocaleString() || '0'}</p>
                </Link>
              ))}
              {group.items.length === 0 && (
                <p className="no-data">No hay discos disponibles</p>
              )}
            </div>
          </section>
        ))}
      </div>
    </div>
  )
}
