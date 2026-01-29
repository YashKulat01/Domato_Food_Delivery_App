import React, { useContext, useEffect, useState } from "react";
import "./MyOrders.css";
import { api } from "../../api";
import { storeContext } from "../../Components/Context/StoredContext";

const statusDisplay = {
  PLACED: "Placed",
  CONFIRMED: "Confirmed",
  PREPARING: "Preparing",
  DELIVERED: "Delivered",
  CANCELLED: "Cancelled",
};

export default function MyOrders() {
  const { user } = useContext(storeContext);
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!user?.id) {
      setLoading(false);
      return;
    }
    api
      .get(`/orders/get_orders/${user.id}`)
      .then(({ data }) => setOrders(data || []))
      .catch(() => setOrders([]))
      .finally(() => setLoading(false));
  }, [user?.id]);

  if (!user) {
    return (
      <div className="myOrders">
        <p style={{ padding: "2rem", textAlign: "center" }}>
          Please log in to see your orders.
        </p>
      </div>
    );
  }

  if (loading) return <div className="myOrders"><p>Loading orders...</p></div>;

  return (
    <div className="myOrders">
      <h2>My Orders</h2>
      {orders.length === 0 ? (
        <p>No orders yet.</p>
      ) : (
        <div className="myOrdersList">
          {orders.map((order) => (
            <div key={order.id} className="orderCard">
              <div className="orderHeader">
                <span>Order #{order.id}</span>
                <span>{new Date(order.orderDate).toLocaleString()}</span>
                <strong>{statusDisplay[order.orderStatus] || order.orderStatus}</strong>
              </div>
              <div className="orderBody">
                <p>Total: ${order.totalAmount?.toFixed(2)}</p>
                {order.orderItems?.length > 0 && (
                  <ul>
                    {order.orderItems.map((item, i) => (
                      <li key={i}>
                        {item.foodName} x {item.quantity} - $
                        {(item.foodPrice * item.quantity).toFixed(2)}
                      </li>
                    ))}
                  </ul>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
