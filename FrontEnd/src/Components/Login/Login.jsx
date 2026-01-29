import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";
import { assets } from "../../assets/assets";
import { api } from "../../api";

export default function Login({ setLogin }) {
  const navigate = useNavigate();
  const [currState, setCurrState] = useState("Sign Up");
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    phoneNo: "",
    agree: false
  });
  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value
    });
  };

  const validate = () => {
    let newErrors = {};
    if (currState === "Sign Up" && !formData.name.trim()) {
      newErrors.name = "Name is required";
    }
    if (currState === "Sign Up" && !formData.phoneNo.trim()) {
      newErrors.phoneNo = "Phone is required";
    }
    if (!formData.email.trim()) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "Invalid email format";
    }
    if (!formData.password) {
      newErrors.password = "Password is required";
    } else if (formData.password.length < 8) {
      newErrors.password = "Password must be at least 8 characters";
    }
    if (currState === "Sign Up" && !formData.agree) {
      newErrors.agree = "You must accept terms & conditions";
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    try {
      if (currState === "Sign Up") {
        const payload = {
          name: formData.name,
          email: formData.email,
          password: formData.password,
          phoneNo: formData.phoneNo
        };
        await api.post("/users/register", payload);
        alert("Account created! Please login.");
        setCurrState("Login");
        return;
      }

      const { data } = await api.post("/auth/login", {
        email: formData.email,
        password: formData.password
      });

      localStorage.setItem("token", data.token);
      localStorage.setItem("user", JSON.stringify(data.userDto));
      setLogin(false);

      const role = data.userDto?.role?.roleName;
      if (role === "ROLE_ADMIN") {
        navigate("/admin");
      } else {
        navigate("/");
      }
      window.dispatchEvent(new Event("storage"));
    } catch (error) {
      const msg = error.response?.data?.message || error.response?.data?.error || "Something went wrong.";
      setErrors({ api: msg });
    }
  };

  return (
    <div className="loginSec">
      <form className="loginCont" onSubmit={handleSubmit} noValidate>
        <div className="loginTitle">
          <h2>{currState}</h2>
          <img
            onClick={() => setLogin(false)}
            src={assets.cross_icon}
            alt=""
          />
        </div>

        <div className="loginInput">
          {currState === "Sign Up" && (
            <>
              <input
                type="text"
                placeholder="Your name"
                name="name"
                value={formData.name}
                onChange={handleChange}
              />
              {errors.name && <p className="error">{errors.name}</p>}
              <input
                type="phone"
                placeholder="Your Phone"
                name="phoneNo"
                value={formData.phoneNo}
                onChange={handleChange}
              />
              {errors.phoneNo && <p className="error">{errors.phoneNo}</p>}
            </>
          )}

          <input
            type="email"
            placeholder="Your Email"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          {errors.email && <p className="error">{errors.email}</p>}

          <input
            type="password"
            placeholder="Password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
          {errors.password && <p className="error">{errors.password}</p>}
        </div>

        {errors.api && <p className="error">{errors.api}</p>}
        <button type="submit">
          {currState === "Sign Up" ? "Create account" : "Login"}
        </button>

        <div className="loginCondition">
          <input
            type="checkbox"
            name="agree"
            checked={formData.agree}
            onChange={handleChange}
          />
          <p>By continuing, I agree to the terms of use & privacy policy.</p>
        </div>
        {errors.agree && <p className="error">{errors.agree}</p>}

        {currState === "Login" ? (
          <p>
            Create a new account?
            <span onClick={() => setCurrState("Sign Up")}> Click here</span>
          </p>
        ) : (
          <p>
            Already have an account?
            <span onClick={() => setCurrState("Login")}> Login here</span>
          </p>
        )}
      </form>
    </div>
  );
}