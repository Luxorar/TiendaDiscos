import { Routes, Route, Navigate } from 'react-router-dom'
import { useAuth } from './context/AuthContext'
import { CartProvider } from './context/CartContext'

import Login from './pages/Login'
import Home from './pages/Home'
import Busqueda from './pages/Busqueda'
import DiscoDetail from './pages/DiscoDetail'
import Carrito from './pages/Carrito'
import Checkout from './pages/Checkout'
import Perfil from './pages/Perfil'
import AdminDashboard from './pages/AdminDashboard'
import AdminUsuarios from './pages/AdminUsuarios'
import AdminProductos from './pages/AdminProductos'
import AdminDiscos from './pages/AdminDiscos'
import AdminDescuentos from './pages/AdminDescuentos'
import AdminEnvios from './pages/AdminEnvios'
import AdminStock from './pages/AdminStock'
import AdminSedes from './pages/AdminSedes'
import AdminVentas from './pages/AdminVentas'

function ProtectedRoute({ children }) {
  const { user } = useAuth()
  return user ? children : <Navigate to="/login" />
}

function AdminRoute({ children }) {
  const { user } = useAuth()
  if (!user) return <Navigate to="/login" />
  if (user.tipo !== 'admin') return <Navigate to="/" />
  return children
}

export default function App() {
  return (
    <CartProvider>
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<Home />} />
      <Route path="/busqueda" element={<Busqueda />} />
      <Route path="/disco/:id" element={<DiscoDetail />} />
      <Route path="/carrito" element={<ProtectedRoute><Carrito /></ProtectedRoute>} />
      <Route path="/checkout" element={<ProtectedRoute><Checkout /></ProtectedRoute>} />
      <Route path="/perfil" element={<ProtectedRoute><Perfil /></ProtectedRoute>} />
      <Route path="/admin" element={<AdminRoute><AdminDashboard /></AdminRoute>} />
      <Route path="/admin/usuarios" element={<AdminRoute><AdminUsuarios /></AdminRoute>} />
      <Route path="/admin/productos" element={<AdminRoute><AdminProductos /></AdminRoute>} />
      <Route path="/admin/discos" element={<AdminRoute><AdminDiscos /></AdminRoute>} />
      <Route path="/admin/descuentos" element={<AdminRoute><AdminDescuentos /></AdminRoute>} />
      <Route path="/admin/envios" element={<AdminRoute><AdminEnvios /></AdminRoute>} />
      <Route path="/admin/stock" element={<AdminRoute><AdminStock /></AdminRoute>} />
      <Route path="/admin/sedes" element={<AdminRoute><AdminSedes /></AdminRoute>} />
      <Route path="/admin/ventas" element={<AdminRoute><AdminVentas /></AdminRoute>} />
      <Route path="*" element={<Navigate to="/" />} />
    </Routes>
    </CartProvider>
  )
}
