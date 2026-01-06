import React, { useState } from 'react'
import './NavBar.css'
import { assets } from '../../assets/assets.js'

export default function NavBar() {

    const [menu, setMenu] = useState("Home");
    return (
        <div className='navBar'>
            <img src={assets.logo}
                alt="webLogo"
                className='logo' />
            <ul className="navBarMenu">
                <li onClick={()=>setMenu("Home")} className={menu === "Home" ? "active" : ""}>Home</li>
                <li onClick={()=>setMenu("Menu")} className={menu === "Menu" ? "active" : ""}>Menu</li>
                <li onClick={()=>setMenu("MobileApp")} className={menu === "MobileApp" ? "active" : ""}>Mobile App</li>
                <li onClick={()=>setMenu("ContactUs")} className={menu === "ContactUs" ? "active" : ""}>Contact Us</li>
            </ul>
            <div className="navBarRight">
                <img src={assets.search_icon}
                    alt="searchIcon" />

                <div className="navBarSearchIcon">
                    <img src={assets.basket_icon}
                        alt="basketIcon" />
                    <div className="dot"></div>
                </div>
                <button>Sign in</button>
            </div>
        </div>
    )
}
