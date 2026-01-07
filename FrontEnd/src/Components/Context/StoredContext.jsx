import { createContext, useEffect, useState } from "react";
import { food_list } from "../../assets/assets";

export const storeContext = createContext("null")

const StoreContextProvider = (props) => {

    // CONTEXT FOR MANAGING CART ITEMS...
    // ADDING ITEM FOR THE FIRST TIME..
    const [cartItems, setCartItems] = useState({});

    const addToCart = (itemId) => {
        //CREATE A NEW ENTRY FOR PRODUCT IF NOT AVAILABLE....
        if (!cartItems[itemId]) {
            setCartItems((prev) => ({ ...prev, [itemId]: 1 }))
        } else {
            setCartItems((prev) => ({ ...prev, [itemId]: prev[itemId] + 1 }))
        }
    }

    // FOR REMOVING FROM CART...
    const removeFromCart = (itemId) => {
        setCartItems((prev) => ({ ...prev, [itemId]: prev[itemId] - 1 }))
    }

    useEffect(()=>{
        console.log(cartItems);
    },[cartItems])

    // CONTEXT FOR GETTING ALL PRODUCT OBJECTS...
    const contextValue = {
        food_list,
        cartItems,
        setCartItems,
        addToCart,
        removeFromCart
    }

    return (
        <storeContext.Provider value={contextValue}>
            {props.children}
        </storeContext.Provider>
    )
}

export default StoreContextProvider;