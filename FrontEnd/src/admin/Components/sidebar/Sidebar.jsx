import React from 'react'
import './Sidebar.css'
import { assets } from '../../../assets/assets copy'
import { NavLink } from 'react-router-dom'

export default function Sidebar() {
  return (
    <div className='sidebar'>
        <div className="sidebarOptions">
            <NavLink to='/admin/add' className="sidebarOption">
                <img src={assets.add_icon} alt="" />
                <p>Add Items</p>
            </NavLink>

            <NavLink to='/admin/list' className="sidebarOption">
                <img src={assets.order_icon} alt="" />
                <p>List Items</p>
            </NavLink>

            <NavLink to='/admin/orders' className="sidebarOption">
                <img src={assets.order_icon} alt="" />
                <p>Orders</p>
            </NavLink>
        </div>
    </div>
  )
}
