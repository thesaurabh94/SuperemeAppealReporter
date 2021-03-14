package com.SuperemeAppealReporter.api.converter;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;

import com.SuperemeAppealReporter.api.bo.AddPlanBo;
import com.SuperemeAppealReporter.api.bo.AddStaffBo;
import com.SuperemeAppealReporter.api.bo.DeleteClientBo;
import com.SuperemeAppealReporter.api.bo.DeletePlanBo;
import com.SuperemeAppealReporter.api.bo.DeleteStaffBo;
import com.SuperemeAppealReporter.api.bo.GetClientListBo;
import com.SuperemeAppealReporter.api.bo.GetPlanListBo;
import com.SuperemeAppealReporter.api.bo.GetStaffListBo;
import com.SuperemeAppealReporter.api.bo.SearchClientBo;
import com.SuperemeAppealReporter.api.bo.SearchStaffBo;
import com.SuperemeAppealReporter.api.bo.UpdateClientBo;
import com.SuperemeAppealReporter.api.bo.UserSignupBo;
import com.SuperemeAppealReporter.api.io.entity.SubscriptionPlanEntity;
import com.SuperemeAppealReporter.api.ui.model.request.AddClientRequest;
import com.SuperemeAppealReporter.api.ui.model.request.AddPlanRequest;
import com.SuperemeAppealReporter.api.ui.model.request.AddStaffRequest;
import com.SuperemeAppealReporter.api.ui.model.request.DeleteClientRequest;
import com.SuperemeAppealReporter.api.ui.model.request.DeletePlanRequest;
import com.SuperemeAppealReporter.api.ui.model.request.DeleteStaffRequest;
import com.SuperemeAppealReporter.api.ui.model.request.UpdateStaffRequest;
import com.SuperemeAppealReporter.api.ui.model.request.GetClientListRequest;
import com.SuperemeAppealReporter.api.ui.model.request.GetPlanListRequest;
import com.SuperemeAppealReporter.api.ui.model.request.GetStaffListRequest;
import com.SuperemeAppealReporter.api.ui.model.request.SearhClientRequest;
import com.SuperemeAppealReporter.api.ui.model.request.SearhStaffRequest;
import com.SuperemeAppealReporter.api.ui.model.request.UpdateClientRequest;
import com.SuperemeAppealReporter.api.bo.UpdateStaffBo;
public class AdminConverter {

	public static GetClientListBo convertGetClientListRequestToGetClientListResponse(GetClientListRequest getClientListRequest)
	{
		GetClientListBo getClientListBo = new GetClientListBo();
		BeanUtils.copyProperties(getClientListRequest, getClientListBo);
		return getClientListBo;
	}
	
	public static UserSignupBo convertAddClientRequestToUserSignupBo(AddClientRequest addClientRequest)
	{
		UserSignupBo userSignupBo = new UserSignupBo();
		BeanUtils.copyProperties(addClientRequest, userSignupBo);
		return userSignupBo;
	}

	public static GetStaffListBo convertGetStaffListRequestToGetStaffListResponse(GetStaffListRequest getStaffListRequest) {
		GetStaffListBo getStaffListBo = new GetStaffListBo();
		BeanUtils.copyProperties(getStaffListRequest, getStaffListBo);
		return getStaffListBo;
	}

	public static AddStaffBo convertAddStaffRequestToAddStaffBo(AddStaffRequest addStaffRequest) {
		AddStaffBo addStaffBo = new AddStaffBo();
		BeanUtils.copyProperties(addStaffRequest, addStaffBo);
		return addStaffBo;
	}
	
	public static DeleteStaffBo convertDeleteStaffRequestToDeleteStaffBo(DeleteStaffRequest deleteStaffRequest) {
		DeleteStaffBo deleteStaffBo = new DeleteStaffBo();
		BeanUtils.copyProperties(deleteStaffRequest, deleteStaffBo);
		return deleteStaffBo;
	}

	public static UpdateStaffBo convertUpdateStaffRequestToUpdateStaffBo(UpdateStaffRequest updateStaffRequest) {
		UpdateStaffBo updateStaffBo = new UpdateStaffBo();
		BeanUtils.copyProperties(updateStaffRequest, updateStaffBo);
		return updateStaffBo;
}

	public static DeleteClientBo convertDeleteClientRequestToDeleteClientBo(DeleteClientRequest deleteClientRequest) {
		DeleteClientBo deleteClientBo = new DeleteClientBo();
		BeanUtils.copyProperties(deleteClientRequest, deleteClientBo);
		return deleteClientBo;
	}

	public static UpdateClientBo convertUpdateClientRequestToUpdateClientBo(UpdateClientRequest updateClientRequest) {
		UpdateClientBo updateClientBo = new UpdateClientBo();
		BeanUtils.copyProperties(updateClientRequest, updateClientBo);
		return updateClientBo;
	}

	public static AddPlanBo convertAddPlanRequestToAddPlanBo(AddPlanRequest addPlanRequest) {
		AddPlanBo addPlanBo = new AddPlanBo();
		BeanUtils.copyProperties(addPlanRequest, addPlanBo);
		return addPlanBo;
	}

	public static SubscriptionPlanEntity convertAddPlanBoToPlanEntity(AddPlanBo addPlanBo) {
		SubscriptionPlanEntity planEntity = new SubscriptionPlanEntity();
		BeanUtils.copyProperties(addPlanBo, planEntity);
		return planEntity;
	}

	public static DeletePlanBo convertDeletePlanRequestToDeletePlanBo(DeletePlanRequest deletePlanRequest) {
		DeletePlanBo planBo = new DeletePlanBo();
		BeanUtils.copyProperties(deletePlanRequest, planBo);
		return planBo;
	}

	public static GetPlanListBo convertDeleteGetPlanListRequestToGetPlanListBo(GetPlanListRequest getPlanListRequest) {
		GetPlanListBo planBo = new GetPlanListBo();
		BeanUtils.copyProperties(getPlanListRequest, planBo);
		return planBo;
	}

	public static SearchClientBo convertSearchClientRequestToSearchClientBo(SearhClientRequest searchClientRequest) {
		SearchClientBo searchClientBo = new SearchClientBo();
		BeanUtils.copyProperties(searchClientRequest, searchClientBo);
		return searchClientBo;
	}

	public static SearchStaffBo convertSearchStaffRequestToSearchStaffBo(SearhStaffRequest searchStaffRequest) {
		SearchStaffBo searchStaffBo = new SearchStaffBo();
		BeanUtils.copyProperties(searchStaffRequest, searchStaffBo);
		return searchStaffBo;
	}
}