import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { api } from "../../api";

export default function Payment() {
  const { orderId } = useParams();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const load = async () => {
      try {
        // create razorpay order on backend
        const res = await api.post(`/razorpay/create/${orderId}`);
        const data = res.data;

        // load razorpay script
        const script = document.createElement("script");
        script.src = "https://checkout.razorpay.com/v1/checkout.js";
        script.async = true;
        document.body.appendChild(script);

        script.onload = () => {
          const markPaymentFailed = async () => {
            try {
              await api.post(`/razorpay/fail`, { internalOrderId: orderId });
            } catch (failErr) {
              console.error("Failed to mark payment as failed:", failErr);
            }
          };

          const options = {
            key: data.key,
            amount: data.amount,
            currency: data.currency,
            name: "Domato",
            description: "Order Payment",
            order_id: data.id,
            handler: async function (response) {
              try {
                // verify payment on backend
                await api.post(`/razorpay/verify`, {
                  paymentId: response.razorpay_payment_id,
                  orderId: response.razorpay_order_id,
                  signature: response.razorpay_signature,
                  internalOrderId: orderId,
                });
                alert("Payment successful");
                navigate("/my-orders");
              } catch (err) {
                console.error(err);
                await markPaymentFailed();
                alert("Payment verification failed");
                navigate("/my-orders");
              }
            },
            modal: {
              ondismiss: async function () {
                await markPaymentFailed();
                alert("Payment cancelled. Order was not placed.");
                navigate("/cart");
              },
            },
            prefill: {
              // optional: prefill customer info
            },
            theme: {
              color: "#F37254",
            },
          };

          const rzp1 = new window.Razorpay(options);
          rzp1.open();
        };

        setLoading(false);
      } catch (err) {
        console.error(err);
        try {
          await api.post(`/razorpay/fail`, { internalOrderId: orderId });
        } catch (failErr) {
          console.error("Failed to mark payment as failed:", failErr);
        }
        setError("Failed to create payment order");
        setLoading(false);
      }
    };

    load();
    // cleanup script if needed
    return () => {};
  }, [orderId, navigate]);

  if (loading) return <div style={{ padding: "2rem" }}>Preparing payment...</div>;
  if (error) return <div style={{ padding: "2rem", color: "red" }}>{error}</div>;
  return <div style={{ padding: "2rem" }}>Opening payment window...</div>;
}
