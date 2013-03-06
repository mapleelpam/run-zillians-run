package com.zillians.run_zillians_run.zs_flex_project.actions.upload;

public interface ServerMessages {
	final static int Server_Valid = 101;
	final static int Server_Invalid = 102;
	final static int Login_Success = 201;
	final static int AccoutOrPasswordError = 202;
	final static int GetProjectsSuccess = 301;
	final static int NoProjects = 302;
	final static int ProjectValid = 401;
	final static int ProjectInvalid = 402;
	final static int UploadSuccess = 501;
	final static int UploadFaild = 502;
	final static int Other = 0;
}
