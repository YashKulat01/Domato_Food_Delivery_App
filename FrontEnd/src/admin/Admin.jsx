import React from 'react'
import NavBar from './Components/NavBar/NavBar'
import Sidebar from './Components/sidebar/Sidebar'
import { Outlet } from 'react-router-dom'

export default function Admin() {
  return (
    <>
        <NavBar/>
        <hr />
        <div className="appContent">
            <Sidebar/>
            <Outlet/>
        </div>
        
    </>
  )
}
