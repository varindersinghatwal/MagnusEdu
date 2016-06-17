package com.magnus.edutech.webservices;


import android.content.Context;

import com.magnus.edutech.DataBase.DatabaseHandler;
import com.magnus.edutech.model.Chapters;
import com.magnus.edutech.model.Course;
import com.magnus.edutech.model.Videos;

import java.util.List;

/*By :J.K.JOSHI */
public class HelpingClass {

	Context context;
	public static DatabaseHandler db;

	public HelpingClass(Context ctx) {
		this.context = ctx;
		db = new DatabaseHandler(context);
	}

	//Get Table row count 
	public int getRowCountInTable(String tableName)
	{
		return db.getRowCountInTable(tableName);
	}
	// Get All Subjects
	public List<Course> fetchCoursesFromDb(Course course) {
		return db.FETCH_COURSE_LIST(course);
	}

	// Get All Chapters
	public List<Chapters> fetchChaptersFromDb(Chapters chapters) {
		return db.FETCH_CHAPTER_LIST(chapters);
	}

	// Get All Videos
	public List<Videos> fetchVideosFromDb(Videos videos) {
				return db.FETCH_VIDEO_LIST(videos);
	}

	// Get Subject info for particular subject id .
	public Course getSubjectInfoPerSubjectIdFromDB(Course course) {
		return db.TABLE_COURSE_INFO(1, course);
	}
	// updating values into tables
	public void updateIntoTables(Object obj) {
		int queryType = 2;

		if (obj instanceof Course) {

		} else if (obj instanceof Chapters) {

		} else if (obj instanceof Videos) {
			/* db.TABLE_CUSTOMER_INFO(queryType, (Customer) obj); */

		}
	}
	public boolean updateVideoStatusForClient(){

        List<Course> courseList =fetchCoursesFromDb(null);
        if(courseList ==null)
        {
            return false;
        }
        for(int i=0;i<courseList.size();i++)
        {
            Videos videos = new Videos();
            videos.setSubject_id(courseList.get(i).getSubject_id());
            videos.setPurchased(true);
            updateIntoTables(videos);
        }

        GlobalConstants.save_Preferences_boolean(GlobalConstants.PURCHASED,true,context);
		return true;
	}

}
