import { createContext, useContext, useState, useEffect } from 'react'
import { usuarioService } from '../api/usuarioService'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem('user')
    return saved ? JSON.parse(saved) : null
  })

  useEffect(() => {
    if (user?.modoOscuro) {
      document.body.classList.add('dark-mode')
    } else {
      document.body.classList.remove('dark-mode')
    }
  }, [user?.modoOscuro])

  const login = (userData) => {
    setUser(userData)
    localStorage.setItem('user', JSON.stringify(userData))
    if (userData?.modoOscuro) {
      document.body.classList.add('dark-mode')
    } else {
      document.body.classList.remove('dark-mode')
    }
  }

  const logout = () => {
    setUser(null)
    localStorage.removeItem('user')
    document.body.classList.remove('dark-mode')
  }

  const toggleDarkMode = async () => {
    if (!user?.id) return
    const nuevoValor = !user.modoOscuro
    await usuarioService.putModoOscuro(user.id, nuevoValor)
    const updated = { ...user, modoOscuro: nuevoValor }
    setUser(updated)
    localStorage.setItem('user', JSON.stringify(updated))
  }

  return (
    <AuthContext.Provider value={{ user, login, logout, toggleDarkMode }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  return useContext(AuthContext)
}
