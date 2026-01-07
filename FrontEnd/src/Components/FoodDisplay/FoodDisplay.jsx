import React, { useContext } from 'react'

import './FoodDisplay.css'
import { storeContext } from '../Context/StoredContext'
import FoodItem from '../FoodItem/FoodItem';

export default function FoodDisplay({ category }) {

    const { food_list } = useContext(storeContext);


    return (
        // Food Display Section...............................
        <div className='foodDisplay' id='foodDisplay'>
            <h2>Top Dishes Near You !!!</h2>
            <div className="foodDisplayList">
                {food_list.map((item, index) => {

                    if (category==="All" || category ===item.category) {
                        return <FoodItem key={index}
                        id={item._id}
                        name={item.name}
                        description={item.description}
                        price={item.price}
                        image={item.image} />
                    }

                })}
            </div>
        </div>
    )
}
