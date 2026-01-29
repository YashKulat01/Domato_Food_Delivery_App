import React, { useState } from "react";
import NavBar from "./Components/NavBar/NavBar";
import Sidebar from "./Components/sidebar/Sidebar";
import { Outlet } from "react-router-dom";
import "./Admin.css";

export default function Admin() {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <div className="adminLayout">
      <NavBar onMenuClick={() => setSidebarOpen((o) => !o)} />
      <div className="adminContent">
        <Sidebar
          isOpen={sidebarOpen}
          onClose={() => setSidebarOpen(false)}
        />
        <div
          className="adminSidebarBackdrop"
          aria-hidden={!sidebarOpen}
          onClick={() => setSidebarOpen(false)}
        />
        <main className="adminMain">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
