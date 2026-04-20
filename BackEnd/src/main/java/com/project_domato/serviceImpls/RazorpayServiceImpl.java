package com.project_domato.serviceImpls;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project_domato.services.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Service
public class RazorpayServiceImpl implements RazorpayService {

	@Value("${Razorpay.key}")
	private String razorpayKey;

	@Value("${Razorpay.secret}")
	private String razorpaySecret;

	@Override
	public Order createOrder(double amount) throws RazorpayException {
		RazorpayClient client = new RazorpayClient(razorpayKey, razorpaySecret);
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("amount", (int) Math.round(amount * 100));
		jsonObject.put("currency", "INR");
		jsonObject.put("receipt", "order_rcptid_" + System.currentTimeMillis());

		return client.orders.create(jsonObject);
	}

	@Override
	public boolean verifyPayment(String paymentId, String orderId, String signature) throws RazorpayException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("razorpay_order_id", orderId);
		jsonObject.put("razorpay_payment_id", paymentId);
		jsonObject.put("razorpay_signature", signature);

		return Utils.verifyPaymentSignature(jsonObject, razorpaySecret);
	}

}
