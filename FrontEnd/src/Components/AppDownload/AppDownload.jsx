import React from 'react'

import './AppDownload.css'
import { assets } from '../../assets/assets'

export default function AppDownload() {
  return (
    <div className='appDownload' id='appDownload'>
        <p>For Better Experience Download <br /> Domato App</p>
        <div className="appDownloadPlatforms">
            <img src={assets.play_store} alt="Play_store_icon" />
            <img src={assets.app_store} alt="App_store_icon" />
        </div>
    </div>
  )
}
