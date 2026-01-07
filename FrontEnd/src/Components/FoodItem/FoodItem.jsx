import React, { useContext} from 'react'

import './FoodItem.css'
import { assets } from '../../assets/assets'
import { storeContext } from '../Context/StoredContext';

export default function FoodItem({ id, name, price, description, image }) {

    const { cartItems, addToCart, removeFromCart } = useContext(storeContext);

    return (
        <div className='foodItem'>
            <div className="foodItemImageCont">
                <img className='foodItemImg'
                    src={image}
                    alt="" />
                {!cartItems[id]
                    ? <img className='add'
                        src={assets.add_icon_white}
                        onClick={() => addToCart(id)} alt="" />
                    : <div className='foodItemCounter'>
                        <img onClick={() => removeFromCart(id)}
                            src={assets.remove_icon_red}
                            alt="" />
                        <p>{cartItems[id]}</p>
                        <img onClick={() => addToCart(id)}
                            src={assets.add_icon_green}
                            alt="" />
                    </div>

                }
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
                    ${price}
                </p>
            </div>
        </div>
    )
}
