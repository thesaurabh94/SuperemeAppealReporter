package com.SuperemeAppealReporter.api.service;

import com.SuperemeAppealReporter.api.bo.ConfirmPaymentBo;
import com.SuperemeAppealReporter.api.bo.GetPaymentStatusBo;
import com.SuperemeAppealReporter.api.bo.InitiatePaymentBo;
import com.SuperemeAppealReporter.api.ui.model.request.PlaceNewOrderRequest;
import com.SuperemeAppealReporter.api.ui.model.response.GetPaymentStatusResponse;
import com.SuperemeAppealReporter.api.ui.model.response.InitiatePaymentResponse;

public interface PaymentService {

	public InitiatePaymentResponse initiatePaymentService(InitiatePaymentBo initiatePaymentBo);
	
	public GetPaymentStatusResponse getPaymentStatusAndCapturePayment(ConfirmPaymentBo confirmPaymentBo);

	public InitiatePaymentResponse placeNewOrder(PlaceNewOrderRequest request);
}
