import React, { useState } from 'react'

import { Outlet } from 'react-router-dom'

import NavBar from '../../Components/NavBar/NavBar'
import Login from '../../Components/Login/Login';

export default function Layout() {

    // LOGIN SECTION..................
    const [login, setLogin] = useState(false);

    return (
        <>
            {login ? <Login setLogin={setLogin}/> : <></>}
            <NavBar setLogin={setLogin} />
            <Outlet />
        </>
    )
}
