import React from 'react'

import './FoodItem.css'
import { assets } from '../../assets/assets'

export default function FoodItem({ id, name, price, description, image }) {
    return (
        <div className='foodItem'>
            <div className="foodItemImageCont">
                <img className='foodItemImg'
                    src={image}
                    alt="" />
            </div>
            <div className="foodItemInfo">
                <div className="foodItemNameRating">
                    <p>{name}</p>
                    <img src={assets.rating_starts} alt="" />
                </div>
                <p className="foodItemDesc">
                    {description}
                </p>
                <p className="foodItemPrice">
                    ₹{price}
                </p>
            </div>
        </div>
    )
}
