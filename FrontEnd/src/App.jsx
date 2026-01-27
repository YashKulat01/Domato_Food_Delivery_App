import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './App.css'
import Layout from './Pages/Routes/Layout'
import Home from './Pages/Home/Home'
import PlaceOrder from './Pages/PlaceOrder/PlaceOrder'
import Cart from './Pages/Cart/Cart'
import StoreContextProvider from './Components/Context/StoredContext'
import Footer from './Components/Footer/Footer'
import Admin from './admin/Admin'
import Add from './admin/Pages/add/Add'
import List from './admin/Pages/list/List'
import Orders from './admin/Pages/Orders/Orders'

export default function App() {

  // ALL Routes.........................................
  const routes = createBrowserRouter([
    // Main Layout path.................................
    {
      path: "/",
      element: <Layout />,
      // Children Routes..............................
      children: [
        // HOme path........................
        {
          path: "/",
          element: <Home />
        },
        // Cart Path.........................
        {
          path: "/cart",
          element: <Cart />
        },
        // Place Order Path........................
        {
          path: "/placeOrder",
          element: <PlaceOrder />
        },
      ]
    },
    {
      path: "/admin",
      element: <Admin />,
      children:[
        {
          path:"/admin/add",
          element:<Add/>
        },
        {
          path:"/admin/list",
          element:<List/>
        },
        {
          path:"/admin/orders",
          element:<Orders/>
        }
      ]
    }
  ])

  return (
    <>
      <div className='app'>
        <RouterProvider router={routes} />
        <StoreContextProvider />
      </div>
    </>
  )
}

