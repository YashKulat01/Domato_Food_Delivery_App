import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './App.css'
import Layout from './Pages/Routes/Layout'
import Home from './Pages/Home/Home'
import PlaceOrder from './Pages/PlaceOrder/PlaceOrder'
import Cart from './Pages/Cart/Cart'
import StoreContextProvider from './Components/Context/StoredContext'
import Footer from './Components/Footer/Footer'

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
          element: <Cart/>
        },
        // Place Order Path........................
        {
          path: "/placeOrder",
          element: <PlaceOrder />
        },
      ]
    }
  ])

  return (
    <>
      <div className='app'>
        <RouterProvider router={routes} />
        <StoreContextProvider />
      </div>
      <Footer />
    </>
  )
}

