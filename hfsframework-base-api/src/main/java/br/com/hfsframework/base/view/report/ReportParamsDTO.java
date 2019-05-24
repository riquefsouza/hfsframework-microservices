package br.com.hfsframework.base.view.report;

import javax.validation.constraints.NotBlank;

public class ReportParamsDTO {

	@NotBlank
	private String reportType;

	@NotBlank
	private String forceDownload;

	@NotBlank
	private String reportName;

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getForceDownload() {
		return forceDownload;
	}

	public void setForceDownload(String forceDownload) {
		this.forceDownload = forceDownload;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	@Override
	public String toString() {
		return "ReportParamsDTO [reportType=" + reportType + ", forceDownload=" + forceDownload + ", reportName="
				+ reportName + "]";
	}
	
}
