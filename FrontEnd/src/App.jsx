import { createBrowserRouter, Navigate, RouterProvider } from "react-router-dom";
import "./App.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Layout from "./Pages/Routes/Layout";
import Home from "./Pages/Home/Home";
import PlaceOrder from "./Pages/PlaceOrder/PlaceOrder";
import Cart from "./Pages/Cart/Cart";
import MyOrders from "./Pages/MyOrders/MyOrders";
import Payment from "./Pages/Payment/Payment";
import StoreContextProvider from "./Components/Context/StoredContext";
import Admin from "./admin/Admin";
import Add from "./admin/Pages/add/Add";
import List from "./admin/Pages/list/List";
import Orders from "./admin/Pages/Orders/Orders";

function AdminGuard({ children }) {
  try {
    const user = JSON.parse(localStorage.getItem("user") || "null");
    const isAdmin = user?.role?.roleName === "ROLE_ADMIN";
    if (!user || !isAdmin) return <Navigate to="/" replace />;
    return children;
  } catch {
    return <Navigate to="/" replace />;
  }
}

export default function App() {
  const routes = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      children: [
        { path: "/", element: <Home /> },
        { path: "/cart", element: <Cart /> },
        { path: "/placeOrder", element: <PlaceOrder /> },
        { path: "/payment/:orderId", element: <Payment /> },
        { path: "/my-orders", element: <MyOrders /> },
      ],
    },
    {
      path: "/admin",
      element: (
        <AdminGuard>
          <Admin />
        </AdminGuard>
      ),
      children: [
        { path: "/admin/add", element: <Add /> },
        { path: "/admin/list", element: <List /> },
        { path: "/admin/orders", element: <Orders /> },
      ],
    },
  ]);

  return (
    <div className='app'>
      <StoreContextProvider>
        <RouterProvider router={routes} />
        <ToastContainer
          position="top-right"
          autoClose={3000}
          toastStyle={{ background: "#f97316", color: "#fff" }}
        />
      </StoreContextProvider>
    </div>
  )
}

