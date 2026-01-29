import React, { useContext } from "react";
import "./FoodDisplay.css";
import { storeContext } from "../Context/StoredContext";
import FoodItem from "../FoodItem/FoodItem";

export default function FoodDisplay({ category }) {
  const { food_list, loading } = useContext(storeContext);
  const filtered = food_list.filter(
    (item) => category === "All" || category === item.category
  );

  return (
    <div className="foodDisplay" id="foodDisplay">
      <h2>Top Dishes Near You !!!</h2>
      {loading ? (
        <p>Loading menu...</p>
      ) : filtered.length === 0 ? (
        <p>No dishes in this category.</p>
      ) : (
        <div className="foodDisplayList">
          {filtered.map((item, index) => (
            <FoodItem
              key={item.foodId ?? item._id ?? index}
              id={item._id ?? item.foodId}
              name={item.name}
              description={item.description}
              price={item.price}
              image={item.image}
            />
          ))}
        </div>
      )}
    </div>
  );
}
