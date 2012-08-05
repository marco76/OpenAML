package ch.genidea.checknames.utility;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class UploadFileListJob extends QuartzJobBean {

	private UploadFileUtility uploadUtility;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
				uploadUtility.uploadFileList();
		// TODO Auto-generated method stub
		
	}

	public void setUploadUtility(UploadFileUtility uploadUtility) {
		this.uploadUtility = uploadUtility;
	}

	
	
	

}
