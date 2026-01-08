import React, { useState } from 'react'
import './NavBar.css'
import { assets } from '../../assets/assets.js'
import { Link } from 'react-router-dom';

export default function NavBar({setLogin}) {

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
                <Link to='/' onClick={() => setMenu("Home")} className={menu === "Home" ? "active" : ""}>Home</Link>
                <a href='#exploreMenu' onClick={() => setMenu("Menu")} className={menu === "Menu" ? "active" : ""}>Menu</a>
                <a href='#appDownload' onClick={() => setMenu("MobileApp")} className={menu === "MobileApp" ? "active" : ""}>Mobile App</a>
                <a href='#footer' onClick={() => setMenu("ContactUs")} className={menu === "ContactUs" ? "active" : ""}>Contact Us</a>
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
                <button onClick={()=>setLogin(true)}>Sign in</button>
            </div>
        </div>
    )
}
