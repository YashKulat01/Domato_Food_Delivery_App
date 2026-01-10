import React, { useContext } from 'react'

import './Cart.css'
import { storeContext } from '../../Components/Context/StoredContext'
import { useNavigate } from 'react-router-dom';

export default function Cart() {

  // USE CONTEXT SECTION
  const { cartItems, food_list, removeFromCart, getTotalCartAmount} = useContext(storeContext);

  //NAVIGATE SECTION...
  const navigate = useNavigate(); 

  return (
    <div className='cart'>
      <div className="cartItems">
        <div className="cartItemsTitle">
          <p>Items</p>
          <p>Title</p>
          <p>Price</p>
          <p>Quantity</p>
          <p>Total</p>
          <p>Remove</p>
        </div>
        <br />
        <hr />
        {food_list.map((item, index) => {
          if (cartItems[item._id] > 0) {
            return (
              <div key={index}>
                <div className="cartItemsTitle cartItemsItem">
                  <img src={item.image} alt="" />
                  <p>{item.name}</p>
                  <p>${item.price}</p>
                  <p>{cartItems[item._id]}</p>
                  <p>${item.price * cartItems[item._id]}</p>
                  <p className='cross'
                    onClick={() => removeFromCart(item._id)}
                    title='Remove item'>
                    X
                  </p>
                </div>
                <hr />
              </div>
            )
          }
        })}
      </div>
      {/* Cart Bottom details..................... */}
      <div className="cartBottom">
        {/* Cart Bottom/Cart Total Section................. */}
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
              <p>${getTotalCartAmount()===0?0:2}</p>
            </div>
            <hr />
            <div className="cartTotalDetails">
              <b>Total </b>
              <b>${getTotalCartAmount()===0?0: getTotalCartAmount() + 2}</b>
            </div>
          </div>
          <button onClick={()=>navigate('/placeOrder')}>Proceed To Checkout</button>
        </div>
        {/* Promo Code section......................... */}
        <div className="cartPromoCode">
          <div>
            <p>If you have a promo code, Enter it here</p>
            <div className="cartPromoCodeInput">
              <input type="text" placeholder='PromoCode' />
              <button>Submit</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
