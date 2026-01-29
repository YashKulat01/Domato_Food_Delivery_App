import React, { useEffect, useState } from "react";
import "./Orders.css";
import { api } from "../../../api";

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchOrders = async () => {
    try {
      const { data } = await api.get("/orders/get_all");
      setOrders(data || []);
    } catch (e) {
      console.error(e);
      setOrders([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchOrders();
  }, []);

  const updateStatus = async (orderId, action) => {
    try {
      await api.put(`/orders/${orderId}/${action}`);
      await fetchOrders();
    } catch (e) {
      alert(e.response?.data?.message || "Failed to update");
    }
  };

  const statusDisplay = {
    PLACED: "Placed",
    CONFIRMED: "Confirmed",
    PREPARING: "Preparing",
    DELIVERED: "Delivered",
    CANCELLED: "Cancelled",
  };

  const statusClass = (status) => {
    const s = (status || "").toLowerCase();
    if (s === "placed") return "placed";
    if (s === "confirmed") return "confirmed";
    if (s === "preparing") return "preparing";
    if (s === "delivered") return "delivered";
    if (s === "cancelled") return "cancelled";
    return "";
  };

  return (
    <div className="ordersAdmin">
      <h2>All Orders</h2>
      {loading ? (
        <p>Loading orders...</p>
      ) : (
        <div className="ordersList">
          {orders.length === 0 ? (
            <p>No orders yet.</p>
          ) : (
            orders.map((order) => (
              <div key={order.id} className="orderCard">
                <div className="orderHeader">
                  <span>Order #{order.id}</span>
                  <span>{new Date(order.orderDate).toLocaleString()}</span>
                  <span className={`orderStatus ${statusClass(order.orderStatus)}`}>
                    {statusDisplay[order.orderStatus] || order.orderStatus}
                  </span>
                </div>
                <div className="orderBody">
                  <p>Total: ${order.totalAmount?.toFixed(2)}</p>
                  {order.orderItems?.length > 0 && (
                    <ul>
                      {order.orderItems.map((item, i) => (
                        <li key={i}>
                          {item.foodName} × {item.quantity} — ${(item.foodPrice * item.quantity).toFixed(2)}
                        </li>
                      ))}
                    </ul>
                  )}
                </div>
                <div className="orderActions">
                  {order.orderStatus === "PLACED" && (
                    <>
                      <button onClick={() => updateStatus(order.id, "confirm")}>Confirm</button>
                      <button className="cancelBtn" onClick={() => updateStatus(order.id, "cancel")}>Cancel</button>
                    </>
                  )}
                  {order.orderStatus === "CONFIRMED" && (
                    <button onClick={() => updateStatus(order.id, "prepare")}>Mark Preparing</button>
                  )}
                  {order.orderStatus === "PREPARING" && (
                    <button onClick={() => updateStatus(order.id, "deliver")}>Mark Delivered</button>
                  )}
                </div>
              </div>
            ))
          )}
        </div>
      )}
    </div>
  );
}
