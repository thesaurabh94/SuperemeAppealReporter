package com.SuperemeAppealReporter.api.converter;

import org.springframework.beans.BeanUtils;

import com.SuperemeAppealReporter.api.bo.AddCourtBo;
import com.SuperemeAppealReporter.api.bo.AddCourtBranchBo;
import com.SuperemeAppealReporter.api.bo.DeleteCourtBo;
import com.SuperemeAppealReporter.api.bo.DeleteCourtBranchBo;
import com.SuperemeAppealReporter.api.bo.GetCourtBo;
import com.SuperemeAppealReporter.api.ui.model.request.AddCourtBranchRequest;
import com.SuperemeAppealReporter.api.ui.model.request.AddCourtRequest;
import com.SuperemeAppealReporter.api.ui.model.request.DeleteCourtBranchRequest;
import com.SuperemeAppealReporter.api.ui.model.request.DeleteCourtRequest;
import com.SuperemeAppealReporter.api.ui.model.request.GetCourtRequest;

public class CourtConverter {

	public static AddCourtBo convertAddCourtRequestToAddCourtBo(AddCourtRequest addCourtRequest) {
		AddCourtBo addCourtBo = new AddCourtBo();
		BeanUtils.copyProperties(addCourtRequest, addCourtBo);
		return addCourtBo;
	}

	public static DeleteCourtBo convertDeleteCourtRequestToDeleteCourtBo(DeleteCourtRequest deleteCourtRequest) {
		DeleteCourtBo deleteCourtBo = new DeleteCourtBo();
		BeanUtils.copyProperties(deleteCourtRequest, deleteCourtBo);
		return deleteCourtBo;
	}

	public static AddCourtBranchBo convertAddCourtBranchRequestToAddCourtBranchBo(AddCourtBranchRequest addCourtBranchRequest) {
		AddCourtBranchBo addCourtBo = new AddCourtBranchBo();
		BeanUtils.copyProperties(addCourtBranchRequest, addCourtBo);
		return addCourtBo;
	}
	
	public static DeleteCourtBranchBo convertDeleteCourtBrnachRequestToDeleteCourtBranchBo(DeleteCourtBranchRequest deleteCourtRequest) {
		DeleteCourtBranchBo deleteCourtBo = new DeleteCourtBranchBo();
		BeanUtils.copyProperties(deleteCourtRequest, deleteCourtBo);
		return deleteCourtBo;
	}

	public static GetCourtBo convertGetCourtRequestToGetCourtBo(GetCourtRequest getCourtRequest) {
		GetCourtBo getCourtBo = new GetCourtBo();
		BeanUtils.copyProperties(getCourtRequest, getCourtBo);
		return getCourtBo;
	}
}
