package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
public class FeedbackDAO {
    public static void addFeedback(Feedback f) {
        try{
            Connection con = DBConnection.getConnection();
            String sql="INSERT INTO feedback(student_name, course_name, teacher_name, rating, comments) VALUES(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,f.getStudentName());
            ps.setString(2,f.getCourseName());
            ps.setString(3,f.getTeacherName());
            ps.setInt(4,f.getRating());
            ps.setString(5,f.getComments());
            ps.executeUpdate();
        }catch(Exception e){ e.printStackTrace(); }
    }
    public static List<Feedback> getAllFeedback(){
        List<Feedback> list=new ArrayList<>();
        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM feedback");
            while(rs.next()){
                Feedback f=new Feedback(rs.getInt("id"),rs.getString("student_name"),rs.getString("course_name"),
                                        rs.getString("teacher_name"),rs.getInt("rating"),rs.getString("comments"));
                list.add(f);
            }
        }catch(Exception e){ e.printStackTrace(); }
        return list;
    }
    public static void updateFeedback(Feedback f){
        try{
            Connection con = DBConnection.getConnection();
            String sql="UPDATE feedback SET student_name=?, course_name=?, teacher_name=?, rating=?, comments=? WHERE id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,f.getStudentName());
            ps.setString(2,f.getCourseName());
            ps.setString(3,f.getTeacherName());
            ps.setInt(4,f.getRating());
            ps.setString(5,f.getComments());
            ps.setInt(6,f.getId());
            ps.executeUpdate();
        }catch(Exception e){ e.printStackTrace(); }
    }
    public static void deleteFeedback(int id){
        try{
            Connection con=DBConnection.getConnection();
            String sql="DELETE FROM feedback WHERE id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){ e.printStackTrace(); }
    }
}