import React from "react";
import "./Sidebar.css";
import { assets } from "../../../assets/assets";
import { NavLink } from "react-router-dom";

export default function Sidebar({ isOpen, onClose }) {
  const linkClass = ({ isActive }) => "sidebarOption" + (isActive ? " active" : "");

  return (
    <aside className={`sidebar ${isOpen ? "sidebarOpen" : ""}`} aria-hidden={!isOpen}>
      <div className="sidebarOptions">
        <span className="sidebarLabel">Menu</span>
        <NavLink to="/admin/add" className={linkClass} onClick={onClose}>
          <img src={assets.add_icon} alt="" />
          <p>Add Items</p>
        </NavLink>
        <NavLink to="/admin/list" className={linkClass} onClick={onClose}>
          <img src={assets.order_icon} alt="" />
          <p>List Items</p>
        </NavLink>
        <NavLink to="/admin/orders" className={linkClass} onClick={onClose}>
          <img src={assets.parcel_icon} alt="" />
          <p>Orders</p>
        </NavLink>
      </div>
    </aside>
  );
}
