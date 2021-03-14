package com.SuperemeAppealReporter.api.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchRequest {
	
	private String searchType;
	
	/**for search type = DashboardSearch**/
	private String dashboardSearchValue;
	
	
	/**for search type = MainSearch**/
	private String mainSearchCourtId;
	private String mainSearchDecisionDate;
	private String mainSearchCaseNumber;
	private String mainSearchDecidedYear;
	private String mainSearchPrimaryAppellant;
	private String mainSearchPrimaryRespondent;
	private String mainSearchActName;
	private String mainSearchSection;
	private String mainSearchTopic;
	private String mainSearchHeadNote;
	private String mainSearchJudgement;
	private String mainSearchAdvocate;
	private String mainSearchJudge;
	private String mainSearchCaseResult;
	private String mainSearchStartYear;
	private String mainSearchEndYear;
	
	
	/**for search type = CitationSearch**/
	private boolean isOtherSearch;
	private String citationSearchJournal;
	private String citationSearchYear;
	private String citationSearchPageNumber;
	private String citationCategory;
	
	//other citation variables 
	private String otherCitationSearchJournal;
	private String otherCitationSearchYear;
	private String otherCitationSearchVolume;
	private String otherCitationSearchPageNumber;
	
	
	/**for search type = StatueSearch**/
	private String statueSearchActName;
	private String statueSearchSection;
	private String statueSearchOrder;
	private String statueSearchRule;
	private String statueSearchArticle;
	
	
	/**for all search alphabet start**/
	private String startsWithAlphabet;
	
	/**for all search decidedDate order a->Ascending, d->Descedning **/
	private String decidedDateOrder;
	
	/**for all search appellant name order a->Ascending, d->Descedning **/
	private String appellantNameOrder;
	
	
	

	

}
