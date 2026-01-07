import React from 'react'

import './Footer.css'
import { assets } from '../../assets/assets'

export default function Footer() {
    return (
        <div className='footer' id='footer'>
            {/* Footer Container */}
            <div className="footerCont">
                {/* Footer Container/Left Section */}
                <div className="footerContLeft">
                    <img src={assets.logo} alt="" />
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perspiciatis ex fuga, totam neque, expedita harum vero esse distinctio et pariatur hic, tenetur ad alias laudantium iure suscipit blanditiis dignissimos sequi.</p>
                    <div className="footerSocialIcon">
                        <img src={assets.facebook_icon} alt="" />
                        <img src={assets.twitter_icon} alt="" />
                        <img src={assets.linkedin_icon} alt="" />
                    </div>
                </div>
                {/* Footer Container/Center Section */}
                <div className="footerContCenter">
                    <h2>Company</h2>
                    <ul>
                        <li>Home</li>
                        <li>About us</li>
                        <li>Delivery</li>
                        <li>Privacy policy</li>
                    </ul>
                </div>
                {/* Footer Container/Right Section */}
                <div className="footerContRight">
                    <h2>Contact Us</h2>
                    <ul>
                        <li>+1-212-456-7890</li>
                        <li>contact@Domato.com</li>
                    </ul>
                </div>
            </div>
            <hr />
            <p className="footerCopyRight">
                Copyright 2026 © Domato.com - All Rights Reserved.
            </p>
        </div>
    )
}
