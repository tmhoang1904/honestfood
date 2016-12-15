//package vn.com.arillance.cleanhome.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Properties;
//import java.util.UUID;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Component;
//
//import vn.com.arillance.cleanhome.dao.JobDao;
//import vn.com.arillance.cleanhome.dao.UserDao;
//import vn.com.arillance.cleanhome.exception.ErrorMessage;
//import vn.com.arillance.cleanhome.model.AuthModel;
//import vn.com.arillance.cleanhome.model.JobModel;
//import vn.com.arillance.cleanhome.model.JobTitle;
//import vn.com.arillance.cleanhome.model.ProfileModel;
//import vn.com.arillance.cleanhome.model.SearchJobModel;
//import vn.com.arillance.cleanhome.model.SearchModel;
//import vn.com.arillance.cleanhome.model.SkillSet;
//import vn.com.arillance.cleanhome.model.UserModel;
//import vn.com.arillance.cleanhome.utils.Utils;
//
//@Component
//@Path("/api")
//public class JobFinderController {
//	@Autowired
//	private ResourceLoader resourceLoader;
//
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public InputStream index() throws Exception {
//		Resource resource = resourceLoader.getResource("classpath:index.html");
//		File dbAsFile = resource.getFile();
//		return new FileInputStream(dbAsFile);
//	}
//
//	@POST
//	@Path("/login")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Object login(AuthModel auth) throws Exception {
//
//		if (auth.getUserId() == null || auth.getPassword() == null) {
//			return new ErrorMessage(0, "login failed ");
//		}
//		UserDao userDao = new UserDao();
//		UserModel user = userDao.login(auth.getUserId(), auth.getPassword());
//		if (user != null) {
//			return user;
//		} else {
//			return new ErrorMessage(0, "login failed ");
//		}
//	}
//
//	@POST
//	@Path("/loginsocial")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Object loginSocial(UserModel user) throws Exception {
//
//		if (user.getUserId() == null) {
//			return new ErrorMessage(0, "login failed ");
//		}
//		Boolean flag = false;
//		if (user.getSocialType().equalsIgnoreCase("tw")) {
//			flag = checkTwitterLogin(user.getUserId(), user.getToken());
//		}
//		if (user.getSocialType().equalsIgnoreCase("fb")) {
//			flag = checkFacebookLogin(user.getUserId(), user.getToken());
//		}
//		if (flag) {
//			UserDao userDao = new UserDao();
//			UserModel userSocial = userDao.loginSocial(user);
//			if (userSocial != null) {
//				return userSocial;
//			} else {
//				return new ErrorMessage(0, "login failed ");
//			}
//		} else {
//			return new ErrorMessage(0, "login failed ");
//		}
//	}
//
//	private Boolean checkFacebookLogin(String userId, String token) throws Exception{
//	    JSONObject json = Utils.readJsonFromUrl("https://graph.facebook.com/me?access_token="+token);
//	    if (json.get("id").toString().equals(userId)){
//	    	return true;
//	    }
//		return false;
//	}
//
//	private Boolean checkTwitterLogin(String userId, String token) {
//		
//		return true;
//	}
//
//	@POST
//	@Path("/register")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage register(UserModel user) throws Exception {
//		UserDao userDao = new UserDao();
//		userDao.register(user);
//		return new ErrorMessage(1, "success");
//
//	}
//
//	@POST
//	@Path("/createprofile")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage createProfile(ProfileModel profile) throws Exception {
//		profile.setProfileId(UUID.randomUUID().toString());
//		UserDao userDao = new UserDao();
//		userDao.createProfile(profile);
//		return new ErrorMessage(1, "success");
//
//	}
//
//	@POST
//	@Path("/updateaccount")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage updateAccount(UserModel user) throws Exception {
//		UserDao userDao = new UserDao();
//		userDao.updateAccount(user);
//		return new ErrorMessage(1, "success");
//
//	}
//	@POST
//	@Path("/changepassword")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage changePassword(UserModel user) throws Exception {
//		UserDao userDao = new UserDao();
//		userDao.changePassword(user);
//		return new ErrorMessage(1, "success");
//
//	}
//	@POST
//	@Path("/searchuser")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<ProfileModel> search(SearchModel search) throws Exception {
//		if (search == null) {
//			search = new SearchModel();
//		}
//		UserDao userDao = new UserDao();
//		return userDao.search(search);
//	}
//
//	@GET
//	@Path("/getuser")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ProfileModel getUser(@QueryParam("userid") String userId) throws Exception {
//		UserDao userDao = new UserDao();
//		return userDao.getuser(userId);
//	}
//
//	@GET
//	@Path("/getlistprofile")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<ProfileModel> getListProfile(@QueryParam("userid") String userId) throws Exception {
//		UserDao userDao = new UserDao();
//		return userDao.getListProfile(userId);
//	}
//
//	@GET
//	@Path("/getlistjob")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<JobModel> getListJob(@QueryParam("userid") String userId) throws Exception {
//		JobDao jobDao = new JobDao();
//		return jobDao.getListJob(userId);
//	}
//
//	@POST
//	@Path("/searchjob")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<JobModel> searchJob(SearchJobModel search) throws Exception {
//		if (search == null) {
//			search = new SearchJobModel();
//		}
//
//		JobDao jobDao = new JobDao();
//		ArrayList<JobModel> listJob = jobDao.searchJob(search);
//		for (JobModel jobModel : listJob) {
//			jobModel.calRange();
//		}
//		return listJob;
//	}
//
//	@POST
//	@Path("/createjob")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage createJob(JobModel job) throws Exception {
//
//		JobDao jobDao = new JobDao();
//		int maxId = jobDao.getMaxIdDb();
//		job.setJobId("QJ000" + (maxId + 1));
//		jobDao.createJob(job);
//		return new ErrorMessage(1, "success");
//	}
//
//	@GET
//	@Path("/getjob")
//	@Produces(MediaType.APPLICATION_JSON)
//	public JobModel getJob(@QueryParam("jobid") String jobId) throws Exception {
//		JobDao jobDao = new JobDao();
//		return jobDao.getJob(jobId);
//	}
//
//	@GET
//	@Path("/getlistcity")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<String> getListCity() throws Exception {
//		JobDao jobDao = new JobDao();
//		return jobDao.getListCity();
//	}
//
//	@GET
//	@Path("/checkemail")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Boolean checkEmail(@QueryParam("email") String email) throws Exception {
//		UserDao userDao = new UserDao();
//		return userDao.checkEmail(email);
//	}
//
//	@GET
//	@Path("/getskillset")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<SkillSet> getskillset() {
//		JobDao jobDao = new JobDao();
//		return jobDao.getSkillSet();
//	}
//
//	@GET
//	@Path("/getjobset")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ArrayList<JobTitle> getTitleJobs() {
//		JobDao jobDao = new JobDao();
//		return jobDao.getTitleJobs();
//	}
//
//	@POST
//	@Path("/updateposition")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage updateUserPosition(SearchModel user) throws Exception {
//		try {
//			UserDao userDao = new UserDao();
//			userDao.updatePosition(user);
//			return new ErrorMessage(userDao.updatePosition(user), "");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ErrorMessage(0, "failed");
//		}
//	}
//
//	@POST
//	@Path("/updateuserflag")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage updateUserFlag(SearchModel user) throws Exception {
//		try {
//			UserDao userDao = new UserDao();
//			userDao.updateFlag(user);
//			return new ErrorMessage(userDao.updateFlag(user), "");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ErrorMessage(0, "failed");
//		}
//	}
//
//	@POST
//	@Path("/updatejobflag")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public ErrorMessage updateJobFlag(SearchModel user) throws Exception {
//		try {
//			JobDao jobDao = new JobDao();
//			jobDao.updateJobFlag(user);
//			return new ErrorMessage(jobDao.updateJobFlag(user), "");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ErrorMessage(0, "failed");
//		}
//	}
//
//	@POST
//	@Path("/upload")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//
//	public ErrorMessage upload(@FormDataParam("file") InputStream uploadedInputStream,
//			@FormDataParam("file") FormDataContentDisposition fileDetail) {
//		String fileName = "";
//		Properties prop;
//		try {
//			prop = new Properties();
//			InputStream input = null;
//			String fn = "application.properties";
//			input = JobFinderController.class.getClassLoader().getResourceAsStream(fn);
//			if (input == null) {
//				System.out.println("Sorry, unable to find " + fn);
//				return new ErrorMessage(0, "Sorry, unable to find " + fn);
//			}
//			prop.load(input);
//			String enviroment = prop.getProperty("app.environment");
//
//			String folderPath = prop.getProperty("app.folderpath." + enviroment);
//			// upload file
//			fileName = UUID.randomUUID().toString() + ".png";
//			String fileLocation = folderPath + fileName;
//
//			FileOutputStream out = new FileOutputStream(new File(fileLocation));
//			int read = 0;
//			byte[] bytes = new byte[1024];
//			out = new FileOutputStream(new File(fileLocation));
//			while ((read = uploadedInputStream.read(bytes)) != -1) {
//				out.write(bytes, 0, read);
//			}
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return new ErrorMessage(0, e.getMessage());
//		}
//		return new ErrorMessage(1, prop.getProperty("app.service") + fileName);
//	}
//
//}
