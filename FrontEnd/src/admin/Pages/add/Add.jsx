import React, { useState } from "react";
import "./Add.css";
import { assets } from "../../../assets/assets";
import { api } from "../../../api";
import { frontendToBackendCategory } from "../../../utils/categoryMap";

const categoryOptions = [
  "Salad",
  "Rolls",
  "Deserts",
  "Sandwich",
  "Cake",
  "Pure Veg",
  "Pasta",
  "Noodles",
];

export default function Add() {
  const [image, setImage] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    const form = e.target;
    const name = form.name?.value?.trim();
    const description = form.description?.value?.trim();
    const category = form.category?.value;
    const price = parseFloat(form.price?.value);

    if (!name || !description || !category || !price || price <= 0) {
      setError("Please fill all fields and set a valid price.");
      return;
    }
    if (!image) {
      setError("Please upload an image.");
      return;
    }

    setLoading(true);
    try {
      const formData = new FormData();
      formData.append("image", image);
      formData.append("foodName", name);
      formData.append("foodDesc", description);
      formData.append("foodCategory", frontendToBackendCategory[category] || category);
      formData.append("foodPrice", price);

      await api.post("/food/add_food_with_image", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      alert("Food added successfully!");
      form.reset();
      setImage(null);
    } catch (err) {
      setError(err.response?.data?.message || "Failed to add food.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="add">
      <h2>Add New Item</h2>
      <form className="flex-col" onSubmit={handleSubmit}>
        <div className="addImageUpload flex-col">
          <p>Upload Image</p>
          <label htmlFor="image">
            <img
              src={image ? URL.createObjectURL(image) : assets.upload_area}
              alt=""
            />
          </label>
          <input
            onChange={(e) => setImage(e.target.files?.[0] || null)}
            type="file"
            id="image"
            hidden
            accept="image/*"
          />
        </div>

        <div className="addProductName flex-col">
          <p>Product Name</p>
          <input type="text" name="name" placeholder="Enter here" />
        </div>

        <div className="addProductDescription flex-col">
          <p>Product Description</p>
          <textarea name="description" rows="6" placeholder="Write content" />
        </div>

        <div className="addCategoryPrice">
          <div className="addCategory flex-col">
            <p>Product Category</p>
            <select name="category">
              {categoryOptions.map((cat) => (
                <option key={cat} value={cat}>
                  {cat}
                </option>
              ))}
            </select>
          </div>
          <div className="addPrice flex-col">
            <p>Product Price</p>
            <input type="number" name="price" placeholder="$20" step="0.01" min="0" />
          </div>
        </div>

        {error && <p className="error" style={{ color: "red" }}>{error}</p>}
        <button type="submit" className="addBtn" disabled={loading}>
          {loading ? "Adding..." : "Add"}
        </button>
      </form>
    </div>
  );
}
