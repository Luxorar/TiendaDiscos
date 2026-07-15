import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { usuarioService } from '../api/usuarioService'
import logoImg from '../assets/logo.png'
import './Login.css'

export default function Login() {
  const [gmail, setGmail] = useState('')
  const [contrasena, setContrasena] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const { login } = useAuth()
  const navigate = useNavigate()

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      try {
        const user = await usuarioService.loginUser(gmail, contrasena)
        login({ ...user, tipo: 'usuario' })
        navigate('/')
        return
      } catch {
        // no es usuario, intentar como admin
      }

      const admin = await usuarioService.loginAdmin(gmail, contrasena)
      login({ ...admin, tipo: 'admin' })
      navigate('/admin')
    } catch {
      setError('Credenciales incorrectas')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="login-page">
      <div className="login-card">
        <div className="login-logo">
          <img src={logoImg} alt="Plaza Música" className="login-logo-img" />
        </div>
        <h1 className="login-title">Plaza Música</h1>
        <form onSubmit={handleSubmit} className="login-form">
          <p className="login-hint">Ingrese gmail o nombre de usuario</p>
          <input
            type="text"
            placeholder="Gmail o nombre de usuario"
            value={gmail}
            onChange={(e) => setGmail(e.target.value)}
            required
            className="login-input"
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={contrasena}
            onChange={(e) => setContrasena(e.target.value)}
            required
            className="login-input"
          />
          <a href="#" className="login-forgot">¿Se te olvidó la contraseña?</a>
          {error && <p className="login-error">{error}</p>}
          <button type="submit" className="login-btn" disabled={loading}>
            {loading ? 'Verificando...' : 'Verificar'}
          </button>
        </form>
        <p className="login-register">
          ¿No tienes cuenta? <a href="#">¡Haz click aquí!</a>
        </p>
      </div>
    </div>
  )
}
