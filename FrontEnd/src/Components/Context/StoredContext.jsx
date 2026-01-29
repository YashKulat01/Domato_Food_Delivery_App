import { createContext, useCallback, useEffect, useState } from "react";
import { api } from "../../api";
import { backendToFrontendCategory } from "../../utils/categoryMap";

const BASE_URL = "http://localhost:8080";

export const storeContext = createContext(null);

const StoreContextProvider = (props) => {
  const [food_list, setFoodList] = useState([]);
  const [cartItems, setCartItems] = useState({});
  const [user, setUser] = useState(() => {
    try {
      return JSON.parse(localStorage.getItem("user") || "null");
    } catch {
      return null;
    }
  });
  const [loading, setLoading] = useState(true);

  const loadFoods = useCallback(async () => {
    try {
      const { data } = await api.get("/food/get_foods");
      const mapped = (data || []).map((f) => ({
        _id: f.foodId,
        foodId: f.foodId,
        name: f.foodName,
        image: f.foodImg ? (f.foodImg.startsWith("http") ? f.foodImg : `${BASE_URL}${f.foodImg}`) : "",
        price: f.foodPrice,
        description: f.foodDesc || "",
        category: backendToFrontendCategory[f.foodCategory] || f.foodCategory || "All",
      }));
      setFoodList(mapped);
    } catch (e) {
      console.error("Failed to load foods", e);
      setFoodList([]);
    } finally {
      setLoading(false);
    }
  }, []);

  const loadCart = useCallback(async () => {
    if (!user?.id) {
      setCartItems({});
      return;
    }
    try {
      const { data } = await api.get(`/cart/get_cart/${user.id}`);
      const items = {};
      (data?.items || []).forEach((item) => {
        items[item.foodId] = item.quantity;
      });
      setCartItems(items);
    } catch (e) {
      if (e.response?.status === 404 || e.response?.status === 500) {
        setCartItems({});
      }
    }
  }, [user?.id]);

  useEffect(() => {
    loadFoods();
  }, [loadFoods]);

  useEffect(() => {
    const u = () => {
      try {
        setUser(JSON.parse(localStorage.getItem("user") || "null"));
      } catch {
        setUser(null);
      }
    };
    window.addEventListener("storage", u);
    u();
    return () => window.removeEventListener("storage", u);
  }, []);

  useEffect(() => {
    loadCart();
  }, [loadCart]);

  const addToCart = async (itemId) => {
    const foodId = typeof itemId === "number" ? itemId : parseInt(itemId, 10);
    if (!user?.id) {
      alert("Please login to add to cart");
      return;
    }
    try {
      await api.post("/cart/add_cart", null, {
        params: { userId: user.id, foodId, quantity: 1 },
      });
      setCartItems((prev) => ({ ...prev, [foodId]: (prev[foodId] || 0) + 1 }));
    } catch (e) {
      console.error(e);
      alert(e.response?.data?.message || "Failed to add to cart");
    }
  };

  const removeFromCart = async (itemId) => {
    const foodId = typeof itemId === "number" ? itemId : parseInt(itemId, 10);
    if (!user?.id) return;
    const current = cartItems[foodId] || 0;
    if (current <= 1) {
      try {
        await api.delete("/cart/remove_item", { params: { userId: user.id, foodId } });
        setCartItems((prev) => {
          const next = { ...prev };
          delete next[foodId];
          return next;
        });
      } catch (e) {
        console.error(e);
      }
      return;
    }
    try {
      await api.delete("/cart/remove_item", { params: { userId: user.id, foodId } });
      await api.post("/cart/add_cart", null, {
        params: { userId: user.id, foodId, quantity: current - 1 },
      });
      setCartItems((prev) => ({ ...prev, [foodId]: current - 1 }));
    } catch (e) {
      console.error(e);
    }
  };

  const getTotalCartAmount = () => {
    let total = 0;
    for (const id in cartItems) {
      if (cartItems[id] > 0) {
        const food = food_list.find((f) => f._id === parseInt(id, 10) || f._id === id);
        if (food) total += food.price * cartItems[id];
      }
    }
    return total;
  };

  const refreshCart = () => loadCart();

  const contextValue = {
    food_list,
    cartItems,
    setCartItems,
    addToCart,
    removeFromCart,
    getTotalCartAmount,
    user,
    setUser,
    loading,
    refreshCart,
    loadFoods,
  };

  return (
    <storeContext.Provider value={contextValue}>
      {props.children}
    </storeContext.Provider>
  );
};

export default StoreContextProvider;
