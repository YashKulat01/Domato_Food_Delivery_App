import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./PlaceOrder.css";
import { storeContext } from "../../Components/Context/StoredContext";
import { api } from "../../api";

export default function PlaceOrder() {
  const navigate = useNavigate();
  const { getTotalCartAmount, user, refreshCart } = useContext(storeContext);
  const [placing, setPlacing] = useState(false);
  const [error, setError] = useState("");

  const subtotal = getTotalCartAmount();
  const deliveryFee = subtotal === 0 ? 0 : 2;
  const total = subtotal + deliveryFee;

  const handlePlaceOrder = async (e) => {
    e.preventDefault();
    if (!user?.id) {
      setError("Please log in to place an order.");
      return;
    }
    if (subtotal === 0) {
      setError("Your cart is empty.");
      return;
    }
    setError("");
    setPlacing(true);
    try {
      await api.post(`/orders/place/${user.id}`);
      refreshCart();
      alert("Order placed successfully!");
      navigate("/my-orders");
    } catch (err) {
      setError(err.response?.data?.message || "Failed to place order.");
    } finally {
      setPlacing(false);
    }
  };

  if (!user) {
    return (
      <div className="placeOrder">
        <p style={{ padding: "2rem", textAlign: "center" }}>
          Please log in to place an order.
        </p>
      </div>
    );
  }

  return (
    <form className="placeOrder" onSubmit={handlePlaceOrder}>
      <div className="placeOrderLeft">
        <p className="title">Delivery Information</p>
        <div className="multiFields">
          <input type="text" placeholder="First Name" />
          <input type="text" placeholder="Last Name" />
        </div>
        <input type="email" placeholder="Email Address" defaultValue={user.email} />
        <input type="text" placeholder="Street" />

        <div className="multiFields">
          <input type="text" placeholder="City" />
          <input type="text" placeholder="State" />
        </div>

        <div className="multiFields">
          <input type="text" placeholder="Zip code" />
          <input type="text" placeholder="Country" />
        </div>
        <input type="text" placeholder="Phone" defaultValue={user.phoneNo} />
      </div>

      <div className="placeOrderRight">
        <div className="cartTotal">
          <h2>Cart Totals</h2>
          <div>
            <div className="cartTotalDetails">
              <p>Subtotal</p>
              <p>${subtotal.toFixed(2)}</p>
            </div>
            <hr />
            <div className="cartTotalDetails">
              <p>Delivery Fee</p>
              <p>${deliveryFee}</p>
            </div>
            <hr />
            <div className="cartTotalDetails">
              <b>Total</b>
              <b>${total.toFixed(2)}</b>
            </div>
          </div>
          {error && <p style={{ color: "red" }}>{error}</p>}
          <button type="submit" disabled={placing || subtotal === 0}>
            {placing ? "Placing Order..." : "Place Order"}
          </button>
        </div>
      </div>
    </form>
  );
}
