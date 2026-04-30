import React, { useContext, useEffect, useState } from "react";
import { toast } from "react-toastify";
import "./List.css";
import { api } from "../../../api";
import { storeContext } from "../../../Components/Context/StoredContext";

const BASE_URL = "http://localhost:8080";
const orangeToast = { style: { background: "#f97316", color: "#fff" } };

export default function List() {
  const { loadFoods, food_list } = useContext(storeContext);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadFoods();
  }, [loadFoods]);

  const handleDelete = async (foodName) => {
    if (!window.confirm(`Delete "${foodName}"?`)) return;
    setLoading(true);
    try {
      await api.delete(`/food/del_food/${encodeURIComponent(foodName)}`);
      await loadFoods();
    } catch (e) {
      toast(e.response?.data?.message || "Failed to delete", orangeToast);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="list">
      <h2>All Foods</h2>
      <div className="listTable">
        {food_list.length === 0 ? (
          <div className="listEmpty">
            <p>No items yet. Add your first item from the sidebar.</p>
          </div>
        ) : (
          <table>
            <thead>
              <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Category</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {food_list.map((item) => (
                <tr key={item.foodId || item._id}>
                  <td>
                    <img
                      src={
                        item.image
                          ? item.image.startsWith("http")
                            ? item.image
                            : `${BASE_URL}${item.image}`
                          : ""
                      }
                      alt={item.name}
                    />
                  </td>
                  <td><strong>{item.name}</strong></td>
                  <td>₹{Number(item.price).toFixed(2)}</td>
                  <td>{item.category}</td>
                  <td>
                    <button
                      type="button"
                      className="deleteBtn"
                      onClick={() => handleDelete(item.name)}
                      disabled={loading}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}
