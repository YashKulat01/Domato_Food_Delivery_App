import React from "react";
import { useNavigate } from "react-router-dom";
import { assets } from "../../../assets/assets";
import "./NavBar.css";

export default function NavBar({ onMenuClick }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    navigate("/");
    window.location.reload();
  };

  return (
    <header className="navbar">
      <button
        type="button"
        className="navbarMenuBtn"
        onClick={onMenuClick}
        aria-label="Open menu"
      >
        <span />
        <span />
        <span />
      </button>
      <img className="logo" src={assets.logo} alt="Domato" />
      <span className="navbarTitle">Admin Panel</span>
      <div className="navbarRight">
        <img className="profile" src={assets.profile_icon} alt="Profile" />
        <button type="button" className="logoutBtn" onClick={handleLogout}>
          Logout
        </button>
      </div>
    </header>
  );
}
