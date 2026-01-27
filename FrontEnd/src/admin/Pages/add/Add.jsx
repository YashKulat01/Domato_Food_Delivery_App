import React, { useState } from 'react'

import './Add.css';
import { assets } from '../../../assets/assets copy';

export default function Add() {

    const [image,setImage] = useState(false);
    return (
        <div className='add'>
            <form className='flex-col'>
                <div className="addImageUpload flex-col">
                    <p>Upload Image</p>
                    <label htmlFor="image">
                        <img src={image?URL.createObjectURL(image):assets.upload_area} alt="" />
                    </label>
                    <input onChange={(e)=>setImage(e.target.files[0])} type="file" id='image' hidden required />
                </div>

                <div className="addProductName flex-col">
                    <p>Product Name</p>
                    <input type="text" name='name' placeholder='Enter here' />
                </div>

                <div className="addProductDescription flex-col">
                    <p>Product Description</p>
                    <textarea name='description' rows='6'
                        placeholder='Write content' />
                </div>

                <div className="addCategoryPrice">
                    <div className="addCategory flex-col">
                        <p>Product Category</p>
                        <select name="category">
                            <option value="Salad">Salad</option>
                            <option value="Rolls">Rolls</option>
                            <option value="Deserts">Deserts</option>
                            <option value="Sandwitch">Sandwitch</option>
                            <option value="Cake">Cake</option>
                            <option value="Pure Veg">Pure Veg</option>
                            <option value="Pasta">Pasta</option>
                            <option value="Noodles">Noodles</option>
                        </select>
                    </div>
                    <div className="addPrice flex-col">
                        <p>Product Price</p>
                        <input type="Number" name='price'
                            placeholder='$20' />
                    </div>
                </div>
                <button type='submit' className='addBtn'>
                    Add
                </button>
            </form>
        </div>
    )
}
