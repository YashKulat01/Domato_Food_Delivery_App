import React, { useContext } from 'react'

import './PlaceOrder.css'
import { storeContext } from '../../Components/Context/StoredContext'

export default function PlaceOrder() {

  const { getTotalCartAmount } = useContext(storeContext);

  return (
    <form className='placeOrder' action="">
      <div className='placeOrderLeft'>
        <p className="title">Delivery Information</p>
        <div className="multiFields">
          <input type="text" placeholder='First Name' required/>
          <input type="text" placeholder='Last Name' required/>
        </div>
        <input type="email" placeholder='Email Address' required/>
        <input type="text" placeholder='Street' required/>

        <div className="multiFields">
          <input type="text" placeholder='City' required/>
          <input type="text" placeholder='State' required/>
        </div>

        <div className="multiFields">
          <input type="text" placeholder='Zip code' required/>
          <input type="text" placeholder='Country' required/>
        </div>
        <input type="text" placeholder='Phone' required/>
      </div>


      <div className='placeOrderRight'>
        <div className="cartTotal">
          <h2>Cart Totals</h2>
          <div>
            <div className="cartTotalDetails">
              <p>Subtotal</p>
              <p>${getTotalCartAmount()}</p>
            </div>
            <hr />
            <div className="cartTotalDetails">
              <p>Delivery Fee</p>
              <p>${getTotalCartAmount() === 0 ? 0 : 2}</p>
            </div>
            <hr />
            <div className="cartTotalDetails">
              <b>Total </b>
              <b>${getTotalCartAmount() === 0 ? 0 : getTotalCartAmount() + 2}</b>
            </div>
          </div>
          <button onClick={() => navigate('/placeOrder')}>Proceed To Checkout</button>
        </div>
      </div>
    </form>
  )
}
