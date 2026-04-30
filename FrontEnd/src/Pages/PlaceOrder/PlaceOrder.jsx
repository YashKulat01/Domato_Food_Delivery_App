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
  const [fieldErrors, setFieldErrors] = useState({});
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: user?.email || "",
    street: "",
    city: "",
    state: "",
    zipCode: "",
    country: "",
    phone: user?.phoneNo || "",
  });

  const subtotal = getTotalCartAmount();
  const deliveryFee = subtotal === 0 ? 0 : 50;
  const total = subtotal + deliveryFee;

  const validateForm = () => {
    const errors = {};

    if (!formData.firstName.trim()) errors.firstName = "First name is required.";
    if (!formData.lastName.trim()) errors.lastName = "Last name is required.";
    if (!formData.email.trim()) {
      errors.email = "Email is required.";
    } else if (!/^\S+@\S+\.\S+$/.test(formData.email)) {
      errors.email = "Please enter a valid email.";
    }
    if (!formData.street.trim()) errors.street = "Street is required.";
    if (!formData.city.trim()) errors.city = "City is required.";
    if (!formData.state.trim()) errors.state = "State is required.";
    if (!formData.zipCode.trim()) {
      errors.zipCode = "Zip code is required.";
    } else if (!/^\d{6}$/.test(formData.zipCode)) {
      errors.zipCode = "Zip code must be exactly 6 digits.";
    }
    if (!formData.country.trim()) errors.country = "Country is required.";
    if (!formData.phone.trim()) {
      errors.phone = "Phone number is required.";
    } else if (!/^\d{10,}$/.test(formData.phone)) {
      errors.phone = "Phone number must be at least 10 digits.";
    }

    return errors;
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
    setFieldErrors((prev) => ({ ...prev, [name]: "" }));
  };

  const handlePlaceOrder = async (e) => {
    e.preventDefault();
    const validationErrors = validateForm();
    if (Object.keys(validationErrors).length > 0) {
      setFieldErrors(validationErrors);
      return;
    }
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
      const payload = {
        firstName: formData.firstName.trim(),
        lastName: formData.lastName.trim(),
        email: formData.email.trim(),
        street: formData.street.trim(),
        city: formData.city.trim(),
        state: formData.state.trim(),
        zipCode: parseInt(formData.zipCode, 10),
        country: formData.country.trim(),
        phone: parseInt(formData.phone, 10),
      };
      const res = await api.post(`/orders/place/${user.id}`, payload);
      const orderId = res.data.id;
      refreshCart();
      // navigate to payment page for the created order
      navigate(`/payment/${orderId}`);
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
          <div className="inputWrap">
            <input
              type="text"
              name="firstName"
              placeholder="First Name"
              value={formData.firstName}
              onChange={handleInputChange}
              className={fieldErrors.firstName ? "inputError" : ""}
            />
            {fieldErrors.firstName && <small className="errorText">{fieldErrors.firstName}</small>}
          </div>
          <div className="inputWrap">
            <input
              type="text"
              name="lastName"
              placeholder="Last Name"
              value={formData.lastName}
              onChange={handleInputChange}
              className={fieldErrors.lastName ? "inputError" : ""}
            />
            {fieldErrors.lastName && <small className="errorText">{fieldErrors.lastName}</small>}
          </div>
        </div>
        <div className="inputWrap">
          <input
            type="email"
            name="email"
            placeholder="Email Address"
            value={formData.email}
            onChange={handleInputChange}
            className={fieldErrors.email ? "inputError" : ""}
          />
          {fieldErrors.email && <small className="errorText">{fieldErrors.email}</small>}
        </div>
        <div className="inputWrap">
          <input
            type="text"
            name="street"
            placeholder="Street"
            value={formData.street}
            onChange={handleInputChange}
            className={fieldErrors.street ? "inputError" : ""}
          />
          {fieldErrors.street && <small className="errorText">{fieldErrors.street}</small>}
        </div>

        <div className="multiFields">
          <div className="inputWrap">
            <input
              type="text"
              name="city"
              placeholder="City"
              value={formData.city}
              onChange={handleInputChange}
              className={fieldErrors.city ? "inputError" : ""}
            />
            {fieldErrors.city && <small className="errorText">{fieldErrors.city}</small>}
          </div>
          <div className="inputWrap">
            <input
              type="text"
              name="state"
              placeholder="State"
              value={formData.state}
              onChange={handleInputChange}
              className={fieldErrors.state ? "inputError" : ""}
            />
            {fieldErrors.state && <small className="errorText">{fieldErrors.state}</small>}
          </div>
        </div>

        <div className="multiFields">
          <div className="inputWrap">
            <input
              type="text"
              name="zipCode"
              placeholder="Zip code"
              value={formData.zipCode}
              onChange={handleInputChange}
              maxLength={6}
              className={fieldErrors.zipCode ? "inputError" : ""}
            />
            {fieldErrors.zipCode && <small className="errorText">{fieldErrors.zipCode}</small>}
          </div>
          <div className="inputWrap">
            <input
              type="text"
              name="country"
              placeholder="Country"
              value={formData.country}
              onChange={handleInputChange}
              className={fieldErrors.country ? "inputError" : ""}
            />
            {fieldErrors.country && <small className="errorText">{fieldErrors.country}</small>}
          </div>
        </div>
        <div className="inputWrap">
          <input
            type="text"
            name="phone"
            placeholder="Phone"
            value={formData.phone}
            onChange={handleInputChange}
            className={fieldErrors.phone ? "inputError" : ""}
          />
          {fieldErrors.phone && <small className="errorText">{fieldErrors.phone}</small>}
        </div>
      </div>

      <div className="placeOrderRight">
        <div className="cartTotal">
          <h2>Cart Totals</h2>
          <div>
            <div className="cartTotalDetails">
              <p>Subtotal</p>
              <p>₹{subtotal.toFixed(2)}</p>
            </div>
            <hr />
            <div className="cartTotalDetails">
              <p>Delivery Fee</p>
              <p>₹{deliveryFee}</p>
            </div>
            <hr />
            <div className="cartTotalDetails">
              <b>Total</b>
              <b>₹{total.toFixed(2)}</b>
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
