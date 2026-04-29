import React, { useContext, useState } from "react";
import "./NavBar.css";
import { assets } from "../../assets/assets.js";
import { Link, useNavigate } from "react-router-dom";
import { storeContext } from "../Context/StoredContext.jsx";

export default function NavBar({ setLogin }) {
  const navigate = useNavigate();
  const [menu, setMenu] = useState("Home");
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);
  const { getTotalCartAmount, user } = useContext(storeContext);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    window.dispatchEvent(new Event("storage"));
    navigate("/");
  };

  return (
    <div className="navBar">
      <button
        className="sidebarToggleButton"
        onClick={() => setIsSidebarOpen(true)}
        aria-label="Open menu"
      >
        &#9776;
      </button>
      <Link to="/">
        <img src={assets.logo} alt="webLogo" className="logo" />
      </Link>
      <ul className={`navBarMenu ${isSidebarOpen ? "sidebarOpen" : ""}`}>
        <div className="sidebarTopBrand">
          <img src={assets.logo} alt="Tomato logo" className="sidebarLogo" />
        </div>
        <button
          className="sidebarCloseButton"
          onClick={() => setIsSidebarOpen(false)}
          aria-label="Close menu"
        >
          &times;
        </button>
        <Link to="/" onClick={() => { setMenu("Home"); setIsSidebarOpen(false); }} className={menu === "Home" ? "active" : ""}>Home</Link>
        <a href="#exploreMenu" onClick={() => { setMenu("Menu"); setIsSidebarOpen(false); }} className={menu === "Menu" ? "active" : ""}>Menu</a>
        <Link to="/my-orders" onClick={() => { setMenu("MyOrders"); setIsSidebarOpen(false); }} className={menu === "MyOrders" ? "active" : ""}>My Orders</Link>
        <a href="#appDownload" onClick={() => { setMenu("MobileApp"); setIsSidebarOpen(false); }} className={menu === "MobileApp" ? "active" : ""}>Mobile App</a>
        <a href="#footer" onClick={() => { setMenu("ContactUs"); setIsSidebarOpen(false); }} className={menu === "ContactUs" ? "active" : ""}>Contact Us</a>
      </ul>
      {isSidebarOpen && <div className="sidebarOverlay" onClick={() => setIsSidebarOpen(false)}></div>}
      <div className="navBarRight">
        {/* <img src={assets.search_icon} alt="searchIcon" /> */}
        <div className="navBarSearchIcon">
          <Link to="/cart">
            <img src={assets.basket_icon} alt="basketIcon" />
          </Link>
          <div className={getTotalCartAmount() === 0 ? "" : "dot"}></div>
        </div>
        <div className="userSection">
          {user ? (
            <>
              <div className="usernameSection">
                <div><img src={assets.profile_icon} alt="" /></div>
                <>{user.name}</>
              </div>
              <button onClick={handleLogout}>Logout</button>
            </>
          ) : (
            <button onClick={() => setLogin(true)}>Sign in</button>
          )}
        </div>
      </div>
    </div>
  );
}
