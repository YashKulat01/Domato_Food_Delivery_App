import React, { useState } from 'react'
import './NavBar.css'
import { assets } from '../../assets/assets.js'

export default function NavBar() {

    const [menu, setMenu] = useState("Home");
    return (
        // navBar body
        <div className='navBar'>
            {/* NavBar Logo Section */}
            <img src={assets.logo}
                alt="webLogo"
                className='logo' />
            {/* NavBar Menu Section */}
            <ul className="navBarMenu">
                <li onClick={() => setMenu("Home")} className={menu === "Home" ? "active" : ""}>Home</li>
                <li onClick={() => setMenu("Menu")} className={menu === "Menu" ? "active" : ""}>Menu</li>
                <li onClick={() => setMenu("MobileApp")} className={menu === "MobileApp" ? "active" : ""}>Mobile App</li>
                <li onClick={() => setMenu("ContactUs")} className={menu === "ContactUs" ? "active" : ""}>Contact Us</li>
            </ul>
            {/* NavBar Right Section */}
            <div className="navBarRight">
                {/* NavBar Right Section/Search Section */}
                <img src={assets.search_icon}
                    alt="searchIcon" />

                <div className="navBarSearchIcon">
                    {/* NavBar Right Section/Basket Section */}
                    <img src={assets.basket_icon}
                        alt="basketIcon" />
                    <div className="dot"></div>
                </div>
                {/* NavBar Right Section/Sign in Section */}
                <button>Sign in</button>
            </div>
        </div>
    )
}
