import React, { useContext, useState } from "react";
import "./NavBar.css";
import { assets } from "../../assets/assets.js";
import { Link, useNavigate } from "react-router-dom";
import { storeContext } from "../Context/StoredContext.jsx";

export default function NavBar({ setLogin }) {
  const navigate = useNavigate();
  const [menu, setMenu] = useState("Home");
  const { getTotalCartAmount, user } = useContext(storeContext);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    window.dispatchEvent(new Event("storage"));
    navigate("/");
  };

  return (
    <div className="navBar">
      <Link to="/">
        <img src={assets.logo} alt="webLogo" className="logo" />
      </Link>
      <ul className="navBarMenu">
        <Link to="/" onClick={() => setMenu("Home")} className={menu === "Home" ? "active" : ""}>Home</Link>
        <a href="#exploreMenu" onClick={() => setMenu("Menu")} className={menu === "Menu" ? "active" : ""}>Menu</a>
        <Link to="/my-orders" onClick={() => setMenu("MyOrders")} className={menu === "MyOrders" ? "active" : ""}>My Orders</Link>
        <a href="#appDownload" onClick={() => setMenu("MobileApp")} className={menu === "MobileApp" ? "active" : ""}>Mobile App</a>
        <a href="#footer" onClick={() => setMenu("ContactUs")} className={menu === "ContactUs" ? "active" : ""}>Contact Us</a>
      </ul>
      <div className="navBarRight">
        <img src={assets.search_icon} alt="searchIcon" />
        <div className="navBarSearchIcon">
          <Link to="/cart">
            <img src={assets.basket_icon} alt="basketIcon" />
          </Link>
          <div className={getTotalCartAmount() === 0 ? "" : "dot"}></div>
        </div>
        {user ? (
          <button onClick={handleLogout}>Logout</button>
        ) : (
          <button onClick={() => setLogin(true)}>Sign in</button>
        )}
      </div>
    </div>
  );
}
